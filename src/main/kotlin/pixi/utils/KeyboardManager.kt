package pixi.utils

import kotlinx.browser.document
import kotlinx.js.jso
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent

sealed interface KeyboardEvents<T : Any> {
	object keydown : KeyboardEvents<KeyboardEvent>
	object keyup : KeyboardEvents<KeyboardEvent>
}

class KeyboardManager(var enabled: Boolean = true, var ignoreCase: Boolean = false) {
	val listeners = mutableMapOf<String, MutableList<(KeyboardEvent) -> Unit>>()
	val keyPressed = mutableSetOf<Int>()
	val keyPressedNames = mutableSetOf<String>()
	
	init {
		if (enabled) activateEvents()
	}
	
	fun enable() {
		if (enabled) return
		activateEvents()
		enabled = true
	}
	
	fun disable() {
		if (!enabled) return
		off(KeyboardEvents.keydown, ::onKeyDown)
		off(KeyboardEvents.keyup, ::onKeyUp)
		enabled = false
	}
	
	fun isDown(keyCode: Int) = keyCode in keyPressed
	fun isDown(keyName: String) = if (ignoreCase) keyName.lowercase() in keyPressedNames else keyName in keyPressedNames
	fun isPressed(keyCode: Int) = isDown(keyCode)
	fun isPressed(keyName: String) = isDown(keyName)
	fun isUp(keyCode: Int) = !isDown(keyCode)
	fun isUp(keyName: String) = !isDown(keyName)
	
	fun <T : Any> on(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) {
		val name = event::class.simpleName!!
		listeners.getOrPut(name) { mutableListOf() } += callback
		document.addEventListener(name, {
			if (enabled) callback(it as KeyboardEvent)
		})
	}
	
	fun <T : Any> once(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) {
		val name = event::class.simpleName!!
		listeners.getOrPut(name) { mutableListOf() } += callback
		document.addEventListener(name, {
			if (enabled) {
				callback(it as KeyboardEvent)
				listeners[name]?.remove(callback)
			}
		}, jso { once = true })
	}
	
	fun <T : Any> off(event: KeyboardEvents<T>, callback: ((KeyboardEvent) -> Unit)? = null) {
		val name = event::class.simpleName!!
		if (callback == null) {
			listeners[name]?.forEach { document.removeEventListener(name, it as EventListener) }
			listeners.remove(name)
		} else {
			listeners[name]?.removeAll { it == callback }
			document.removeEventListener(name, callback as EventListener)
		}
	}
	
	fun onPress(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keydown, callback)
	
	fun onRelease(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keyup, callback)
	
	fun onPress(key: Int, callback: (KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onPress(key: String, callback: (KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun onRelease(key: Int, callback: (KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onRelease(key: String, callback: (KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun offPress(callback: (KeyboardEvent) -> Unit) = off(KeyboardEvents.keydown, callback)
	
	fun offRelease(callback: (KeyboardEvent) -> Unit) = off(KeyboardEvents.keyup, callback)
	
	fun offPress(key: Int, callback: (event: KeyboardEvent) -> Unit) = offPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun offPress(key: String, callback: (event: KeyboardEvent) -> Unit) = offPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun offRelease(key: Int, callback: (event: KeyboardEvent) -> Unit) = offRelease {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun offRelease(key: String, callback: (event: KeyboardEvent) -> Unit) = offRelease {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	private fun activateEvents() {
		on(KeyboardEvents.keydown, ::onKeyDown)
		on(KeyboardEvents.keyup, ::onKeyUp)
	}
	
	private fun onKeyDown(event: KeyboardEvent) {
		keyPressed += event.keyCode
		keyPressedNames += event.key
	}
	
	private fun onKeyUp(event: KeyboardEvent) {
		keyPressed -= event.keyCode
		keyPressedNames -= event.key
	}
}

data class KeyListener internal constructor(val type: KeyboardEvents<*>, val key: String, val callback: (KeyboardEvent) -> Unit)

class KeyMap(keyMap: Map<String, List<String>> = HashMap(), var enabled: Boolean = true, ignoreCase: Boolean = false) {
	private val listeners = HashMap<String, MutableList<KeyListener>>()
	private val _keys = keyMap.map { it.key to it.value.toMutableSet() }.toMap().toMutableMap()
	val keyboardManager = KeyboardManager(enabled, ignoreCase)
	val keyPressed get() = keyboardManager.keyPressed
	val keys: Map<String, Set<String>>
		get() = _keys.toMap()
	
	fun addPress(entryName: String, key: String, callback: (event: KeyboardEvent) -> Unit = {}) {
		_keys.getOrPut(entryName) { mutableSetOf() } += key
		listeners.getOrPut(entryName) { mutableListOf() } += KeyListener(KeyboardEvents.keydown, key, callback)
		keyboardManager.onPress(key, callback)
	}
	
	fun addRelease(entryName: String, key: String, callback: (event: KeyboardEvent) -> Unit = {}) {
		_keys.getOrPut(entryName) { mutableSetOf() } += key
		listeners.getOrPut(entryName) { mutableListOf() } += KeyListener(KeyboardEvents.keyup, key, callback)
		keyboardManager.onRelease(key, callback)
	}
	
	fun enable() {
		if (enabled) return
		keyboardManager.enable()
		enabled = true
	}
	
	fun disable() {
		if (!enabled) return
		keyboardManager.disable()
		enabled = false
	}
	
	fun isPressed(entry: String) = _keys[entry]?.any { keyboardManager.isPressed(it) } ?: false
	fun isDown(entry: String) = isPressed(entry)
	fun isUp(entry: String) = !isPressed(entry)
	
	fun getEntry(key: String) = _keys.entries.find { it.value.contains(key) }?.key
	
	fun onPress(entry: String, callback: (KeyboardEvent) -> Unit) {
		_keys[entry]?.forEach { addPress(entry, it, callback) }
	}
	
	fun onRelease(entry: String, callback: (KeyboardEvent) -> Unit) {
		_keys[entry]?.forEach { addRelease(entry, it, callback) }
	}
	
	fun offPress(entry: String, callback: ((KeyboardEvent) -> Unit)? = null) {
		_keys[entry]?.forEach { keyboardManager.off(KeyboardEvents.keydown, callback) }
		_keys.remove(entry)
	}
	
	fun offRelease(entry: String, callback: ((KeyboardEvent) -> Unit)? = null) {
		_keys[entry]?.forEach { keyboardManager.off(KeyboardEvents.keyup, callback) }
		_keys.remove(entry)
	}
	
	fun removePress(entryName: String, key: String? = null) {
		if (key == null || _keys[entryName]?.isEmpty() == true) {
			listeners.remove(entryName)?.let {
				it.forEach { keyboardManager.off(it.type, it.callback) }
			}
			_keys.remove(entryName)
		} else {
			_keys[entryName]?.remove(key)
			
			val toRemove = listeners[entryName]?.dropWhile { it.key == key }
			toRemove?.forEach { keyboardManager.off(it.type, it.callback) }
			listeners[entryName]?.removeAll(toRemove ?: listOf())
		}
	}
}

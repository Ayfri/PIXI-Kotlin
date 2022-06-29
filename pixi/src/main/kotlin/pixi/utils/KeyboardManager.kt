package pixi.utils

import kotlinx.browser.document
import kotlinx.js.jso
import org.w3c.dom.events.EventListener
import org.w3c.dom.events.KeyboardEvent
import pixi.externals.extensions.add
import pixi.typings.ticker.Ticker

sealed interface KeyboardEvents<T : Any> {
	object keydown : KeyboardEvents<KeyboardEvent>
	object keyup : KeyboardEvents<KeyboardEvent>
}

internal class KeptCallback(val callback: (KeyboardEvent) -> Unit) {
	lateinit var event: KeyboardEvent
}

class KeyboardManager(enabled: Boolean = true, var ignoreCase: Boolean = false) {
	var enabled = enabled
		private set
	val listeners = mutableMapOf<String, MutableList<(KeyboardEvent) -> Unit>>()
	val keyPressed = mutableSetOf<Int>()
	val keyPressedNames = mutableSetOf<String>()
	val ticker = Ticker()
	
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
		ticker.stop()
		enabled = false
	}
	
	fun isDown(keyCode: Int) = keyCode in keyPressed
	fun isDown(keyName: String) = if (ignoreCase) keyName.lowercase() in keyPressedNames.map(String::lowercase) else keyName in keyPressedNames
	fun isPressed(keyCode: Int) = isDown(keyCode)
	fun isPressed(keyName: String) = isDown(keyName)
	fun isUp(keyCode: Int) = !isDown(keyCode)
	fun isUp(keyName: String) = !isDown(keyName)
	
	fun <T : Any> on(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) {
		val name = event::class.simpleName ?: return
		
		listeners.getOrPut(name, ::mutableListOf) += callback
		document.addEventListener(name, {
			if (enabled) callback(it.unsafeCast<KeyboardEvent>())
		})
	}
	
	fun <T : Any> once(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) {
		val name = event::class.simpleName ?: return
		
		listeners.getOrPut(name, ::mutableListOf) += callback
		document.addEventListener(name, {
			if (enabled) {
				callback(it.unsafeCast<KeyboardEvent>())
				listeners[name]?.remove(callback)
			}
		}, jso { once = true })
	}
	
	fun <T : Any> off(event: KeyboardEvents<T>, callback: ((KeyboardEvent) -> Unit)? = null) {
		val name = event::class.simpleName ?: return
		
		if (callback == null) {
			listeners[name]?.forEach { document.removeEventListener(name, it.unsafeCast<EventListener>()) }
			listeners.remove(name)
		} else {
			listeners[name]?.removeAll { it == callback }
			document.removeEventListener(name, callback.unsafeCast<EventListener>())
		}
	}
	
	fun onKeep(key: Int, callback: (KeyboardEvent) -> Unit) {
		val kept = KeptCallback(callback)
		
		onPress {
			if (ignoreCase && it.keyCode.toChar() == key.toChar()) kept.event = it
			else if (!ignoreCase && it.keyCode == key) kept.event = it
		}
		
		ticker.add {
			if (isPressed(key) && kept::event.isInitialized) kept.callback(kept.event)
		}
	}
	
	fun onKeep(key: String, callback: (KeyboardEvent) -> Unit) {
		val kept = KeptCallback(callback)
		
		onPress {
			if (ignoreCase && it.key.lowercase() == key.lowercase()) kept.event = it
			else if (!ignoreCase && it.key == key) kept.event = it
		}
		
		ticker.add {
			if (isPressed(key) && kept::event.isInitialized) kept.callback(kept.event)
		}
	}
	
	fun onPress(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keydown, callback)
	
	fun onPress(key: Int, callback: (KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onPress(key: String, callback: (KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun onRelease(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keyup, callback)
	
	fun onRelease(key: Int, callback: (KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onRelease(key: String, callback: (KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun offPress(callback: (KeyboardEvent) -> Unit) = off(KeyboardEvents.keydown, callback)
	
	fun offPress(key: Int, callback: (event: KeyboardEvent) -> Unit) = offPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun offPress(key: String, callback: (event: KeyboardEvent) -> Unit) = offPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun offRelease(callback: (KeyboardEvent) -> Unit) = off(KeyboardEvents.keyup, callback)
	
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
		ticker.start()
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

class KeyMap(keyMap: Map<String, Set<String>> = HashMap(), enabled: Boolean = true, ignoreCase: Boolean = false) {
	var enabled = enabled
		private set
	private val listeners = HashMap<String, MutableList<KeyListener>>()
	private val _keys = keyMap.mapValues { it.value.toMutableSet() }.toMutableMap()
	val keyboardManager = KeyboardManager(enabled, ignoreCase)
	val keyPressed get() = keyboardManager.keyPressed
	val keyPressedNames get() = keyboardManager.keyPressedNames
	val keys: Map<String, Set<String>>
		get() = _keys.toMap()
	
	val debug get() = listeners
	
	fun addPress(entryName: String, key: String, callback: (KeyboardEvent) -> Unit = {}) {
		_keys.getOrPut(entryName, ::mutableSetOf) += key
		listeners.getOrPut(entryName, ::mutableListOf) += KeyListener(KeyboardEvents.keydown, key, callback)
		keyboardManager.onPress(key, callback)
	}
	
	fun addRelease(entryName: String, key: String, callback: (KeyboardEvent) -> Unit = {}) {
		_keys.getOrPut(entryName, ::mutableSetOf) += key
		listeners.getOrPut(entryName, ::mutableListOf) += KeyListener(KeyboardEvents.keyup, key, callback)
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
	
	fun isPressed(entry: String) = _keys[entry]?.any(keyboardManager::isPressed) ?: false
	fun isDown(entry: String) = isPressed(entry)
	fun isUp(entry: String) = !isPressed(entry)
	
	fun getEntry(key: String) = _keys.entries.find { it.value.contains(key) }?.key
	
	fun onKeep(entry: String, callback: (KeyboardEvent) -> Unit) {
		val kept = KeptCallback(callback)
		
		keyboardManager.onPress {
			if (isPressed(entry)) kept.event = it
		}
		
		keyboardManager.ticker.add {
			if (isPressed(entry) && kept::event.isInitialized) kept.callback(kept.event)
		}
	}
	
	fun onPress(entry: String, callback: (KeyboardEvent) -> Unit) = _keys[entry]?.forEach { addPress(entry, it, callback) }
	
	fun onRelease(entry: String, callback: (KeyboardEvent) -> Unit) = _keys[entry]?.forEach { addRelease(entry, it, callback) }
	
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
			listeners.remove(entryName)?.let { keyListeners ->
				keyListeners.forEach { keyboardManager.off(it.type, it.callback) }
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

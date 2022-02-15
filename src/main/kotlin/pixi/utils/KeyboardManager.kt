package pixi.utils

import kotlinx.browser.window
import kotlinx.js.jso
import org.w3c.dom.events.KeyboardEvent
import pixi.typings.utils.EventEmitter

sealed interface KeyboardEvents<T : Any> {
	object keydown : KeyboardEvents<KeyboardEvent>
	object keyup : KeyboardEvents<KeyboardEvent>
}

class KeyboardManager(var enabled: Boolean = true, var ignoreCase: Boolean = false) : EventEmitter() {
	var keyPressed = mutableSetOf<Int>()
	
	init {
		if (enabled) enable()
	}
	
	fun enable() {
		if (enabled) return
		on(KeyboardEvents.keydown, ::onKeyDown)
		on(KeyboardEvents.keyup, ::onKeyUp)
		enabled = true
	}
	
	fun disable() {
		if (!enabled) return
		off(KeyboardEvents.keydown, ::onKeyDown)
		off(KeyboardEvents.keyup, ::onKeyUp)
		enabled = false
	}
	
	fun isDown(keyCode: Int) = keyCode in keyPressed
	fun isUp(keyCode: Int) = keyCode !in keyPressed
	
	fun <T : Any> on(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) = window.addEventListener(event::class.simpleName!!, {
		if (enabled) callback(it as KeyboardEvent)
	})
	
	fun <T : Any> once(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) = window.addEventListener(event::class.simpleName!!, {
		if (enabled) callback(it as KeyboardEvent)
	}, jso { once = true })
	
	fun <T : Any> off(event: KeyboardEvents<T>, callback: (KeyboardEvent) -> Unit) {
		window.removeEventListener(event::class.simpleName!!, { callback(it as KeyboardEvent) })
	}
	
	fun onPress(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keydown, callback)
	
	fun onRelease(callback: (KeyboardEvent) -> Unit) = on(KeyboardEvents.keyup, callback)
	
	fun onPress(key: Int, callback: (event: KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onPress(key: String, callback: (event: KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	fun onRelease(key: Int, callback: (event: KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	fun onRelease(key: String, callback: (event: KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	private fun onKeyDown(event: KeyboardEvent) {
		keyPressed += event.keyCode
	}
	
	private fun onKeyUp(event: KeyboardEvent) {
		keyPressed -= event.keyCode
	}
}

class KeyMap(keys: MutableMap<String, MutableList<String>> = mutableMapOf(), var enabled: Boolean = true, ignoreCase: Boolean = false) {
	private var _keys = keys
	private val callbacks = mutableMapOf<String, MutableList<(KeyboardEvent) -> Unit>>()
	val keyboardManager = KeyboardManager(true, ignoreCase)
	var keys: MutableMap<String, MutableList<String>>
		get() = _keys
		set(value) {
			_keys = value
		}
	
	init {
		if (enabled) enable()
	}
	
	fun add(entryName: String, key: String, callback: (event: KeyboardEvent) -> Unit = {}) {
		keys.getOrPut(entryName) { mutableListOf() } += key
		onPress(key, callback)
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
	
	fun getEntry(key: String) = keys.entries.find { it.value.contains(key) }?.key
	
	fun onPress(entry: String, callback: (KeyboardEvent) -> Unit) {
		callbacks[entry]?.set(0, callback)
	}
	
	fun onRelease(entry: String, callback: (KeyboardEvent) -> Unit) {
		callbacks[entry]?.set(1, callback)
	}
	
	fun offPress(entry: String) {
		callbacks[entry]?.set(0) {}
	}
	
	fun offRelease(entry: String) {
		callbacks[entry]?.set(1) {}
	}
	
	fun remove(entryName: String, key: String? = null) {
		if (key == null || keys[entryName]?.isEmpty() == true) {
			keys.remove(entryName)
			callbacks.remove(entryName)
		} else {
			keys[entryName]?.remove(key)
		}
	}
}

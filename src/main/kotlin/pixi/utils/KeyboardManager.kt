package pixi.utils

import kotlinx.browser.window
import org.w3c.dom.events.KeyboardEvent
import pixi.typings.utils.EventEmitter

sealed interface KeyboardEvents<T> {
	object keydown : KeyboardEvents<KeyboardEvent>
	object keyup : KeyboardEvents<KeyboardEvent>
}

class KeyboardManager(var enabled: Boolean = true, var ignoreCase: Boolean = false) : EventEmitter() {
	var keyPressed = mutableSetOf<Int>()
	val keyPressedAsString get() = keyPressed.map { it.toChar() }
	
	init {
		if (enabled) enable()
	}
	
	fun enable() {
		if (enabled) return
		on<KeyboardEvents.keydown>(::onKeyDown)
		on<KeyboardEvents.keyup>(::onKeyUp)
		enabled = true
	}
	
	fun disable() {
		if (!enabled) return
		off<KeyboardEvents.keydown>(::onKeyDown)
		off<KeyboardEvents.keyup>(::onKeyUp)
		enabled = false
	}
	
	fun isDown(key: KeyboardEvent) = key.keyCode in keyPressed
	fun isUp(key: KeyboardEvent) = !isDown(key)
	
	inline fun <reified T : KeyboardEvents<*>> on(crossinline callback: (KeyboardEvent) -> Unit) {
		window.addEventListener(T::class.simpleName!!, { callback(it as KeyboardEvent) })
	}
	
	inline fun onPress(crossinline callback: (KeyboardEvent) -> Unit) {
		window.addEventListener("keydown", { callback(it as KeyboardEvent) })
	}
	
	inline fun onRelease(crossinline callback: (KeyboardEvent) -> Unit) {
		window.addEventListener("keyup", { callback(it as KeyboardEvent) })
	}
	
	inline fun onPress(key: Int, crossinline callback: (event: KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	inline fun onPress(key: String, crossinline callback: (event: KeyboardEvent) -> Unit) = onPress {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	inline fun onRelease(key: Int, crossinline callback: (event: KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.keyCode.toChar() == key.toChar()) callback(it)
		else if (!ignoreCase && it.keyCode == key) callback(it)
	}
	
	inline fun onRelease(key: String, crossinline callback: (event: KeyboardEvent) -> Unit) = onRelease {
		if (ignoreCase && it.key.lowercase() == key.lowercase()) callback(it)
		else if (!ignoreCase && it.key == key) callback(it)
	}
	
	inline fun <reified T : KeyboardEvents<*>> off(crossinline callback: (KeyboardEvent) -> Unit) {
		window.removeEventListener(T::class.simpleName!!, { callback(it as KeyboardEvent) })
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

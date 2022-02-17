package pixi.utils

import kotlinx.browser.window
import kotlinx.js.jso
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import pixi.typings.interaction.Button

@Suppress("ClassName")
sealed interface MouseEvents<T> {
	object click : MouseEvents<MouseEvent>
	object dblclick : MouseEvents<MouseEvent>
	object mousedown : MouseEvents<MouseEvent>
	object mouseenter : MouseEvents<MouseEvent>
	object mouseleave : MouseEvents<MouseEvent>
	object mousemove : MouseEvents<MouseEvent>
	object mouseover : MouseEvents<MouseEvent>
	object mouseout : MouseEvents<MouseEvent>
	object mouseup : MouseEvents<MouseEvent>
	object mousewheel : MouseEvents<WheelEvent>
}

class MouseManager(var enabled: Boolean = true) {
	var pressed = mutableSetOf<Short>()
	var overWindow = false
	
	init {
		if (enabled) activateEvents()
	}
	
	fun enable() {
		if (enabled) return
		activateEvents()
		enabled = true
	}
	
	private fun activateEvents() {
		on(MouseEvents.mousedown, ::onMouseDown)
		on(MouseEvents.mouseup, ::onMouseUp)
		on(MouseEvents.mouseenter) { onMouseEnter() }
		on(MouseEvents.mouseleave) { onMouseLeave() }
	}
	
	fun disable() {
		if (!enabled) return
		off(MouseEvents.mousedown, ::onMouseDown)
		off(MouseEvents.mouseup, ::onMouseUp)
		enabled = false
	}
	
	fun isPressed(button: Short) = button in pressed
	fun isPressed(button: Button) = button.ordinal.toShort() in pressed
	fun isNotPressed(button: Short) = button !in pressed
	fun isNotPressed(button: Button) = button.ordinal.toShort() !in pressed
	
	fun <T : Any> on(event: MouseEvents<T>, callback: (T) -> Unit) = window.addEventListener(event::class.simpleName!!, {
		if (enabled) callback(it as T)
	})
	
	fun <T : Any> once(event: MouseEvents<T>, callback: (T) -> Unit) = window.addEventListener(event::class.simpleName!!, {
		if (enabled) callback(it as T)
	}, jso { once = true })
	
	fun <T : Any> off(event: MouseEvents<T>, callback: (T) -> Unit) = window.removeEventListener(event::class.simpleName!!, { callback(it as T) })
	
	fun onDoubleClick(callback: (MouseEvent) -> Unit) = on(MouseEvents.dblclick, callback)
	
	fun onMouseDown(callback: (MouseEvent) -> Unit) = on(MouseEvents.mousedown, callback)
	
	fun onMouseDown(button: Button, callback: (MouseEvent) -> Unit) = on(MouseEvents.mousedown) {
		if (it.button == button.ordinal.toShort()) callback(it)
	}
	
	fun onMouseUp(callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseup, callback)
	
	fun onMouseUp(button: Button, callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseup) {
		if (it.button == button.ordinal.toShort()) callback(it)
	}
	
	fun onMouseEnter(callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseenter, callback)
	
	fun onMouseLeave(callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseleave, callback)
	
	fun onMouseMove(callback: (MouseEvent) -> Unit) = on(MouseEvents.mousemove, callback)
	
	fun onMouseOver(callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseover, callback)
	
	fun onMouseOut(callback: (MouseEvent) -> Unit) = on(MouseEvents.mouseout, callback)
	
	fun onMouseWheel(callback: (WheelEvent) -> Unit) = on(MouseEvents.mousewheel, callback)
	
	private fun onMouseDown(event: MouseEvent) {
		pressed += event.button
	}
	
	private fun onMouseUp(event: MouseEvent) {
		pressed -= event.button
	}
	
	private fun onMouseEnter() {
		overWindow = true
	}
	
	private fun onMouseLeave() {
		overWindow = false
	}
}

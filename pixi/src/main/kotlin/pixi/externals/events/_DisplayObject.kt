package pixi.externals.events

import pixi.externals.Tuple
import pixi.typings.ListenerFn
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.interaction.InteractionEvent


sealed interface DisplayObjectInteractionEvents<T : Any> {
	object added : DisplayObjectInteractionEvents<Container<DisplayObject>>
	object click : DisplayObjectInteractionEvents<InteractionEvent>
	object destroyed : DisplayObjectInteractionEvents<Nothing>
	object mousedown : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseenter : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseleave : DisplayObjectInteractionEvents<InteractionEvent>
	object mousemove : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseout : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseover : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseup : DisplayObjectInteractionEvents<InteractionEvent>
	object mouseupoutside : DisplayObjectInteractionEvents<InteractionEvent>
	object pointercancel : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerdown : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerenter : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerleave : DisplayObjectInteractionEvents<InteractionEvent>
	object pointermove : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerout : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerover : DisplayObjectInteractionEvents<InteractionEvent>
	object pointertap : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerup : DisplayObjectInteractionEvents<InteractionEvent>
	object pointerupoutside : DisplayObjectInteractionEvents<InteractionEvent>
	object removed : DisplayObjectInteractionEvents<Container<DisplayObject>>
	object removedFrom : DisplayObjectInteractionEvents<Tuple<DisplayObject, Container<DisplayObject>, Int>>
	object rightclick : DisplayObjectInteractionEvents<InteractionEvent>
	object rightdown : DisplayObjectInteractionEvents<InteractionEvent>
	object rightup : DisplayObjectInteractionEvents<InteractionEvent>
	object rightupoutside : DisplayObjectInteractionEvents<InteractionEvent>
	object tap : DisplayObjectInteractionEvents<InteractionEvent>
	object touchcancel : DisplayObjectInteractionEvents<InteractionEvent>
	object touchend : DisplayObjectInteractionEvents<InteractionEvent>
	object touchendoutside : DisplayObjectInteractionEvents<InteractionEvent>
	object touchmove : DisplayObjectInteractionEvents<InteractionEvent>
	object touchstart : DisplayObjectInteractionEvents<InteractionEvent>
}

fun <T : DisplayObjectInteractionEvents<out E>, E> DisplayObject.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> DisplayObject.on(event: DisplayObjectInteractionEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> DisplayObject.once(event: DisplayObjectInteractionEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> DisplayObject.off(event: DisplayObjectInteractionEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun DisplayObject.emitAdded(container: Container<DisplayObject>) = emit(DisplayObjectInteractionEvents.added, container)
fun DisplayObject.onAdded(callback: (Container<DisplayObject>) -> Unit) = on(DisplayObjectInteractionEvents.added, callback)
fun DisplayObject.onceAdded(callback: (Container<DisplayObject>) -> Unit) = once(DisplayObjectInteractionEvents.added, callback)
fun DisplayObject.offAdded(callback: (Container<DisplayObject>) -> Unit) = off(DisplayObjectInteractionEvents.added, callback)

fun DisplayObject.emitClick(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.click, event)
fun DisplayObject.onClick(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.click, callback)
fun DisplayObject.onceClick(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.click, callback)
fun DisplayObject.offClick(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.click, callback)

fun DisplayObject.emitDestroyed() = emit(DisplayObjectInteractionEvents.destroyed)
fun DisplayObject.onDestroyed(callback: (Nothing) -> Unit) = on(DisplayObjectInteractionEvents.destroyed, callback)
fun DisplayObject.onceDestroyed(callback: (Nothing) -> Unit) = once(DisplayObjectInteractionEvents.destroyed, callback)
fun DisplayObject.offDestroyed(callback: (Nothing) -> Unit) = off(DisplayObjectInteractionEvents.destroyed, callback)

fun DisplayObject.emitMouseDown(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mousedown, event)
fun DisplayObject.onMouseDown(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mousedown, callback)
fun DisplayObject.onceMouseDown(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mousedown, callback)
fun DisplayObject.offMouseDown(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mousedown, callback)

fun DisplayObject.emitMouseEnter(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseenter, event)
fun DisplayObject.onMouseEnter(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseenter, callback)
fun DisplayObject.onceMouseEnter(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseenter, callback)
fun DisplayObject.offMouseEnter(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseenter, callback)

fun DisplayObject.emitMouseLeave(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseleave, event)
fun DisplayObject.onMouseLeave(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseleave, callback)
fun DisplayObject.onceMouseLeave(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseleave, callback)
fun DisplayObject.offMouseLeave(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseleave, callback)

fun DisplayObject.emitMouseMove(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mousemove, event)
fun DisplayObject.onMouseMove(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mousemove, callback)
fun DisplayObject.onceMouseMove(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mousemove, callback)
fun DisplayObject.offMouseMove(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mousemove, callback)

fun DisplayObject.emitMouseOut(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseout, event)
fun DisplayObject.onMouseOut(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseout, callback)
fun DisplayObject.onceMouseOut(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseout, callback)
fun DisplayObject.offMouseOut(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseout, callback)

fun DisplayObject.emitMouseOver(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseover, event)
fun DisplayObject.onMouseOver(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseover, callback)
fun DisplayObject.onceMouseOver(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseover, callback)
fun DisplayObject.offMouseOver(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseover, callback)

fun DisplayObject.emitMouseUp(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseup, event)
fun DisplayObject.onMouseUp(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseup, callback)
fun DisplayObject.onceMouseUp(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseup, callback)
fun DisplayObject.offMouseUp(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseup, callback)

fun DisplayObject.emitMouseUpOutside(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.mouseupoutside, event)
fun DisplayObject.onMouseUpOutside(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.mouseupoutside, callback)
fun DisplayObject.onceMouseUpOutside(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.mouseupoutside, callback)
fun DisplayObject.offMouseUpOutside(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.mouseupoutside, callback)

fun DisplayObject.emitPointerCancel(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointercancel, event)
fun DisplayObject.onPointerCancel(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointercancel, callback)
fun DisplayObject.oncePointerCancel(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointercancel, callback)
fun DisplayObject.offPointerCancel(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointercancel, callback)

fun DisplayObject.emitPointerDown(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerdown, event)
fun DisplayObject.onPointerDown(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerdown, callback)
fun DisplayObject.oncePointerDown(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerdown, callback)
fun DisplayObject.offPointerDown(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerdown, callback)

fun DisplayObject.emitPointerEnter(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerenter, event)
fun DisplayObject.onPointerEnter(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerenter, callback)
fun DisplayObject.oncePointerEnter(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerenter, callback)
fun DisplayObject.offPointerEnter(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerenter, callback)

fun DisplayObject.emitPointerLeave(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerleave, event)
fun DisplayObject.onPointerLeave(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerleave, callback)
fun DisplayObject.oncePointerLeave(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerleave, callback)
fun DisplayObject.offPointerLeave(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerleave, callback)

fun DisplayObject.emitPointerMove(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointermove, event)
fun DisplayObject.onPointerMove(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointermove, callback)
fun DisplayObject.oncePointerMove(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointermove, callback)
fun DisplayObject.offPointerMove(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointermove, callback)

fun DisplayObject.emitPointerOut(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerout, event)
fun DisplayObject.onPointerOut(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerout, callback)
fun DisplayObject.oncePointerOut(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerout, callback)
fun DisplayObject.offPointerOut(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerout, callback)

fun DisplayObject.emitPointerOver(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerover, event)
fun DisplayObject.onPointerOver(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerover, callback)
fun DisplayObject.oncePointerOver(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerover, callback)
fun DisplayObject.offPointerOver(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerover, callback)

fun DisplayObject.emitPointerTap(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointertap, event)
fun DisplayObject.onPointerTap(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointertap, callback)
fun DisplayObject.oncePointerTap(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointertap, callback)
fun DisplayObject.offPointerTap(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointertap, callback)

fun DisplayObject.emitPointerUp(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerup, event)
fun DisplayObject.onPointerUp(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerup, callback)
fun DisplayObject.oncePointerUp(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerup, callback)
fun DisplayObject.offPointerUp(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerup, callback)

fun DisplayObject.emitPointerUpOutside(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.pointerupoutside, event)
fun DisplayObject.onPointerUpOutside(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.pointerupoutside, callback)
fun DisplayObject.oncePointerUpOutside(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.pointerupoutside, callback)
fun DisplayObject.offPointerUpOutside(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.pointerupoutside, callback)

fun DisplayObject.emitRemoved(container: Container<DisplayObject>) = emit(DisplayObjectInteractionEvents.removed, container)
fun DisplayObject.onRemoved(callback: (Container<DisplayObject>) -> Unit) = on(DisplayObjectInteractionEvents.removed, callback)
fun DisplayObject.onceRemoved(callback: (Container<DisplayObject>) -> Unit) = once(DisplayObjectInteractionEvents.removed, callback)
fun DisplayObject.offRemoved(callback: (Container<DisplayObject>) -> Unit) = off(DisplayObjectInteractionEvents.removed, callback)

fun DisplayObject.emitRemovedFrom(child: DisplayObject, container: Container<DisplayObject>, index: Int) = emit(DisplayObjectInteractionEvents.removedFrom, child, container, index)
fun DisplayObject.onRemovedFrom(callback: (Tuple<DisplayObject, Container<DisplayObject>, Int>) -> Unit) = on(DisplayObjectInteractionEvents.removedFrom, callback)
fun DisplayObject.onceRemovedFrom(callback: (Tuple<DisplayObject, Container<DisplayObject>, Int>) -> Unit) = once(DisplayObjectInteractionEvents.removedFrom, callback)
fun DisplayObject.offRemovedFrom(callback: (Tuple<DisplayObject, Container<DisplayObject>, Int>) -> Unit) = off(DisplayObjectInteractionEvents.removedFrom, callback)

fun DisplayObject.emitRightClick(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.rightclick, event)
fun DisplayObject.onRightClick(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.rightclick, callback)
fun DisplayObject.onceRightClick(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.rightclick, callback)
fun DisplayObject.offRightClick(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.rightclick, callback)

fun DisplayObject.emitRightDown(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.rightdown, event)
fun DisplayObject.onRightDown(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.rightdown, callback)
fun DisplayObject.onceRightDown(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.rightdown, callback)
fun DisplayObject.offRightDown(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.rightdown, callback)

fun DisplayObject.emitRightUp(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.rightup, event)
fun DisplayObject.onRightUp(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.rightup, callback)
fun DisplayObject.onceRightUp(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.rightup, callback)
fun DisplayObject.offRightUp(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.rightup, callback)

fun DisplayObject.emitRightUpOutside(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.rightupoutside, event)
fun DisplayObject.onRightUpOutside(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.rightupoutside, callback)
fun DisplayObject.onceRightUpOutside(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.rightupoutside, callback)
fun DisplayObject.offRightUpOutside(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.rightupoutside, callback)

fun DisplayObject.emitTap(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.tap, event)
fun DisplayObject.onTap(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.tap, callback)
fun DisplayObject.onceTap(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.tap, callback)
fun DisplayObject.offTap(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.tap, callback)

fun DisplayObject.emitTouchCancel(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.touchcancel, event)
fun DisplayObject.onTouchCancel(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.touchcancel, callback)
fun DisplayObject.onceTouchCancel(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.touchcancel, callback)
fun DisplayObject.offTouchCancel(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.touchcancel, callback)

fun DisplayObject.emitTouchEnd(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.touchend, event)
fun DisplayObject.onTouchEnd(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.touchend, callback)
fun DisplayObject.onceTouchEnd(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.touchend, callback)
fun DisplayObject.offTouchEnd(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.touchend, callback)

fun DisplayObject.emitTouchEndOutside(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.touchendoutside, event)
fun DisplayObject.onTouchEndOutside(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.touchendoutside, callback)
fun DisplayObject.onceTouchEndOutside(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.touchendoutside, callback)
fun DisplayObject.offTouchEndOutside(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.touchendoutside, callback)

fun DisplayObject.emitTouchMove(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.touchmove, event)
fun DisplayObject.onTouchMove(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.touchmove, callback)
fun DisplayObject.onceTouchMove(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.touchmove, callback)
fun DisplayObject.offTouchMove(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.touchmove, callback)

fun DisplayObject.emitTouchStart(event: InteractionEvent) = emit(DisplayObjectInteractionEvents.touchstart, event)
fun DisplayObject.onTouchStart(callback: (InteractionEvent) -> Unit) = on(DisplayObjectInteractionEvents.touchstart, callback)
fun DisplayObject.onceTouchStart(callback: (InteractionEvent) -> Unit) = once(DisplayObjectInteractionEvents.touchstart, callback)
fun DisplayObject.offTouchStart(callback: (InteractionEvent) -> Unit) = off(DisplayObjectInteractionEvents.touchstart, callback)

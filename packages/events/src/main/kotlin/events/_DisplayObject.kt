package events

import pixi.FederatedPointerEvent
import pixi.FederatedWheelEvent
import pixi.externals.Tuple
import pixi.typings.ListenerFn
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

sealed interface DisplayObjectEvents<T : Any> {
	object added : DisplayObjectEvents<Container>
	object click : DisplayObjectEvents<FederatedPointerEvent>
	object clickcapture : DisplayObjectEvents<FederatedPointerEvent>
	object destroyed : DisplayObjectEvents<Nothing>
	object mousedown : DisplayObjectEvents<FederatedPointerEvent>
	object mousedowncapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseenter : DisplayObjectEvents<FederatedPointerEvent>
	object mouseentercapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseleave : DisplayObjectEvents<FederatedPointerEvent>
	object mouseleavecapture : DisplayObjectEvents<FederatedPointerEvent>
	object mousemove : DisplayObjectEvents<FederatedPointerEvent>
	object mousemovecapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseout : DisplayObjectEvents<FederatedPointerEvent>
	object mouseoutcapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseover : DisplayObjectEvents<FederatedPointerEvent>
	object mouseovercapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseup : DisplayObjectEvents<FederatedPointerEvent>
	object mouseupcapture : DisplayObjectEvents<FederatedPointerEvent>
	object mouseupoutside : DisplayObjectEvents<FederatedPointerEvent>
	object mouseupoutsidecapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointercancel : DisplayObjectEvents<FederatedPointerEvent>
	object pointercancelcapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerdown : DisplayObjectEvents<FederatedPointerEvent>
	object pointerdowncapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerenter : DisplayObjectEvents<FederatedPointerEvent>
	object pointerentercapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerleave : DisplayObjectEvents<FederatedPointerEvent>
	object pointerleavecapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointermove : DisplayObjectEvents<FederatedPointerEvent>
	object pointermovecapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerout : DisplayObjectEvents<FederatedPointerEvent>
	object pointeroutcapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerover : DisplayObjectEvents<FederatedPointerEvent>
	object pointerovercapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointertap : DisplayObjectEvents<FederatedPointerEvent>
	object pointertapcapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerup : DisplayObjectEvents<FederatedPointerEvent>
	object pointerupcapture : DisplayObjectEvents<FederatedPointerEvent>
	object pointerupoutside : DisplayObjectEvents<FederatedPointerEvent>
	object pointerupoutsidecapture : DisplayObjectEvents<FederatedPointerEvent>
	object removed : DisplayObjectEvents<Container>
	object removedFrom : DisplayObjectEvents<Tuple<DisplayObject, Container, Int>>
	object rightclick : DisplayObjectEvents<FederatedPointerEvent>
	object rightclickcapture : DisplayObjectEvents<FederatedPointerEvent>
	object rightdown : DisplayObjectEvents<FederatedPointerEvent>
	object rightdowncapture : DisplayObjectEvents<FederatedPointerEvent>
	object rightup : DisplayObjectEvents<FederatedPointerEvent>
	object rightupcapture : DisplayObjectEvents<FederatedPointerEvent>
	object rightupoutside : DisplayObjectEvents<FederatedPointerEvent>
	object rightupoutsidecapture : DisplayObjectEvents<FederatedPointerEvent>
	object tap : DisplayObjectEvents<FederatedPointerEvent>
	object tapcapture : DisplayObjectEvents<FederatedPointerEvent>
	object touchcancel : DisplayObjectEvents<FederatedPointerEvent>
	object touchcancelcapture : DisplayObjectEvents<FederatedPointerEvent>
	object touchend : DisplayObjectEvents<FederatedPointerEvent>
	object touchendcapture : DisplayObjectEvents<FederatedPointerEvent>
	object touchendoutside : DisplayObjectEvents<FederatedPointerEvent>
	object touchendoutsidecapture : DisplayObjectEvents<FederatedPointerEvent>
	object touchmove : DisplayObjectEvents<FederatedPointerEvent>
	object touchmovecapture : DisplayObjectEvents<FederatedPointerEvent>
	object touchstart : DisplayObjectEvents<FederatedPointerEvent>
	object touchstartcapture : DisplayObjectEvents<FederatedPointerEvent>
	object wheel : DisplayObjectEvents<FederatedWheelEvent>
	object wheelcapture : DisplayObjectEvents<FederatedWheelEvent>
}


fun <T : DisplayObjectEvents<out E>, E> DisplayObject.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> DisplayObject.on(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> DisplayObject.once(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> DisplayObject.off(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun DisplayObject.emitAdded(container: Container) = emit(DisplayObjectEvents.added, container)
fun DisplayObject.onAdded(callback: (Container) -> Unit) = on(DisplayObjectEvents.added, callback)
fun DisplayObject.onceAdded(callback: (Container) -> Unit) = once(DisplayObjectEvents.added, callback)
fun DisplayObject.offAdded(callback: (Container) -> Unit) = off(DisplayObjectEvents.added, callback)

fun DisplayObject.emitClick(event: FederatedPointerEvent) = emit(DisplayObjectEvents.click, event)
fun DisplayObject.onClick(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.click, callback)
fun DisplayObject.onceClick(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.click, callback)
fun DisplayObject.offClick(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.click, callback)

fun DisplayObject.emitClickCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.clickcapture, event)
fun DisplayObject.onClickCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.clickcapture, callback)
fun DisplayObject.onceClickCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.clickcapture, callback)
fun DisplayObject.offClickCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.clickcapture, callback)

fun DisplayObject.emitDestroyed() = emit(DisplayObjectEvents.destroyed)
fun DisplayObject.onDestroyed(callback: (Nothing) -> Unit) = on(DisplayObjectEvents.destroyed, callback)
fun DisplayObject.onceDestroyed(callback: (Nothing) -> Unit) = once(DisplayObjectEvents.destroyed, callback)
fun DisplayObject.offDestroyed(callback: (Nothing) -> Unit) = off(DisplayObjectEvents.destroyed, callback)

fun DisplayObject.emitMouseDown(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mousedown, event)
fun DisplayObject.onMouseDown(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mousedown, callback)
fun DisplayObject.onceMouseDown(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mousedown, callback)
fun DisplayObject.offMouseDown(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mousedown, callback)

fun DisplayObject.emitMouseDownCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mousedowncapture, event)
fun DisplayObject.onMouseDownCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mousedowncapture, callback)
fun DisplayObject.onceMouseDownCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mousedowncapture, callback)
fun DisplayObject.offMouseDownCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mousedowncapture, callback)

fun DisplayObject.emitMouseEnter(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseenter, event)
fun DisplayObject.onMouseEnter(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseenter, callback)
fun DisplayObject.onceMouseEnter(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseenter, callback)
fun DisplayObject.offMouseEnter(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseenter, callback)

fun DisplayObject.emitMouseEnterCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseentercapture, event)
fun DisplayObject.onMouseEnterCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseentercapture, callback)
fun DisplayObject.onceMouseEnterCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseentercapture, callback)
fun DisplayObject.offMouseEnterCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseentercapture, callback)

fun DisplayObject.emitMouseLeave(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseleave, event)
fun DisplayObject.onMouseLeave(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseleave, callback)
fun DisplayObject.onceMouseLeave(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseleave, callback)
fun DisplayObject.offMouseLeave(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseleave, callback)

fun DisplayObject.emitMouseLeaveCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseleavecapture, event)
fun DisplayObject.onMouseLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseleavecapture, callback)
fun DisplayObject.onceMouseLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseleavecapture, callback)
fun DisplayObject.offMouseLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseleavecapture, callback)

fun DisplayObject.emitMouseMove(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mousemove, event)
fun DisplayObject.onMouseMove(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mousemove, callback)
fun DisplayObject.onceMouseMove(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mousemove, callback)
fun DisplayObject.offMouseMove(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mousemove, callback)

fun DisplayObject.emitMouseMoveCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mousemovecapture, event)
fun DisplayObject.onMouseMoveCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mousemovecapture, callback)
fun DisplayObject.onceMouseMoveCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mousemovecapture, callback)
fun DisplayObject.offMouseMoveCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mousemovecapture, callback)

fun DisplayObject.emitMouseOut(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseout, event)
fun DisplayObject.onMouseOut(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseout, callback)
fun DisplayObject.onceMouseOut(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseout, callback)
fun DisplayObject.offMouseOut(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseout, callback)

fun DisplayObject.emitMouseOutCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseoutcapture, event)
fun DisplayObject.onMouseOutCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseoutcapture, callback)
fun DisplayObject.onceMouseOutCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseoutcapture, callback)
fun DisplayObject.offMouseOutCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseoutcapture, callback)

fun DisplayObject.emitMouseOver(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseover, event)
fun DisplayObject.onMouseOver(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseover, callback)
fun DisplayObject.onceMouseOver(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseover, callback)
fun DisplayObject.offMouseOver(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseover, callback)

fun DisplayObject.emitMouseOverCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseovercapture, event)
fun DisplayObject.onMouseOverCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseovercapture, callback)
fun DisplayObject.onceMouseOverCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseovercapture, callback)
fun DisplayObject.offMouseOverCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseovercapture, callback)

fun DisplayObject.emitMouseUp(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseup, event)
fun DisplayObject.onMouseUp(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseup, callback)
fun DisplayObject.onceMouseUp(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseup, callback)
fun DisplayObject.offMouseUp(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseup, callback)

fun DisplayObject.emitMouseUpCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseupcapture, event)
fun DisplayObject.onMouseUpCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseupcapture, callback)
fun DisplayObject.onceMouseUpCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseupcapture, callback)
fun DisplayObject.offMouseUpCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseupcapture, callback)

fun DisplayObject.emitMouseUpOutside(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseupoutside, event)
fun DisplayObject.onMouseUpOutside(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseupoutside, callback)
fun DisplayObject.onceMouseUpOutside(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseupoutside, callback)
fun DisplayObject.offMouseUpOutside(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseupoutside, callback)

fun DisplayObject.emitMouseUpOutsideCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.mouseupoutsidecapture, event)
fun DisplayObject.onMouseUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.mouseupoutsidecapture, callback)
fun DisplayObject.onceMouseUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.mouseupoutsidecapture, callback)
fun DisplayObject.offMouseUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.mouseupoutsidecapture, callback)

fun DisplayObject.emitPointerCancel(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointercancel, event)
fun DisplayObject.onPointerCancel(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointercancel, callback)
fun DisplayObject.oncePointerCancel(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointercancel, callback)
fun DisplayObject.offPointerCancel(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointercancel, callback)

fun DisplayObject.emitPointerCancelCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointercancelcapture, event)
fun DisplayObject.onPointerCancelCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointercancelcapture, callback)
fun DisplayObject.oncePointerCancelCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointercancelcapture, callback)
fun DisplayObject.offPointerCancelCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointercancelcapture, callback)

fun DisplayObject.emitPointerDown(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerdown, event)
fun DisplayObject.onPointerDown(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerdown, callback)
fun DisplayObject.oncePointerDown(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerdown, callback)
fun DisplayObject.offPointerDown(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerdown, callback)

fun DisplayObject.emitPointerDownCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerdowncapture, event)
fun DisplayObject.onPointerDownCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerdowncapture, callback)
fun DisplayObject.oncePointerDownCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerdowncapture, callback)
fun DisplayObject.offPointerDownCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerdowncapture, callback)

fun DisplayObject.emitPointerEnter(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerenter, event)
fun DisplayObject.onPointerEnter(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerenter, callback)
fun DisplayObject.oncePointerEnter(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerenter, callback)
fun DisplayObject.offPointerEnter(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerenter, callback)

fun DisplayObject.emitPointerEnterCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerentercapture, event)
fun DisplayObject.onPointerEnterCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerentercapture, callback)
fun DisplayObject.oncePointerEnterCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerentercapture, callback)
fun DisplayObject.offPointerEnterCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerentercapture, callback)

fun DisplayObject.emitPointerLeave(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerleave, event)
fun DisplayObject.onPointerLeave(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerleave, callback)
fun DisplayObject.oncePointerLeave(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerleave, callback)
fun DisplayObject.offPointerLeave(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerleave, callback)

fun DisplayObject.emitPointerLeaveCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerleavecapture, event)
fun DisplayObject.onPointerLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerleavecapture, callback)
fun DisplayObject.oncePointerLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerleavecapture, callback)
fun DisplayObject.offPointerLeaveCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerleavecapture, callback)

fun DisplayObject.emitPointerMove(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointermove, event)
fun DisplayObject.onPointerMove(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointermove, callback)
fun DisplayObject.oncePointerMove(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointermove, callback)
fun DisplayObject.offPointerMove(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointermove, callback)

fun DisplayObject.emitPointerMoveCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointermovecapture, event)
fun DisplayObject.onPointerMoveCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointermovecapture, callback)
fun DisplayObject.oncePointerMoveCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointermovecapture, callback)
fun DisplayObject.offPointerMoveCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointermovecapture, callback)

fun DisplayObject.emitPointerOut(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerout, event)
fun DisplayObject.onPointerOut(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerout, callback)
fun DisplayObject.oncePointerOut(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerout, callback)
fun DisplayObject.offPointerOut(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerout, callback)

fun DisplayObject.emitPointerOutCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointeroutcapture, event)
fun DisplayObject.onPointerOutCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointeroutcapture, callback)
fun DisplayObject.oncePointerOutCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointeroutcapture, callback)
fun DisplayObject.offPointerOutCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointeroutcapture, callback)

fun DisplayObject.emitPointerOver(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerover, event)
fun DisplayObject.onPointerOver(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerover, callback)
fun DisplayObject.oncePointerOver(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerover, callback)
fun DisplayObject.offPointerOver(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerover, callback)

fun DisplayObject.emitPointerOverCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerovercapture, event)
fun DisplayObject.onPointerOverCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerovercapture, callback)
fun DisplayObject.oncePointerOverCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerovercapture, callback)
fun DisplayObject.offPointerOverCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerovercapture, callback)

fun DisplayObject.emitPointerTap(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointertap, event)
fun DisplayObject.onPointerTap(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointertap, callback)
fun DisplayObject.oncePointerTap(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointertap, callback)
fun DisplayObject.offPointerTap(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointertap, callback)

fun DisplayObject.emitPointerTapCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointertapcapture, event)
fun DisplayObject.onPointerTapCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointertapcapture, callback)
fun DisplayObject.oncePointerTapCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointertapcapture, callback)
fun DisplayObject.offPointerTapCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointertapcapture, callback)

fun DisplayObject.emitPointerUp(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerup, event)
fun DisplayObject.onPointerUp(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerup, callback)
fun DisplayObject.oncePointerUp(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerup, callback)
fun DisplayObject.offPointerUp(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerup, callback)

fun DisplayObject.emitPointerUpCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerupcapture, event)
fun DisplayObject.onPointerUpCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerupcapture, callback)
fun DisplayObject.oncePointerUpCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerupcapture, callback)
fun DisplayObject.offPointerUpCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerupcapture, callback)

fun DisplayObject.emitPointerUpOutside(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerupoutside, event)
fun DisplayObject.onPointerUpOutside(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerupoutside, callback)
fun DisplayObject.oncePointerUpOutside(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerupoutside, callback)
fun DisplayObject.offPointerUpOutside(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerupoutside, callback)

fun DisplayObject.emitPointerUpOutsideCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.pointerupoutsidecapture, event)
fun DisplayObject.onPointerUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.pointerupoutsidecapture, callback)
fun DisplayObject.oncePointerUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.pointerupoutsidecapture, callback)
fun DisplayObject.offPointerUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.pointerupoutsidecapture, callback)

fun DisplayObject.emitRemoved(container: Container) = emit(DisplayObjectEvents.removed, container)
fun DisplayObject.onRemoved(callback: (Container) -> Unit) = on(DisplayObjectEvents.removed, callback)
fun DisplayObject.onceRemoved(callback: (Container) -> Unit) = once(DisplayObjectEvents.removed, callback)
fun DisplayObject.offRemoved(callback: (Container) -> Unit) = off(DisplayObjectEvents.removed, callback)

fun DisplayObject.emitRemovedFrom(child: DisplayObject, container: Container, index: Int) = emit(DisplayObjectEvents.removedFrom, child, container, index)
fun DisplayObject.onRemovedFrom(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = on(DisplayObjectEvents.removedFrom, callback)
fun DisplayObject.onceRemovedFrom(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = once(DisplayObjectEvents.removedFrom, callback)
fun DisplayObject.offRemovedFrom(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = off(DisplayObjectEvents.removedFrom, callback)

fun DisplayObject.emitRightClick(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightclick, event)
fun DisplayObject.onRightClick(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightclick, callback)
fun DisplayObject.onceRightClick(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightclick, callback)
fun DisplayObject.offRightClick(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightclick, callback)

fun DisplayObject.emitRightClickCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightclickcapture, event)
fun DisplayObject.onRightClickCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightclickcapture, callback)
fun DisplayObject.onceRightClickCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightclickcapture, callback)
fun DisplayObject.offRightClickCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightclickcapture, callback)

fun DisplayObject.emitRightDown(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightdown, event)
fun DisplayObject.onRightDown(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightdown, callback)
fun DisplayObject.onceRightDown(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightdown, callback)
fun DisplayObject.offRightDown(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightdown, callback)

fun DisplayObject.emitRightDownCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightdowncapture, event)
fun DisplayObject.onRightDownCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightdowncapture, callback)
fun DisplayObject.onceRightDownCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightdowncapture, callback)
fun DisplayObject.offRightDownCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightdowncapture, callback)

fun DisplayObject.emitRightUp(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightup, event)
fun DisplayObject.onRightUp(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightup, callback)
fun DisplayObject.onceRightUp(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightup, callback)
fun DisplayObject.offRightUp(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightup, callback)

fun DisplayObject.emitRightUpCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightupcapture, event)
fun DisplayObject.onRightUpCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightupcapture, callback)
fun DisplayObject.onceRightUpCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightupcapture, callback)
fun DisplayObject.offRightUpCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightupcapture, callback)

fun DisplayObject.emitRightUpOutside(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightupoutside, event)
fun DisplayObject.onRightUpOutside(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightupoutside, callback)
fun DisplayObject.onceRightUpOutside(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightupoutside, callback)
fun DisplayObject.offRightUpOutside(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightupoutside, callback)

fun DisplayObject.emitRightUpOutsideCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.rightupoutsidecapture, event)
fun DisplayObject.onRightUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.rightupoutsidecapture, callback)
fun DisplayObject.onceRightUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.rightupoutsidecapture, callback)
fun DisplayObject.offRightUpOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.rightupoutsidecapture, callback)

fun DisplayObject.emitTap(event: FederatedPointerEvent) = emit(DisplayObjectEvents.tap, event)
fun DisplayObject.onTap(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.tap, callback)
fun DisplayObject.onceTap(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.tap, callback)
fun DisplayObject.offTap(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.tap, callback)

fun DisplayObject.emitTapCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.tapcapture, event)
fun DisplayObject.onTapCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.tapcapture, callback)
fun DisplayObject.onceTapCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.tapcapture, callback)
fun DisplayObject.offTapCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.tapcapture, callback)

fun DisplayObject.emitTouchCancel(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchcancel, event)
fun DisplayObject.onTouchCancel(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchcancel, callback)
fun DisplayObject.onceTouchCancel(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchcancel, callback)
fun DisplayObject.offTouchCancel(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchcancel, callback)

fun DisplayObject.emitTouchCancelCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchcancelcapture, event)
fun DisplayObject.onTouchCancelCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchcancelcapture, callback)
fun DisplayObject.onceTouchCancelCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchcancelcapture, callback)
fun DisplayObject.offTouchCancelCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchcancelcapture, callback)

fun DisplayObject.emitTouchEnd(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchend, event)
fun DisplayObject.onTouchEnd(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchend, callback)
fun DisplayObject.onceTouchEnd(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchend, callback)
fun DisplayObject.offTouchEnd(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchend, callback)

fun DisplayObject.emitTouchEndCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchendcapture, event)
fun DisplayObject.onTouchEndCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchendcapture, callback)
fun DisplayObject.onceTouchEndCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchendcapture, callback)
fun DisplayObject.offTouchEndCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchendcapture, callback)

fun DisplayObject.emitTouchEndOutside(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchendoutside, event)
fun DisplayObject.onTouchEndOutside(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchendoutside, callback)
fun DisplayObject.onceTouchEndOutside(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchendoutside, callback)
fun DisplayObject.offTouchEndOutside(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchendoutside, callback)

fun DisplayObject.emitTouchEndOutsideCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchendoutsidecapture, event)
fun DisplayObject.onTouchEndOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchendoutsidecapture, callback)
fun DisplayObject.onceTouchEndOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchendoutsidecapture, callback)
fun DisplayObject.offTouchEndOutsideCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchendoutsidecapture, callback)

fun DisplayObject.emitTouchMove(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchmove, event)
fun DisplayObject.onTouchMove(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchmove, callback)
fun DisplayObject.onceTouchMove(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchmove, callback)
fun DisplayObject.offTouchMove(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchmove, callback)

fun DisplayObject.emitTouchMoveCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchmovecapture, event)
fun DisplayObject.onTouchMoveCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchmovecapture, callback)
fun DisplayObject.onceTouchMoveCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchmovecapture, callback)
fun DisplayObject.offTouchMoveCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchmovecapture, callback)

fun DisplayObject.emitTouchStart(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchstart, event)
fun DisplayObject.onTouchStart(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchstart, callback)
fun DisplayObject.onceTouchStart(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchstart, callback)
fun DisplayObject.offTouchStart(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchstart, callback)

fun DisplayObject.emitTouchStartCapture(event: FederatedPointerEvent) = emit(DisplayObjectEvents.touchstartcapture, event)
fun DisplayObject.onTouchStartCapture(callback: (FederatedPointerEvent) -> Unit) = on(DisplayObjectEvents.touchstartcapture, callback)
fun DisplayObject.onceTouchStartCapture(callback: (FederatedPointerEvent) -> Unit) = once(DisplayObjectEvents.touchstartcapture, callback)
fun DisplayObject.offTouchStartCapture(callback: (FederatedPointerEvent) -> Unit) = off(DisplayObjectEvents.touchstartcapture, callback)

fun DisplayObject.emitWheel(event: FederatedWheelEvent) = emit(DisplayObjectEvents.wheel, event)
fun DisplayObject.onWheel(callback: (FederatedWheelEvent) -> Unit) = on(DisplayObjectEvents.wheel, callback)
fun DisplayObject.onceWheel(callback: (FederatedWheelEvent) -> Unit) = once(DisplayObjectEvents.wheel, callback)
fun DisplayObject.offWheel(callback: (FederatedWheelEvent) -> Unit) = off(DisplayObjectEvents.wheel, callback)

fun DisplayObject.emitWheelCapture(event: FederatedWheelEvent) = emit(DisplayObjectEvents.wheelcapture, event)
fun DisplayObject.onWheelCapture(callback: (FederatedWheelEvent) -> Unit) = on(DisplayObjectEvents.wheelcapture, callback)
fun DisplayObject.onceWheelCapture(callback: (FederatedWheelEvent) -> Unit) = once(DisplayObjectEvents.wheelcapture, callback)
fun DisplayObject.offWheelCapture(callback: (FederatedWheelEvent) -> Unit) = off(DisplayObjectEvents.wheelcapture, callback)

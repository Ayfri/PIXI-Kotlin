package pixi.externals.extensions

import kotlinx.browser.window
import pixi.externals.Tuple
import pixi.typings.ListenerFn
import pixi.typings.app.Application
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.event.FederatedPointerEvent
import pixi.typings.event.FederatedWheelEvent

@Suppress("ClassName")
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

fun DisplayObject.addToApplication(application: Application) {
	application.stage.addChild(this)
}

fun DisplayObject.collidesWith(other: DisplayObject) = getBounds().collidesWith(other.getBounds())

fun DisplayObject.hide() {
	this.visible = false
}

inline fun <reified T : DisplayObjectEvents<*>> DisplayObject.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments as Array<Any?>)
fun <T : Any> DisplayObject.on(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> DisplayObject.once(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> DisplayObject.off(event: DisplayObjectEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback as ListenerFn, null)

fun DisplayObject.removeFromApplication(application: Application) {
	application.stage.removeChild(this)
}

fun DisplayObject.setPositionFromApplication(application: Application, factorX: Double, factorY: Double) {
	x = application.renderer.width * factorX
	y = application.renderer.height * factorY
}


fun DisplayObject.setPositionFromWindow(factorX: Double, factorY: Double) {
	x = window.innerWidth * factorX
	y = window.innerHeight * factorY
}

fun DisplayObject.show() {
	this.visible = true
}

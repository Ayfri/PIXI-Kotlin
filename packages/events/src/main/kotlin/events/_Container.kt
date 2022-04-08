package events

import pixi.externals.Tuple
import pixi.typings.ListenerFn
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

sealed interface ContainerEvents<T : Any> : DisplayObjectEvents<T> {
	object childAdded : ContainerEvents<Tuple<DisplayObject, Container, Int>>
}

fun <T : ContainerEvents<out E>, E> Container.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> Container.on(event: ContainerEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Container.once(event: ContainerEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Container.off(event: ContainerEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun Container.emitChildAdded(child: DisplayObject, container: Container, index: Int) = emit(ContainerEvents.childAdded, child, container, index)
fun Container.onChildAdded(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = on(ContainerEvents.childAdded, callback)
fun Container.onceChildAdded(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = once(ContainerEvents.childAdded, callback)
fun Container.offChildAdded(callback: (Tuple<DisplayObject, Container, Int>) -> Unit) = off(ContainerEvents.childAdded, callback)

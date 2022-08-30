package events

import pixi.externals.Tuple
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

sealed interface ContainerEvents<T : Any> : DisplayObjectEvents<T> {
	object childAdded : ContainerEvents<Tuple<DisplayObject, Container<*>, Int>>
}

fun Container<*>.emitChildAdded(child: DisplayObject, container: Container<*>, index: Int) = emit(ContainerEvents.childAdded, child, container, index)
fun <T : DisplayObject> Container<T>.onChildAdded(callback: (Tuple<DisplayObject, Container<T>, Int>) -> Unit) = on(ContainerEvents.childAdded, callback = callback.unsafeCast<(Any) -> Unit>())
fun <T : DisplayObject> Container<T>.onceChildAdded(callback: (Tuple<DisplayObject, Container<T>, Int>) -> Unit) = once(ContainerEvents.childAdded, callback = callback.unsafeCast<(Any) -> Unit>())
fun <T : DisplayObject> Container<T>.offChildAdded(callback: (Tuple<DisplayObject, Container<T>, Int>) -> Unit) = off(ContainerEvents.childAdded, callback = callback.unsafeCast<(Any) -> Unit>())

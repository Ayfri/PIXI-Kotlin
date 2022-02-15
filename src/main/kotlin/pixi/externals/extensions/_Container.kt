package pixi.externals.extensions

import pixi.externals.Tuple
import pixi.typings.ListenerFn
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

sealed interface ContainerEvents<T : Any> : DisplayObjectEvents<T> {
	object childAdded : ContainerEvents<Tuple<DisplayObject, Container, Int>>
}

inline fun <reified T : ContainerEvents<*>> Container.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments as Array<Any?>)
fun <T : Any> Container.on(event: ContainerEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> Container.once(event: ContainerEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> Container.off(event: ContainerEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback as ListenerFn, null)

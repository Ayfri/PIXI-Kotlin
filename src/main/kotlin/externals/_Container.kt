package externals

import pixi.typings.ListenerFn
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

sealed interface ContainerEvents<T> : DisplayObjectEvents<T> {
	object childAdded : ContainerEvents<Tuple<DisplayObject, Container, Int>>
}

inline fun <reified T : ContainerEvents<*>> Container.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments as Array<Any?>)
inline fun <reified T : ContainerEvents<*>> Container.on(noinline callback: (T) -> Unit) = on(T::class.simpleName!!, callback as ListenerFn, null)
inline fun <reified T : ContainerEvents<*>> Container.once(noinline callback: (T) -> Unit) = once(T::class.simpleName!!, callback as ListenerFn, null)
inline fun <reified T : ContainerEvents<*>> Container.off(noinline callback: (T) -> Unit) = off(T::class.simpleName!!, callback as ListenerFn, null)

fun a(container: Container) {
	container.on<ContainerEvents.childAdded> {
		println(it)
	}
}

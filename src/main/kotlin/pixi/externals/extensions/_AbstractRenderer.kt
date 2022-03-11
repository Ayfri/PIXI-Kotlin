package pixi.externals.extensions

import pixi.typings.ListenerFn
import pixi.typings.core.AbstractRenderer

sealed interface AbstractRendererEvents<T> {
	object resize : AbstractRendererEvents<Pair<Int, Int>>
}

inline fun <reified T : AbstractRendererEvents<*>> AbstractRenderer.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> AbstractRenderer.on(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> AbstractRenderer.once(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> AbstractRenderer.off(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)


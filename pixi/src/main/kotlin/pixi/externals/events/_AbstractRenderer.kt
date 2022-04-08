package pixi.externals.events

import pixi.typings.ListenerFn
import pixi.typings.core.AbstractRenderer

sealed interface AbstractRendererEvents<T> {
	object resize : AbstractRendererEvents<Pair<Int, Int>>
}

fun <T : AbstractRendererEvents<out E>, E> AbstractRenderer.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> AbstractRenderer.on(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> AbstractRenderer.once(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> AbstractRenderer.off(event: AbstractRendererEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun AbstractRenderer.onResize(callback: (Pair<Int, Int>) -> Unit) = on(AbstractRendererEvents.resize, callback)
fun AbstractRenderer.onceResize(callback: (Pair<Int, Int>) -> Unit) = once(AbstractRendererEvents.resize, callback)
fun AbstractRenderer.offResize(callback: (Pair<Int, Int>) -> Unit) = off(AbstractRendererEvents.resize, callback)

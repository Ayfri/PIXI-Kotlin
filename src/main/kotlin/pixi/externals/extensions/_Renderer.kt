package pixi.externals.extensions

import org.khronos.webgl.WebGLRenderingContext
import pixi.typings.ListenerFn
import pixi.typings.core.Renderer

sealed interface RendererEvents<T> : AbstractRendererEvents<T> {
	object context : AbstractRendererEvents<WebGLRenderingContext>
	object postrender : AbstractRendererEvents<Nothing>
	object prerender : AbstractRendererEvents<Nothing>
	object resize : AbstractRendererEvents<Pair<Int, Int>>
}

inline fun <reified T : RendererEvents<*>> Renderer.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> Renderer.on(event: RendererEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Renderer.once(event: RendererEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Renderer.off(event: RendererEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)


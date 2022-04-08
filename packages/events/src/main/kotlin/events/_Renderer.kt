package events

import org.khronos.webgl.WebGLRenderingContext
import pixi.typings.ListenerFn
import pixi.typings.core.Renderer

sealed interface RendererEvents<T> : AbstractRendererEvents<T> {
	object context : AbstractRendererEvents<WebGLRenderingContext>
	object postrender : AbstractRendererEvents<Nothing>
	object prerender : AbstractRendererEvents<Nothing>
	object resize : AbstractRendererEvents<Pair<Int, Int>>
}

fun <T : RendererEvents<out E>, E> Renderer.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> Renderer.on(event: RendererEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Renderer.once(event: RendererEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Renderer.off(event: RendererEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun Renderer.emitContext(context: WebGLRenderingContext) = emit(RendererEvents.context, context)
fun Renderer.onContext(callback: (WebGLRenderingContext) -> Unit) = on(RendererEvents.context, callback)
fun Renderer.onceContext(callback: (WebGLRenderingContext) -> Unit) = once(RendererEvents.context, callback)
fun Renderer.offContext(callback: (WebGLRenderingContext) -> Unit) = off(RendererEvents.context, callback)

fun Renderer.emitPostRender() = emit(RendererEvents.postrender)
fun Renderer.onPostRender(callback: (Nothing) -> Unit) = on(RendererEvents.postrender, callback)
fun Renderer.oncePostRender(callback: (Nothing) -> Unit) = once(RendererEvents.postrender, callback)
fun Renderer.offPostRender(callback: (Nothing) -> Unit) = off(RendererEvents.postrender, callback)

fun Renderer.emitPreRender() = emit(RendererEvents.prerender)
fun Renderer.onPreRender(callback: (Nothing) -> Unit) = on(RendererEvents.prerender, callback)
fun Renderer.oncePreRender(callback: (Nothing) -> Unit) = once(RendererEvents.prerender, callback)
fun Renderer.offPreRender(callback: (Nothing) -> Unit) = off(RendererEvents.prerender, callback)

fun Renderer.emitResize(width: Int, height: Int) = emit(RendererEvents.resize, width, height)
fun Renderer.onResize(callback: (Pair<Int, Int>) -> Unit) = on(RendererEvents.resize, callback)
fun Renderer.onceResize(callback: (Pair<Int, Int>) -> Unit) = once(RendererEvents.resize, callback)
fun Renderer.offResize(callback: (Pair<Int, Int>) -> Unit) = off(RendererEvents.resize, callback)

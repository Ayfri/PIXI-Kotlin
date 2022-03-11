package pixi.externals.extensions

import org.w3c.dom.ErrorEvent
import pixi.typings.ListenerFn
import pixi.typings.core.BaseRenderTexture
import pixi.typings.core.BaseTexture
import pixi.typings.core.Resource

sealed interface BaseRenderTextureEvents<T> : BaseTextureEvents<T> {
	object error : BaseRenderTextureEvents<Pair<BaseTexture<Resource, Any?>, ErrorEvent>>
	object loaded : BaseRenderTextureEvents<BaseTexture<Resource, Any?>>
}

inline fun <reified T : BaseRenderTextureEvents<*>> BaseRenderTexture.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> BaseRenderTexture.on(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseRenderTexture.once(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseRenderTexture.off(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)


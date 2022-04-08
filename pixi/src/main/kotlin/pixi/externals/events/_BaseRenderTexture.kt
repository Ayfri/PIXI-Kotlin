package pixi.externals.events

import org.w3c.dom.ErrorEvent
import pixi.typings.ListenerFn
import pixi.typings.core.BaseRenderTexture
import pixi.typings.core.BaseTexture
import pixi.typings.core.Resource

sealed interface BaseRenderTextureEvents<T> : BaseTextureEvents<T> {
	object error : BaseRenderTextureEvents<Pair<BaseTexture<Resource, Any?>, ErrorEvent>>
	object loaded : BaseRenderTextureEvents<BaseTexture<Resource, Any?>>
}

fun <T : BaseRenderTextureEvents<out E>, E> BaseRenderTexture.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> BaseRenderTexture.on(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseRenderTexture.once(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseRenderTexture.off(event: BaseRenderTextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun BaseRenderTexture.emitError(texture: BaseTexture<Resource, Any?>, error: ErrorEvent) = emit(BaseRenderTextureEvents.error, texture to error)
fun BaseRenderTexture.onError(callback: (Pair<BaseTexture<Resource, Any?>, ErrorEvent>) -> Unit) = on(BaseRenderTextureEvents.error, callback)
fun BaseRenderTexture.onceError(callback: (Pair<BaseTexture<Resource, Any?>, ErrorEvent>) -> Unit) = once(BaseRenderTextureEvents.error, callback)
fun BaseRenderTexture.offError(callback: (Pair<BaseTexture<Resource, Any?>, ErrorEvent>) -> Unit) = off(BaseRenderTextureEvents.error, callback)

fun BaseRenderTexture.emitLoaded(texture: BaseTexture<Resource, Any?>) = emit(BaseRenderTextureEvents.loaded, texture)
fun BaseRenderTexture.onLoaded(callback: (BaseTexture<Resource, Any?>) -> Unit) = on(BaseRenderTextureEvents.loaded, callback)
fun BaseRenderTexture.onceLoaded(callback: (BaseTexture<Resource, Any?>) -> Unit) = once(BaseRenderTextureEvents.loaded, callback)
fun BaseRenderTexture.offLoaded(callback: (BaseTexture<Resource, Any?>) -> Unit) = off(BaseRenderTextureEvents.loaded, callback)

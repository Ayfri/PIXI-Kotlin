package events

import org.w3c.dom.ErrorEvent
import pixi.typings.ListenerFn
import pixi.typings.core.BaseTexture
import pixi.typings.core.Resource

sealed interface BaseTextureEvents<T> {
	object dispose : BaseTextureEvents<BaseTexture<Resource, *>>
	object error : BaseTextureEvents<Pair<BaseTexture<Resource, *>, ErrorEvent>>
	object loaded : BaseTextureEvents<BaseTexture<Resource, *>>
	object update : BaseTextureEvents<BaseTexture<Resource, *>>
}

fun <T : BaseTextureEvents<out E>, E> BaseTexture<*, *>.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> BaseTexture<*, *>.on(event: BaseTextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseTexture<*, *>.once(event: BaseTextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> BaseTexture<*, *>.off(event: BaseTextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)

fun BaseTexture<*, *>.emitDispose(texture: BaseTexture<Resource, *>) = emit(BaseTextureEvents.dispose, texture)
fun BaseTexture<*, *>.onDispose(callback: (BaseTexture<Resource, *>) -> Unit) = on(BaseTextureEvents.dispose, callback)
fun BaseTexture<*, *>.onceDispose(callback: (BaseTexture<Resource, *>) -> Unit) = once(BaseTextureEvents.dispose, callback)
fun BaseTexture<*, *>.offDispose(callback: (BaseTexture<Resource, *>) -> Unit) = off(BaseTextureEvents.dispose, callback)

fun BaseTexture<*, *>.emitError(texture: BaseTexture<Resource, *>, error: ErrorEvent) = emit(BaseTextureEvents.error, texture to error)
fun BaseTexture<*, *>.onError(callback: (Pair<BaseTexture<Resource, *>, ErrorEvent>) -> Unit) = on(BaseTextureEvents.error, callback)
fun BaseTexture<*, *>.onceError(callback: (Pair<BaseTexture<Resource, *>, ErrorEvent>) -> Unit) = once(BaseTextureEvents.error, callback)
fun BaseTexture<*, *>.offError(callback: (Pair<BaseTexture<Resource, *>, ErrorEvent>) -> Unit) = off(BaseTextureEvents.error, callback)

fun BaseTexture<*, *>.emitLoaded(texture: BaseTexture<Resource, *>) = emit(BaseTextureEvents.loaded, texture)
fun BaseTexture<*, *>.onLoaded(callback: (BaseTexture<Resource, *>) -> Unit) = on(BaseTextureEvents.loaded, callback)
fun BaseTexture<*, *>.onceLoaded(callback: (BaseTexture<Resource, *>) -> Unit) = once(BaseTextureEvents.loaded, callback)
fun BaseTexture<*, *>.offLoaded(callback: (BaseTexture<Resource, *>) -> Unit) = off(BaseTextureEvents.loaded, callback)

fun BaseTexture<*, *>.emitUpdate(texture: BaseTexture<Resource, *>) = emit(BaseTextureEvents.update, texture)
fun BaseTexture<*, *>.onUpdate(callback: (BaseTexture<Resource, *>) -> Unit) = on(BaseTextureEvents.update, callback)
fun BaseTexture<*, *>.onceUpdate(callback: (BaseTexture<Resource, *>) -> Unit) = once(BaseTextureEvents.update, callback)
fun BaseTexture<*, *>.offUpdate(callback: (BaseTexture<Resource, *>) -> Unit) = off(BaseTextureEvents.update, callback)

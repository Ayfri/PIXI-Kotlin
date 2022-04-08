package events

import org.w3c.dom.ErrorEvent
import pixi.typings.ListenerFn
import pixi.typings.core.Resource
import pixi.typings.core.Texture

sealed interface TextureEvents<T> {
	object dispose : TextureEvents<Texture<Resource>>
	object error : TextureEvents<Pair<Texture<Resource>, ErrorEvent>>
	object loaded : TextureEvents<Texture<Resource>>
	object update : TextureEvents<Texture<Resource>>
}

fun <T : TextureEvents<out E>, E> Texture<*>.emit(event: T, vararg arguments: E) = emit(event::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> Texture<*>.on(event: TextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Texture<*>.once(event: TextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Texture<*>.off(event: TextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)


fun Texture<*>.emitDispose(texture: Texture<Resource>) = emit(TextureEvents.dispose, texture)
fun Texture<*>.onDispose(callback: (Texture<Resource>) -> Unit) = on(TextureEvents.dispose, callback)
fun Texture<*>.onceDispose(callback: (Texture<Resource>) -> Unit) = once(TextureEvents.dispose, callback)
fun Texture<*>.offDispose(callback: (Texture<Resource>) -> Unit) = off(TextureEvents.dispose, callback)

fun Texture<*>.emitError(texture: Texture<Resource>, error: ErrorEvent) = emit(TextureEvents.error, texture to error)
fun Texture<*>.onError(callback: (Pair<Texture<Resource>, ErrorEvent>) -> Unit) = on(TextureEvents.error, callback)
fun Texture<*>.onceError(callback: (Pair<Texture<Resource>, ErrorEvent>) -> Unit) = once(TextureEvents.error, callback)
fun Texture<*>.offError(callback: (Pair<Texture<Resource>, ErrorEvent>) -> Unit) = off(TextureEvents.error, callback)

fun Texture<*>.emitLoaded(texture: Texture<Resource>) = emit(TextureEvents.loaded, texture)
fun Texture<*>.onLoaded(callback: (Texture<Resource>) -> Unit) = on(TextureEvents.loaded, callback)
fun Texture<*>.onceLoaded(callback: (Texture<Resource>) -> Unit) = once(TextureEvents.loaded, callback)
fun Texture<*>.offLoaded(callback: (Texture<Resource>) -> Unit) = off(TextureEvents.loaded, callback)

fun Texture<*>.emitUpdate(texture: Texture<Resource>) = emit(TextureEvents.update, texture)
fun Texture<*>.onUpdate(callback: (Texture<Resource>) -> Unit) = on(TextureEvents.update, callback)
fun Texture<*>.onceUpdate(callback: (Texture<Resource>) -> Unit) = once(TextureEvents.update, callback)
fun Texture<*>.offUpdate(callback: (Texture<Resource>) -> Unit) = off(TextureEvents.update, callback)

package pixi.externals.extensions

import pixi.typings.ListenerFn
import pixi.typings.core.Texture

sealed interface TextureEvents<T> : BaseTextureEvents<T>

inline fun <reified T : TextureEvents<*>> Texture<*>.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments.unsafeCast<Array<Any?>>())
fun <T : Any> Texture<*>.on(event: TextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Texture<*>.once(event: TextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)
fun <T : Any> Texture<*>.off(event: TextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback.unsafeCast<ListenerFn>(), null)


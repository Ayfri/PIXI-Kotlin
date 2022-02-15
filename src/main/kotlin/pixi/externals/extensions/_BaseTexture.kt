package pixi.externals.extensions

import org.w3c.dom.ErrorEvent
import pixi.typings.ListenerFn
import pixi.typings.core.BaseTexture
import pixi.typings.core.Resource

sealed interface BaseTextureEvents<T> {
	object dispose : BaseTextureEvents<BaseTexture<Resource, Any?>>
	object error : BaseTextureEvents<Pair<BaseTexture<Resource, Any?>, ErrorEvent>>
	object loaded : BaseTextureEvents<BaseTexture<Resource, Any?>>
	object update : BaseTextureEvents<BaseTexture<Resource, Any?>>
}

inline fun <reified T : BaseTextureEvents<*>> BaseTexture<*, *>.emit(vararg arguments: T) = emit(T::class.simpleName!!, arguments as Array<Any?>)
fun <T : Any> BaseTexture<*, *>.on(event: BaseTextureEvents<T>, callback: (T) -> Unit) = on(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> BaseTexture<*, *>.once(event: BaseTextureEvents<T>, callback: (T) -> Unit) = once(event::class.simpleName!!, callback as ListenerFn, null)
fun <T : Any> BaseTexture<*, *>.off(event: BaseTextureEvents<T>, callback: (T) -> Unit) = off(event::class.simpleName!!, callback as ListenerFn, null)

package pixi.typings.loaders

import pixi.typings.VarArgFun
import pixi.typings.app.Application

inline var Application.loader
	get() = asDynamic()?.loader as Loader? ?: Loader.shared
	set(value) {
		asDynamic()?.loader = value
	}

inline fun Loader.add() = asDynamic().add() as Unit
inline fun Loader.use(resource: LoaderResource, noinline next: VarArgFun<dynamic, Unit>) = asDynamic().use(resource, next) as Unit

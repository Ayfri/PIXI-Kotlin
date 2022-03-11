package pixi.typings.loaders

import pixi.typings.app.IApplicationOptions
import pixi.typings.core.Resource
import pixi.typings.core.Texture

inline var ILoaderResource.texture
	get() = asDynamic().texture.unsafeCast<Texture<Resource>?>()
	set(value) {
		asDynamic().texture = value
	}

inline var IApplicationOptions.sharedLoader
	get() = asDynamic().sharedLoader as? Boolean
	set(value) {
		asDynamic().sharedLoader = value
	}

package typings.loaders

import typings.app.IApplicationOptions
import typings.core.Resource
import typings.core.Texture

inline var ILoaderResource.texture: Texture<Resource>
	get() = asDynamic().texture as Texture<Resource>
	set(value) {
		asDynamic().texture = value
	}


inline var IApplicationOptions.sharedLoader: Boolean?
	get() = asDynamic().sharedLoader as? Boolean
	set(value) {
		asDynamic().sharedLoader = value
	}

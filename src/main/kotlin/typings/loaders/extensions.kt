package typings.loaders

import typings.app.IApplicationOptions
import typings.core.Resource
import typings.core.Texture

inline var ILoaderResource.texture
	get() = asDynamic().texture as Texture<Resource>
	set(value) {
		asDynamic().texture = value
	}

inline var IApplicationOptions.sharedLoader
	get() = asDynamic().sharedLoader as? Boolean
	set(value) {
		asDynamic().sharedLoader = value
	}

package typings.spritesheet

import typings.Indexed
import typings.core.Resource
import typings.core.Texture
import typings.loaders.LoaderResource

inline var LoaderResource.spritesheet
	get() = asDynamic().spritesheet as Spritesheet?
	set(value) {
		asDynamic().spritesheet = value
	}

inline var LoaderResource.textures
	get() = asDynamic().textures as Indexed<String, Texture<Resource>>?
	set(value) {
		asDynamic().textures = value
	}

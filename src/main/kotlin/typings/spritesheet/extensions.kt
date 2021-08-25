package typings.spritesheet

import typings.Indexed
import typings.core.Resource
import typings.core.Texture
import typings.loaders.LoaderResource

inline var LoaderResource.spritesheet: Spritesheet
	get() = asDynamic().spritesheet as Spritesheet
	set(value) {
		asDynamic().spritesheet = value
	}

inline var LoaderResource.textures: Indexed<String, Texture<Resource>>
	get() = asDynamic().textures as Indexed<String, Texture<Resource>>
	set(value) {
		asDynamic().textures = value
	}
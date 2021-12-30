package pixi.typings.spritesheet

import pixi.typings.Object
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.loaders.LoaderResource

inline var LoaderResource.spritesheet
	get() = asDynamic().spritesheet as Spritesheet?
	set(value) {
		asDynamic().spritesheet = value
	}

inline var LoaderResource.textures
	get() = asDynamic().textures as Object<String, Texture<Resource>>?
	set(value) {
		asDynamic().textures = value
	}

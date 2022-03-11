package pixi.typings.spritesheet

import pixi.typings.Object
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.loaders.LoaderResource

inline var LoaderResource.spritesheet
	get() = asDynamic().spritesheet.unsafeCast<Spritesheet?>()
	set(value) {
		asDynamic().spritesheet = value
	}

inline var LoaderResource.textures
	get() = asDynamic().textures.unsafeCast < Object<String>(), Texture<Resource>>?
set(value ) {
	asDynamic().textures = value
}

package pixi.externals

import pixi.typings.core.CanvasResource
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.sprite.Sprite

var Sprite.color: Color
	get() = this.tint
	set(value) {
		this.tint = value
	}

fun Sprite(texture: Texture<CanvasResource>) = Sprite(texture as Texture<Resource>)

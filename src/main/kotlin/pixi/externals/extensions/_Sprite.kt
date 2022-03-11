package pixi.externals.extensions

import pixi.externals.Color
import pixi.typings.core.CanvasResource
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.math.Rectangle
import pixi.typings.sprite.Sprite

var Sprite.color: Color
	get() = this.tint
	set(value) {
		this.tint = value
	}

val Sprite.hitBox: Rectangle
	get() = getBounds()

fun Sprite(texture: Texture<CanvasResource>) = Sprite(texture.unsafeCast<Texture<Resource>>())

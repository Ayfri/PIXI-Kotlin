package pixi.externals

import pixi.typings.sprite.Sprite

var Sprite.color: Color
	get() = this.tint
	set(value) {
		this.tint = value
	}

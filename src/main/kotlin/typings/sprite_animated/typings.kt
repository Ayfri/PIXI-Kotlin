@file:JsModule("@pixi/sprite-animated")

package typings.sprite_animated

import typings.Number
import typings.core.Resource
import typings.core.Texture
import typings.display.IDestroyOptions
import typings.sprite.Sprite

open external class AnimatedSprite(textures: Array<Texture<Resource>>, autoUpdate: Boolean = definedExternally) : Sprite {
	constructor(textures: Array<FrameObject>, autoUpdate: Boolean = definedExternally)
	
	open var animationSpeed: Number
	open var loop: Boolean
	open var updateAnchor: Boolean
	open var onComplete: (() -> Unit)?
	open var onFrameChange: ((currentFrame: Number) -> Unit)?
	open var onLoop: (() -> Unit)?
	
	open val totalFrames: Number
	open var textures: dynamic /* Array<Texture> | Array<FrameObject> */
	open val currentFrame: Number
	open val playing: Boolean
	open var autoUpdate: Boolean
	
	open fun stop()
	open fun play()
	open fun gotoAndStop(frameNumber: Number)
	open fun gotoAndPlay(frameNumber: Number)
	open fun update(deltaTime: Number)
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		fun frameFrames(frames: Array<String>): AnimatedSprite
		fun frameImages(images: Array<String>): AnimatedSprite
	}
}

external interface FrameObject {
	var texture: Texture<Resource>
	var time: Number
}

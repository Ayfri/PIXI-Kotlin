@file:JsModule("@pixi/sprite-animated")

package pixi.typings.sprite_animated

import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.IDestroyOptions
import pixi.typings.sprite.Sprite

open external class AnimatedSprite(textures: Array<Texture<Resource>>, autoUpdate: Boolean = definedExternally) : Sprite {
	constructor(textures: Array<FrameObject>, autoUpdate: Boolean = definedExternally)
	
	open var animationSpeed: Double
	open var loop: Boolean
	open var updateAnchor: Boolean
	open var onComplete: (() -> Unit)?
	open var onFrameChange: ((currentFrame: Int) -> Unit)?
	open var onLoop: (() -> Unit)?
	
	open val totalFrames: Int
	open var textures: dynamic /* Array<Texture> | Array<FrameObject> */
	open val currentFrame: Int
	open val playing: Boolean
	open var autoUpdate: Boolean
	
	open fun stop()
	open fun play()
	open fun gotoAndStop(frameNumber: Int)
	open fun gotoAndPlay(frameNumber: Int)
	open fun update(deltaTime: Int)
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		fun frameFrames(frames: Array<String>): AnimatedSprite
		fun frameImages(images: Array<String>): AnimatedSprite
	}
}

external interface FrameObject {
	var texture: Texture<Resource>
	var time: Int
}

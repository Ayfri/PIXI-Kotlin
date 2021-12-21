@file:JsModule("@pixi/sprite")

package typings.sprite

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.ImageBitmap
import typings.Number
import typings.constants.BLEND_MODES
import typings.core.BaseTexture
import typings.core.IAutoDetectOptions
import typings.core.Renderer
import typings.core.Resource
import typings.core.Texture
import typings.display.Container
import typings.math.IPointData
import typings.math.ObservablePoint
import typings.math.Rectangle

open external class Sprite(texture: Texture<Resource> = definedExternally) : Container {
	open var blendMode: BLEND_MODES
	open var indices: Uint16Array
	open var pluginName: String
	open var _width: Number
	open var _height: Number
	open var _texture: Texture<Resource>
	open var _textureID: Number
	open var _cachedTint: Number
	protected open var _textureTrimmedID: Number
	protected open var uvs: Float32Array
	protected open var _anchor: ObservablePoint<Any>
	protected open var vertexData: Float32Array
	open var _tintRGB: Number
	open var roundPixels: Boolean
	override var width: Number
	override var height: Number
	open val anchor: ObservablePoint<Any>
	open var tint: Number
	open var texture: Texture<Resource>
	
	protected open fun _onTextureUpdate()
	open fun calculateVertices()
	open fun calculateTrimmedVertices()
	override fun _render(renderer: Renderer)
	override fun _calculateBounds()
	override fun getLocalBounds(rect: Rectangle): Rectangle
	open fun containsPoint(point: IPointData): Boolean
	
	companion object {
		fun from(source: Texture<out Resource>): Sprite
		fun from(source: String): Sprite
		fun from(source: BaseTexture<Resource, IAutoDetectOptions>): Sprite
		fun from(source: HTMLImageElement): Sprite
		fun from(source: HTMLCanvasElement): Sprite
		fun from(source: HTMLVideoElement): Sprite
		fun from(source: ImageBitmap): Sprite
	}
}

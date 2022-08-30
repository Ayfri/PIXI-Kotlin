@file:JsModule("@pixi/sprite")

package pixi.typings.sprite

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.ImageBitmap
import pixi.externals.Color
import pixi.typings.constants.BLEND_MODES
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.IBaseTextureOptions
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.math.IPointData
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Rectangle

open external class Sprite(texture: Texture<Resource> = definedExternally) : Container<DisplayObject> {
	open var blendMode: BLEND_MODES
	open var indices: Uint16Array
	open var pluginName: String
	override var _width: Double
	override var _height: Double
	open var _texture: Texture<Resource>
	open var _textureID: Int
	open var _cachedTint: Int
	protected open var _textureTrimmedID: Int
	protected open var uvs: Float32Array
	protected open var _anchor: ObservablePoint<Any?>
	protected open var vertexData: Float32Array
	open var _tintRGB: Color
	
	open var roundPixels: Boolean
	override var width: Double
	override var height: Double
	open val anchor: ObservablePoint<Any?>
	open var tint: Color
	open var texture: Texture<Resource>
	
	protected open fun _onTextureUpdate()
	open fun calculateVertices()
	open fun calculateTrimmedVertices()
	override fun _render(renderer: Renderer)
	override fun _calculateBounds()
	override fun getLocalBounds(rect: Rectangle): Rectangle
	open fun containsPoint(point: IPointData): Boolean
	
	companion object {
		fun from(source: Texture<out Resource>, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: String, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: BaseTexture<Resource, IAutoDetectOptions>, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: HTMLImageElement, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: HTMLCanvasElement, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: HTMLVideoElement, options: IBaseTextureOptions<Any> = definedExternally): Sprite
		fun from(source: ImageBitmap, options: IBaseTextureOptions<Any> = definedExternally): Sprite
	}
}

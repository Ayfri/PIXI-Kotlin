@file:JsModule("@pixi/sprite-tiling")

package pixi.typings.sprite_tiling

import pixi.typings.core.*
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.IPointData
import pixi.typings.math.ISize
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Rectangle
import pixi.typings.math.Transform
import pixi.typings.sprite.Sprite

@Suppress("VAR_TYPE_MISMATCH_ON_OVERRIDE")
external interface TilingSpriteFromOptions : ISize, IBaseTextureOptions<Any?> {
	override var width: Int
	override var height: Int
}

open external class TilingSprite(texture: Texture<Resource>, width: Int = definedExternally, height: Int = definedExternally) : Sprite {
	open var tileTransform: Transform
	open var uvMatrix: TextureMatrix
	open var uvRespectAnchor: Boolean
	
	open var clampMargin: Double
	open var tileScale: ObservablePoint<Any?>
		@Suppress("WRONG_SETTER_PARAMETER_TYPE")
		set(value: IPointData) = definedExternally
	open var tilePosition: ObservablePoint<Any?>
	override var width: Double
	override var height: Double
	
	override fun _onTextureUpdate()
	override fun _render(renderer: Renderer)
	override fun _calculateBounds()
	override fun getLocalBounds(rect: Rectangle): Rectangle
	override fun containsPoint(point: IPointData): Boolean
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		fun from(source: BaseTexture<*, *>, options: TilingSpriteFromOptions): TilingSprite
		fun from(source: ImageSource, options: TilingSpriteFromOptions): TilingSprite
		fun from(source: String, options: TilingSpriteFromOptions): TilingSprite
		fun from(source: Texture<*>, options: TilingSpriteFromOptions): TilingSprite
	}
}

open external class TilingSpriteRenderer(renderer: Renderer) : ObjectRenderer {
	open var shader: Shader
	open var simpleShader: Shader
	open var quad: QuadUv
	open val state: State
	
	open fun contextChange()
	open fun render(ts: TilingSprite)
}

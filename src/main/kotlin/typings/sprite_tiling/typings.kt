@file:JsModule("@pixi/sprite-tiling")

package typings.sprite_tiling

import typings.core.*
import typings.math.IPointData
import typings.math.ObservablePoint
import typings.math.Rectangle
import typings.math.Transform
import typings.sprite.Sprite

@Suppress("VAR_TYPE_MISMATCH_ON_OVERRIDE")
external interface TilingSpriteFromOptions : IBaseTextureOptions<Any> {
	override var width: Number
	override var height: Number
}

open external class TilingSprite(texture: Texture<Resource>, width: Number = definedExternally, height: Number = definedExternally) : Sprite {
	open var tileTransform: Transform
	open var uvMatrix: TextureMatrix
	open var uvRespectAnchor: Boolean
	open var clampMargin: Number
	open var tileScale: ObservablePoint<Any>
	open var tilePosition: ObservablePoint<Any>
	override var width: Number
	override var height: Number

	override fun _onTextureUpdate()
	override fun _render(renderer: Renderer)
	override fun _calculateBounds()
	override fun getLocalBounds(rect: Rectangle): Rectangle
	override fun containsPoint(point: IPointData): Boolean

	companion object {
		fun from(source: String, options: TilingSpriteFromOptions): TilingSprite
	}
}

open external class TilingSpriteRenderer(renderer: Renderer) : ObjectRenderer {
	open var shader: Shader
	open var simpleShader: Shader
	open var quad: QuadUv
	open val state: State

	open fun render(ts: TilingSprite)
}
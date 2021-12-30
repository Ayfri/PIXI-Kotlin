@file:JsModule("@pixi/sprite-tiling")

package pixi.typings.sprite_tiling

import pixi.typings.Number
import pixi.typings.core.IBaseTextureOptions
import pixi.typings.core.ObjectRenderer
import pixi.typings.core.QuadUv
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Shader
import pixi.typings.core.State
import pixi.typings.core.Texture
import pixi.typings.core.TextureMatrix
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.IPointData
import pixi.typings.math.ISize
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Rectangle
import pixi.typings.math.Transform
import pixi.typings.sprite.Sprite

@Suppress("VAR_TYPE_MISMATCH_ON_OVERRIDE")
external interface TilingSpriteFromOptions : ISize, IBaseTextureOptions<Any?> {
	override var width: Number
	override var height: Number
}

open external class TilingSprite(texture: Texture<Resource>, width: Number = definedExternally, height: Number = definedExternally) : Sprite {
	open var tileTransform: Transform
	open var uvMatrix: TextureMatrix
	open var uvRespectAnchor: Boolean
	
	open var clampMargin: Number
	open var tileScale: ObservablePoint<Any?>
	open var tilePosition: ObservablePoint<Any?>
	override var width: Number
	override var height: Number
	
	override fun _onTextureUpdate()
	override fun _render(renderer: Renderer)
	override fun _calculateBounds()
	override fun getLocalBounds(rect: Rectangle): Rectangle
	override fun containsPoint(point: IPointData): Boolean
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		fun from(source: String, options: TilingSpriteFromOptions): TilingSprite
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

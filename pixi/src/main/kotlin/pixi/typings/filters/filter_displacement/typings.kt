@file:JsModule("@pixi/filter-displacement")

package pixi.typings.filter_displacement

import pixi.typings.constants.CLEAR_MODES
import pixi.typings.core.Filter
import pixi.typings.core.FilterSystem
import pixi.typings.core.ISpriteMaskTarget
import pixi.typings.core.RenderTexture
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.math.Matrix
import pixi.typings.math.Point

open external class DisplacementFilter(sprite: ISpriteMaskTarget, scale: Double = definedExternally) : Filter {
	open var maskSprite: ISpriteMaskTarget
	open var maskMatrix: Matrix
	open var scale: Point
	
	open var map: Texture<Resource>
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
}

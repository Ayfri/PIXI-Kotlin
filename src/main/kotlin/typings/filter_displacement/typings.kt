@file:JsModule("@pixi/filter-displacement")

package typings.filter_displacement

import typings.constants.CLEAR_MODES
import typings.core.Filter
import typings.core.FilterSystem
import typings.core.ISpriteMaskTarget
import typings.core.RenderTexture
import typings.core.Resource
import typings.core.Texture
import typings.math.Matrix
import typings.math.Point

open external class DisplacementFilter(sprite: ISpriteMaskTarget, scale: Number = definedExternally) : Filter {
	open var maskSprite: ISpriteMaskTarget
	open var maskMatrix: Matrix
	open var scale: Point
	open var map: Texture<Resource>
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
}

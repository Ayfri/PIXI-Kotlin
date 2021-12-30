@file:JsModule("@pixi/filter-blur")

package pixi.typings.filter_blur

import pixi.typings.Number
import pixi.typings.constants.BLEND_MODES
import pixi.typings.constants.CLEAR_MODES
import pixi.typings.core.Filter
import pixi.typings.core.FilterSystem
import pixi.typings.core.RenderTexture

open external class BlurFilter(
	strength: Number = definedExternally,
	quality: Number = definedExternally,
	resolution: Number = definedExternally,
	kernelSize: Number = definedExternally
) : Filter {
	open var blurXFilter: BlurFilterPass
	open var blurYFilter: BlurFilterPass
	
	open var blur: Number
	open var quality: Number
	open var blurX: Number
	open var blurY: Number
	override var blendMode: BLEND_MODES
	open var repeatEdgePixels: Boolean
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
	protected open fun updatePadding()
}

open external class BlurFilterPass(
	horizontal: Boolean,
	strength: Number = definedExternally,
	quality: Number = definedExternally,
	resolution: Number = definedExternally,
	kernelSize: Number = definedExternally
) : Filter {
	open var horizontal: Boolean
	open var strength: Number
	open var passes: Number
	
	open var blur: Number
	open var quality: Number
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
}

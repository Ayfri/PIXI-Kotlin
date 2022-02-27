@file:JsModule("@pixi/filter-blur")

package pixi.typings.filter_blur

import pixi.typings.constants.BLEND_MODES
import pixi.typings.constants.CLEAR_MODES
import pixi.typings.core.Filter
import pixi.typings.core.FilterSystem
import pixi.typings.core.RenderTexture

open external class BlurFilter(
	strength: Double = definedExternally,
	quality: Double = definedExternally,
	resolution: Double = definedExternally,
	kernelSize: Int = definedExternally
) : Filter {
	open var blurXFilter: BlurFilterPass
	open var blurYFilter: BlurFilterPass
	
	open var blur: Double
	open var quality: Double
	open var blurX: Double
	open var blurY: Double
	override var blendMode: BLEND_MODES
	open var repeatEdgePixels: Boolean
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
	protected open fun updatePadding()
}

open external class BlurFilterPass(
	horizontal: Boolean,
	strength: Double = definedExternally,
	quality: Double = definedExternally,
	resolution: Double = definedExternally,
	kernelSize: Int = definedExternally
) : Filter {
	open var horizontal: Boolean
	open var strength: Double
	open var passes: Int
	
	open var blur: Double
	open var quality: Int
	
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
}

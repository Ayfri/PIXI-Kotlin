@file:JsModule("@pixi/filter-blur")

package typings.filter_blur

import typings.constants.BLEND_MODES
import typings.constants.CLEAR_MODES
import typings.core.Filter
import typings.core.FilterSystem
import typings.core.RenderTexture
import typings.Number

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
	protected fun updatePadding()
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

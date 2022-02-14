@file:JsModule("@pixi/filter-color-matrix")

package pixi.typings.filter_color_matrix

import pixi.typings.core.Filter

open external class ColorMatrixFilter : Filter {
	open var grayscale: (scale: Double, multiply: Boolean) -> Unit
	
	open var matrix: ColorMatrix
	open var alpha: Double
	
	open fun brightness(b: Double, multiply: Boolean)
	open fun tint(color: Double, multiply: Boolean = definedExternally)
	open fun greyscale(scale: Double, multiply: Boolean)
	open fun blackAndWhite(multiply: Boolean)
	open fun hue(rotation: Double, multiply: Boolean)
	open fun contrast(amount: Double, multiply: Boolean)
	open fun saturate(amount: Double = definedExternally, multiply: Boolean = definedExternally)
	open fun desaturate()
	open fun negative(multiply: Boolean)
	open fun sepia(multiply: Boolean)
	open fun technicolor(multiply: Boolean)
	open fun polaroid(multiply: Boolean)
	open fun toBGR(multiply: Boolean)
	open fun kodachrome(multiply: Boolean)
	open fun browni(multiply: Boolean)
	open fun vintage(multiply: Boolean)
	open fun colorTone(desaturation: Double, toned: Double, lightColor: Double, darkColor: Double, multiply: Boolean)
	open fun night(intensity: Double, multiply: Boolean)
	open fun predator(amount: Double, multiply: Boolean)
	open fun lsd(multiply: Boolean)
	open fun reset()
}

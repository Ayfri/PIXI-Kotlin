@file:JsModule("@pixi/filter-color-matrix")

package typings.filter_color_matrix

import typings.core.Filter

open external class ColorMatrixFilter : Filter {
	open var grayscale: (scale: Number, multiply: Boolean) -> Unit
	open var matrix: ColorMatrix
	open var alpha: Number

	open fun brightness(b: Number, multiply: Boolean)
	open fun tint(color: Number, multiply: Boolean = definedExternally)
	open fun greyscale(scale: Number, multiply: Boolean)
	open fun blackAndWhite(multiply: Boolean)
	open fun hue(rotation: Number, multiply: Boolean)
	open fun contrast(amount: Number, multiply: Boolean)
	open fun saturate(amount: Number = definedExternally, multiply: Boolean = definedExternally)
	open fun desaturate()
	open fun negative(multiply: Boolean)
	open fun sepia(multiply: Boolean)
	open fun technicolor(multiply: Boolean)
	open fun polaroid(multiply: Boolean)
	open fun toBGR(multiply: Boolean)
	open fun kodachrome(multiply: Boolean)
	open fun browni(multiply: Boolean)
	open fun vintage(multiply: Boolean)
	open fun colorTone(desaturation: Number, toned: Number, lightColor: Number, darkColor: Number, multiply: Boolean)
	open fun night(intensity: Number, multiply: Boolean)
	open fun predator(amount: Number, multiply: Boolean)
	open fun lsd(multiply: Boolean)
	open fun reset()
}
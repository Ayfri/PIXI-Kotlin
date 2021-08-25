@file:JsModule("@pixi/filter-alpha")

package typings.filter_alpha

import typings.core.Filter

open external class AlphaFilter(alpha: Number) : Filter {
	constructor()

	open var alpha: Number
}
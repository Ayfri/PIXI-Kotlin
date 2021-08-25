@file:JsModule("@pixi/filter-noise")

package typings.filter_noise

import typings.core.Filter

open external class NoiseFilter(noise: Number, seed: Number) : Filter {
	constructor(noise: Number)
	constructor()

	open var noise: Number
	open var seed: Number
}


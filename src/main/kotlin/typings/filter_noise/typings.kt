@file:JsModule("@pixi/filter-noise")

package typings.filter_noise

import typings.core.Filter

open external class NoiseFilter(noise: Number = definedExternally, seed: Number = definedExternally) : Filter {
	open var noise: Number
	open var seed: Number
}


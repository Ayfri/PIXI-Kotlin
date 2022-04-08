@file:JsModule("@pixi/filter-noise")

package pixi.typings.filter_noise

import pixi.typings.core.Filter

open external class NoiseFilter(noise: Double = definedExternally, seed: Double = definedExternally) : Filter {
	open var noise: Double
	open var seed: Double
}


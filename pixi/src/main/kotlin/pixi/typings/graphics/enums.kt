package pixi.typings.graphics

import seskar.js.Case
import seskar.js.JsUnion

@JsUnion(case = Case.KEBAB)
external enum class LINE_CAP {
	BUTT,
	ROUND,
	SQUARE
}

@JsUnion(case = Case.KEBAB)
external enum class LINE_JOIN {
	MITER,
	BEVEL,
	ROUND
}
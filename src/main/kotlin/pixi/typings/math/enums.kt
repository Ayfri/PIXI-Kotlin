package pixi.typings.math

import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion
external enum class SHAPES {
	@JsInt(0)
	POLY,
	
	@JsInt(1)
	RECT,
	
	@JsInt(2)
	CIRC,
	
	@JsInt(3)
	ELIP,
	
	@JsInt(4)
	RREC
}

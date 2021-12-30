package pixi.typings.text

import seskar.js.Case
import seskar.js.JsInt
import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
external enum class TEXT_GRADIENT {
	@JsInt(0)
	LINEAR_VERTICAL,
	
	@JsInt(1)
	LINEAR_HORIZONTAL
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleAlign {
	LEFT,
	CENTER,
	RIGHT,
	JUSTIFY
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleFontStyle {
	NORMAL,
	ITALIC,
	OBLIQUE
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleFontVariant {
	NORMAL,
	SMALL_CAPS
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleFontWeight {
	NORMAL,
	BOLD,
	BOLDER,
	LIGHTER,
	
	@JsString("100")
	HUNDRED,
	
	@JsString("200")
	TWO_HUNDRED,
	
	@JsString("300")
	THREE_HUNDRED,
	
	@JsString("400")
	FOUR_HUNDRED,
	
	@JsString("500")
	FIVE_HUNDRED,
	
	@JsString("600")
	SIX_HUNDRED,
	
	@JsString("700")
	SEVEN_HUNDRED,
	
	@JsString("800")
	HEIGHT_HUNDRED,
	
	@JsString("900")
	NINE_HUNDRED,
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleLineJoin {
	MITER,
	ROUND,
	BEVEL
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleTextBaseline {
	ALPHABETIC,
	TOP,
	HANGING,
	MIDDLE,
	IDEOGRAPHIC,
	BOTTOM
}

@JsUnion(case = Case.SNAKE)
external enum class TextStyleWhiteSpace {
	NORMAL,
	PRE,
	PRE_LINE
}

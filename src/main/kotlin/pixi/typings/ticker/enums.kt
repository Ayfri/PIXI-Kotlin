package pixi.typings.ticker

import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion
external enum class UPDATE_PRIORITY {
	@JsInt(50)
	INTERACTION,
	
	@JsInt(25)
	HIGH,
	
	@JsInt(0)
	NORMAL,
	
	@JsInt(-25)
	LOW,
	
	@JsInt(-50)
	UTILITY
}

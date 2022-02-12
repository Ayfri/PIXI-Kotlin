package pixi.typings.accessibility

import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
external enum class PointerEvents {
	@JsString("auto")
	AUTO,
	
	@JsString("none")
	NONE,
	
	@JsString("visiblePainted")
	VISIBLE_PAINTED,
	
	@JsString("visibleFill")
	VISIBLE_FILL,
	
	@JsString("visibleStroke")
	VISIBLE_STROKE,
	
	@JsString("visible")
	VISIBLE,
	
	@JsString("painted")
	PAINTED,
	
	@JsString("fill")
	FILL,
	
	@JsString("stroke")
	STROKE,
	
	@JsString("all")
	ALL,
	
	@JsString("inherit")
	INHERIT
}

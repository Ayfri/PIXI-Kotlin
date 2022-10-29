package pixi.typings.settings

import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
enum class ContextIds {
	@JsString("2d")
	TWO_D,
	
	@JsString("webgl")
	WEBGL,
	
	@JsString("experimental-webgl")
	EXPERIMENTAL_WEBGL,
	
	@JsString("webgl2")
	WEBGL2;
}
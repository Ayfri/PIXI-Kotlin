package pixi.externals

import seskar.js.JsInt
import seskar.js.JsUnion

@JsUnion
enum class WEBGLVersion {
	@JsInt(1)
	ONE,
	
	@JsInt(2)
	TWO;
}

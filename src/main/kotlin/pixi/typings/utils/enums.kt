package pixi.typings.utils

import seskar.js.JsInt
import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
external enum class BufferType {
	@JsString("Float32Array")
	FLOAT32_ARRAY,
	
	@JsString("Uint32Array")
	UINT32_ARRAY,
	
	@JsString("Int32Array")
	INT32_ARRAY,
	
	@JsString("Uint16Array")
	UINT16_ARRAY,
	
	@JsString("Uint8Array")
	UINT8_ARRAY,
}

@JsUnion
external enum class Sign {
	@JsInt(-1)
	NEGATIVE,
	
	@JsInt(0)
	ZERO,
	
	@JsInt(1)
	POSITIVE,
}

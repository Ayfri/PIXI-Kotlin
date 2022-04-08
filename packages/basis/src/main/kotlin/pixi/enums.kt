package pixi

import seskar.js.JsInt
import seskar.js.JsString
import seskar.js.JsUnion

@JsUnion
external enum class BASIS_FORMATS {
	@JsInt(0)
	cTFETC1,
	
	@JsInt(1)
	cTFETC2,
	
	@JsInt(2)
	cTFBC1,
	
	@JsInt(3)
	cTFBC3,
	
	@JsInt(4)
	cTFBC4,
	
	@JsInt(5)
	cTFBC5,
	
	@JsInt(6)
	cTFBC7,
	
	@JsInt(8)
	cTFPVRTC1_4_RGB,
	
	@JsInt(9)
	cTFPVRTC1_4_RGBA,
	
	@JsInt(10)
	cTFASTC_4x4,
	
	@JsInt(11)
	cTFATC_RGB,
	
	@JsInt(12)
	cTFATC_RGBA_INTERPOLATED_ALPHA,
	
	@JsInt(13)
	cTFRGBA32,
	
	@JsInt(14)
	cTFRGB565,
	
	@JsInt(15)
	cTFBGR565,
	
	@JsInt(16)
	cTFRGBA4444
}

@JsUnion
external enum class ITranscodeResponseType {
	@JsString("init")
	INIT,
	
	@JsString("transcode")
	TRANSCODE
}

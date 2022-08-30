package pixi.typings.core

import seskar.js.JsString
import seskar.js.JsUnion

external interface WEBGL_compressed_texture_atc {
	var COMPRESSED_RGB_ATC_WEBGL: Int
	var COMPRESSED_RGBA_ATC_EXPLICIT_ALPHA_WEBGL: Int
	var COMPRESSED_RGBA_ATC_INTERPOLATED_ALPHA_WEBGL: Int
}

external interface WEBGL_compressed_texture_pvrtc {
	var COMPRESSED_RGB_PVRTC_4BPPV1_IMG: Int
	var COMPRESSED_RGBA_PVRTC_4BPPV1_IMG: Int
	var COMPRESSED_RGB_PVRTC_2BPPV1_IMG: Int
	var COMPRESSED_RGBA_PVRTC_2BPPV1_IMG: Int
}

@JsUnion
external enum class WebGLPowerPreference {
	@JsString("default")
	DEFAULT,
	
	@JsString("high-performance")
	HIGH_PERFORMANCE,
	
	@JsString("low-performance")
	LOW_PERFORMANCE
}

typealias GLboolean = Boolean

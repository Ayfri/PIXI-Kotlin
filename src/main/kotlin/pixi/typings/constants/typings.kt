package pixi.typings.constants

import seskar.js.JsInt
import seskar.js.JsString

external enum class ALPHA_MODES {
	@JsInt(0)
	NPM,
	
	@JsInt(1)
	UNPACK,
	
	@JsInt(2)
	PMA,
	
	@JsInt(0)
	NO_PREMULTIPLIED_ALPHA,
	
	@JsInt(1)
	PREMULTIPLY_ON_UPLOAD,
	
	@Deprecated("undocumented", replaceWith = ReplaceWith("PREMULTIPLIED_ALPHA"))
	@JsInt(2)
	PREMULTIPLY_ALPHA,
	
	@JsInt(2)
	PREMULTIPLIED_ALPHA
}

external enum class BLEND_MODES {
	@JsInt(0)
	NORMAL,
	
	@JsInt(1)
	ADD,
	
	@JsInt(2)
	MULTIPLY,
	
	@JsInt(3)
	SCREEN,
	
	@JsInt(4)
	OVERLAY,
	
	@JsInt(5)
	DARKEN,
	
	@JsInt(6)
	LIGHTEN,
	
	@JsInt(7)
	COLOR_DODGE,
	
	@JsInt(8)
	COLOR_BURN,
	
	@JsInt(9)
	HARD_LIGHT,
	
	@JsInt(10)
	SOFT_LIGHT,
	
	@JsInt(11)
	DIFFERENCE,
	
	@JsInt(12)
	EXCLUSION,
	
	@JsInt(13)
	HUE,
	
	@JsInt(14)
	SATURATION,
	
	@JsInt(15)
	COLOR,
	
	@JsInt(16)
	LUMINOSITY,
	
	@JsInt(17)
	NORMAL_NPM,
	
	@JsInt(18)
	ADD_NPM,
	
	@JsInt(19)
	SCREEN_NPM,
	
	@JsInt(20)
	NONE,
	
	@JsInt(0)
	SRC_OVER,
	
	@JsInt(21)
	SRC_IN,
	
	@JsInt(22)
	SRC_OUT,
	
	@JsInt(23)
	SRC_ATOP,
	
	@JsInt(24)
	DST_OVER,
	
	@JsInt(25)
	DST_IN,
	
	@JsInt(26)
	DST_OUT,
	
	@JsInt(27)
	DST_ATOP,
	
	@JsInt(26)
	ERASE,
	
	@JsInt(28)
	SUBTRACT,
	
	@JsInt(29)
	XOR
}

external enum class BUFFER_BITS {
	@JsInt(16384)
	COLOR,
	
	@JsInt(256)
	DEPTH,
	
	@JsInt(1024)
	STENCIL
}

external enum class BUFFER_TYPE {
	@JsInt(34963)
	ELEMENT_ARRAY_BUFFER,
	
	@JsInt(34962)
	ARRAY_BUFFER,
	
	@JsInt(35345)
	UNIFORM_BUFFER
}

external enum class CLEAR_MODES {
	@JsInt(0)
	NO,
	
	@JsInt(1)
	YES,
	
	@JsInt(2)
	AUTO,
	
	@JsInt(0)
	BLEND,
	
	@JsInt(1)
	CLEAR,
	
	@JsInt(2)
	BLIT
}

external enum class DRAW_MODES {
	@JsInt(0)
	POINTS,
	
	@JsInt(1)
	LINES,
	
	@JsInt(2)
	LINE_LOOP,
	
	@JsInt(3)
	LINE_STRIP,
	
	@JsInt(4)
	TRIANGLES,
	
	@JsInt(5)
	TRIANGLE_STRIP,
	
	@JsInt(6)
	TRIANGLE_FAN
}

external enum class ENV {
	@JsInt(0)
	WEBGL_LEGACY,
	
	@JsInt(1)
	WEBGL,
	
	@JsInt(2)
	WEBGL2
}

external enum class FORMATS {
	@JsInt(6408)
	RGBA,
	
	@JsInt(6407)
	RGB,
	
	@JsInt(33319)
	RG,
	
	@JsInt(6403)
	RED,
	
	@JsInt(36249)
	RGBA_INTEGER,
	
	@JsInt(36248)
	RGB_INTEGER,
	
	@JsInt(33320)
	RG_INTEGER,
	
	@JsInt(36244)
	RED_INTEGER,
	
	@JsInt(6406)
	ALPHA,
	
	@JsInt(6409)
	LUMINANCE,
	
	@JsInt(6410)
	LUMINANCE_ALPHA,
	
	@JsInt(6402)
	DEPTH_COMPONENT,
	
	@JsInt(34041)
	DEPTH_STENCIL
}

external enum class GC_MODES {
	@JsInt(0)
	AUTO,
	
	@JsInt(1)
	MANUAL
}

external enum class MASK_TYPES {
	@JsInt(0)
	NONE,
	
	@JsInt(1)
	SCISSOR,
	
	@JsInt(2)
	STENCIL,
	
	@JsInt(3)
	SPRITE
}

external enum class MIPMAP_MODES {
	@JsInt(0)
	OFF,
	
	@JsInt(1)
	POW2,
	
	@JsInt(2)
	ON,
	
	@JsInt(3)
	ON_MANUAL
}

external enum class MSAA_QUALITY {
	@JsInt(0)
	NONE,
	
	@JsInt(2)
	LOW,
	
	@JsInt(4)
	MEDIUM,
	
	@JsInt(8)
	HIGH
}

external enum class PRECISION {
	@JsString("low")
	LOW,
	
	@JsString("medium")
	MEDIUM,
	
	@JsString("high")
	HIGH
}

external enum class RENDERER_TYPE {
	@JsInt(0)
	UNKNOWN,
	
	@JsInt(1)
	WEBGL,
	
	@JsInt(2)
	CANVAS
}

external enum class SAMPLER_TYPES {
	@JsInt(0)
	FLOAT,
	
	@JsInt(1)
	INT,
	
	@JsInt(2)
	UINT
}

external enum class SCALE_MODES {
	@JsInt(0)
	NEAREST,
	
	@JsInt(1)
	LINEAR
}

external enum class TARGETS {
	@JsInt(3553)
	TEXTURE_2D,
	
	@JsInt(34067)
	TEXTURE_CUBE_MAP,
	
	@JsInt(35866)
	TEXTURE_2D_ARRAY,
	
	@JsInt(34069)
	TEXTURE_CUBE_MAP_POSITIVE_X,
	
	@JsInt(34070)
	TEXTURE_CUBE_MAP_NEGATIVE_X,
	
	@JsInt(34071)
	TEXTURE_CUBE_MAP_POSITIVE_Y,
	
	@JsInt(34072)
	TEXTURE_CUBE_MAP_NEGATIVE_Y,
	
	@JsInt(34073)
	TEXTURE_CUBE_MAP_POSITIVE_Z,
	
	@JsInt(34074)
	TEXTURE_CUBE_MAP_NEGATIVE_Z
}

external enum class TYPES {
	@JsInt(5121)
	UNSIGNED_BYTE,
	
	@JsInt(5123)
	UNSIGNED_SHORT,
	
	@JsInt(33635)
	UNSIGNED_SHORT_5_6_5,
	
	@JsInt(32819)
	UNSIGNED_SHORT_4_4_4_4,
	
	@JsInt(32820)
	UNSIGNED_SHORT_5_5_5_1,
	
	@JsInt(5125)
	UNSIGNED_INT,
	
	@JsInt(35899)
	UNSIGNED_INT_10F_11F_11F_REV,
	
	@JsInt(33640)
	UNSIGNED_INT_2_10_10_10_REV,
	
	@JsInt(34042)
	UNSIGNED_INT_24_8,
	
	@JsInt(35902)
	UNSIGNED_INT_5_9_9_9_REV,
	
	@JsInt(5120)
	BYTE,
	
	@JsInt(5122)
	SHORT,
	
	@JsInt(5124)
	INT,
	
	@JsInt(5126)
	FLOAT,
	
	@JsInt(36269)
	FLOAT_32_UNSIGNED_INT_24_8_REV,
	
	@JsInt(36193)
	HALF_FLOAT
}

external enum class WRAP_MODES {
	@JsInt(33071)
	CLAMP,
	
	@JsInt(10497)
	REPEAT,
	
	@JsInt(33648)
	MIRRORED_REPEAT
}

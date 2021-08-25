package typings.core

import org.khronos.webgl.WebGLObject
import seskar.js.JsString
import seskar.js.JsUnion
import typings.compressed_textures.GLenum

@JsUnion
external enum class WebGLPowerPreference {
	@JsString("default")
	DEFAULT,

	@JsString("high-performance")
	HIGH_PERFORMANCE,

	@JsString("low-performance")
	LOW_PERFORMANCE
}

external interface EXT_texture_filter_anisotropic {
	var MAX_TEXTURE_MAX_ANISOTROPY_EXT: GLenum
	var TEXTURE_MAX_ANISOTROPY_EXT: GLenum
}

external interface OES_element_index_uint

external interface OES_texture_float

external interface OES_texture_float_linear

external interface OES_texture_half_float {
	var HALF_FLOAT_OES: GLenum
}

external interface OES_texture_half_float_linear

external interface OES_vertex_array_object {
	fun bindVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?)
	fun createVertexArrayOES(): WebGLVertexArrayObjectOES?
	fun deleteVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?)
	fun isVertexArrayOES(arrayObject: WebGLVertexArrayObjectOES?): GLboolean
	var VERTEX_ARRAY_BINDING_OES: GLenum
}

external interface WEBGL_color_buffer_float {
	var FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE_EXT: GLenum
	var RGBA32F_EXT: GLenum
	var UNSIGNED_NORMALIZED_EXT: GLenum
}

external interface WEBGL_draw_buffers {
	fun drawBuffersWEBGL(buffers: Iterable<GLenum>)
	fun drawBuffersWEBGL(buffers: Array<GLenum>)
	var COLOR_ATTACHMENT0_WEBGL: GLenum
	var COLOR_ATTACHMENT10_WEBGL: GLenum
	var COLOR_ATTACHMENT11_WEBGL: GLenum
	var COLOR_ATTACHMENT12_WEBGL: GLenum
	var COLOR_ATTACHMENT13_WEBGL: GLenum
	var COLOR_ATTACHMENT14_WEBGL: GLenum
	var COLOR_ATTACHMENT15_WEBGL: GLenum
	var COLOR_ATTACHMENT1_WEBGL: GLenum
	var COLOR_ATTACHMENT2_WEBGL: GLenum
	var COLOR_ATTACHMENT3_WEBGL: GLenum
	var COLOR_ATTACHMENT4_WEBGL: GLenum
	var COLOR_ATTACHMENT5_WEBGL: GLenum
	var COLOR_ATTACHMENT6_WEBGL: GLenum
	var COLOR_ATTACHMENT7_WEBGL: GLenum
	var COLOR_ATTACHMENT8_WEBGL: GLenum
	var COLOR_ATTACHMENT9_WEBGL: GLenum
	var DRAW_BUFFER0_WEBGL: GLenum
	var DRAW_BUFFER10_WEBGL: GLenum
	var DRAW_BUFFER11_WEBGL: GLenum
	var DRAW_BUFFER12_WEBGL: GLenum
	var DRAW_BUFFER13_WEBGL: GLenum
	var DRAW_BUFFER14_WEBGL: GLenum
	var DRAW_BUFFER15_WEBGL: GLenum
	var DRAW_BUFFER1_WEBGL: GLenum
	var DRAW_BUFFER2_WEBGL: GLenum
	var DRAW_BUFFER3_WEBGL: GLenum
	var DRAW_BUFFER4_WEBGL: GLenum
	var DRAW_BUFFER5_WEBGL: GLenum
	var DRAW_BUFFER6_WEBGL: GLenum
	var DRAW_BUFFER7_WEBGL: GLenum
	var DRAW_BUFFER8_WEBGL: GLenum
	var DRAW_BUFFER9_WEBGL: GLenum
	var MAX_COLOR_ATTACHMENTS_WEBGL: GLenum
	var MAX_DRAW_BUFFERS_WEBGL: GLenum
}

external interface WEBGL_lose_context {
	fun loseContext()
	fun restoreContext()
}

typealias WebGLVertexArrayObjectOES = WebGLObject

typealias GLboolean = Boolean
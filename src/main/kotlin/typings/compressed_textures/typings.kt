@file:JsModule("@pixi/compressed-textures")

package typings.compressed_textures

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import seskar.js.JsInt
import seskar.js.JsString
import seskar.js.JsUnion
import typings.Indexed
import typings.VarArgFun
import typings.core.*
import typings.loaders.LoaderResource
import kotlin.js.Promise

abstract external class BlobResource(source: String, options: IBlobOptions) : BufferResource {
	constructor(source: Uint32Array, options: IBlobOptions)
	constructor(source: Uint8Array, options: IBlobOptions)
	constructor(source: Float32Array, options: IBlobOptions)
	constructor(source: Uint32Array)
	constructor(source: Uint8Array)
	constructor(source: Float32Array)
	constructor(source: String)

	protected var origin: String
	protected var buffer: ViewableBuffer
	protected var loaded: Boolean

	protected fun onBlobLoaded(_data: ArrayBuffer)

	override fun load(): Promise<Resource>
}

external interface CompressedLevelBuffer {
	var levelID: Number
	var levelWidth: Number
	var levelHeight: Number
	var levelBuffer: Uint8Array
}

@JsUnion
external enum class CompressedTextureExtensionRef {
	@JsString("levelId")
	LEVEL_ID,

	@JsString("levelWidth")
	LEVEL_WIDTH,

	@JsString("levelHeight")
	LEVEL_HEIGHT,

	@JsString("levelBuffer")
	LEVEL_BUFFER
}

external interface CompressedTextureExtensions {
	var s3tc: WEBGL_compressed_texture_s3tc?
	var s3tc_sRGB: WEBGL_compressed_texture_s3tc_srgb
	var etc: Any
	var etc1: Any
	var pvrtc: Any
	var atc: Any
	var astc: WEBGL_compressed_texture_astc
}

external interface PartialCompressedTextureExtensions {
	var s3tc: WEBGL_compressed_texture_s3tc?
	var s3tc_sRGB: WEBGL_compressed_texture_s3tc_srgb?
	var etc: Any?
	var etc1: Any?
	var pvrtc: Any?
	var atc: Any?
	var astc: WEBGL_compressed_texture_astc?
}

open external class CompressedTextureLoader {
	companion object {
		var textureExtensions: PartialCompressedTextureExtensions
		var textureFormats: Indexed<INTERNAL_FORMATS?, Number>
		fun use(resource: LoaderResource, next: VarArgFun<String, Unit>)
		fun add()
	}
}

external interface TextureManifest {
	var src: String
	var format: KEYOF_INTERNAL_FORMATS
}

external interface CompressedTextureManifest {
	var textures: Array<TextureManifest>
	var cacheID: String
}

open external class CompressedTextureResource(source: String, options: ICompressedTextureResourceOptions) :
	BlobResource {
	constructor(source: Uint8Array, options: ICompressedTextureResourceOptions)
	constructor(source: Uint32Array, options: ICompressedTextureResourceOptions)

	open var format: INTERNAL_FORMATS
	open var levels: Number
	override fun upload(
		renderer: Renderer,
		_texture: BaseTexture<Resource, IAutoDetectOptions>,
		_glTexture: GLTexture
	): Boolean

	protected fun onBlobLoaded()
}

open external class DDSLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<String, Unit>)
	}
}

external val FORMATS_TO_COMPONENTS: Indexed<Number, Number>;

external interface IBlobOptions {
	var autoLoad: Boolean?
	var width: Number
	var height: Number
}

external interface ICompressedTextureResourceOptions {
	var format: INTERNAL_FORMATS
	var width: Number
	var height: Number
	var levels: Number?
	var levelBuffers: Array<CompressedLevelBuffer>?
}

external val INTERNAL_FORMAT_TO_BYTES_PER_PIXEL: Indexed<Number, Number>

@JsUnion
external enum class KEYOF_INTERNAL_FORMATS {
	COMPRESSED_RGB_S3TC_DXT1_EXT,
	COMPRESSED_RGBA_S3TC_DXT1_EXT,
	COMPRESSED_RGBA_S3TC_DXT3_EXT,
	COMPRESSED_RGBA_S3TC_DXT5_EXT,
	COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT,
	COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT,
	COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT,
	COMPRESSED_SRGB_S3TC_DXT1_EXT,
	COMPRESSED_R11_EAC,
	COMPRESSED_SIGNED_R11_EAC,
	COMPRESSED_RG11_EAC,
	COMPRESSED_SIGNED_RG11_EAC,
	COMPRESSED_RGB8_ETC2,
	COMPRESSED_RGBA8_ETC2_EAC,
	COMPRESSED_SRGB8_ETC2,
	COMPRESSED_SRGB8_ALPHA8_ETC2_EAC,
	COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2,
	COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2,
	COMPRESSED_RGB_PVRTC_4BPPV1_IMG,
	COMPRESSED_RGBA_PVRTC_4BPPV1_IMG,
	COMPRESSED_RGB_PVRTC_2BPPV1_IMG,
	COMPRESSED_RGBA_PVRTC_2BPPV1_IMG,
	COMPRESSED_RGB_ETC1_WEBGL,
	COMPRESSED_RGB_ATC_WEBGL,
	COMPRESSED_RGBA_ATC_EXPLICIT_ALPHA_WEBGL,
	COMPRESSED_RGBA_ATC_INTERPOLATED_ALPHA_WEBGL,
}


@JsUnion
external enum class INTERNAL_FORMATS {
	@JsInt(33776)
	COMPRESSED_RGB_S3TC_DXT1_EXT,

	@JsInt(33777)
	COMPRESSED_RGBA_S3TC_DXT1_EXT,

	@JsInt(33778)
	COMPRESSED_RGBA_S3TC_DXT3_EXT,

	@JsInt(33779)
	COMPRESSED_RGBA_S3TC_DXT5_EXT,

	@JsInt(35917)
	COMPRESSED_SRGB_ALPHA_S3TC_DXT1_EXT,

	@JsInt(35918)
	COMPRESSED_SRGB_ALPHA_S3TC_DXT3_EXT,

	@JsInt(35919)
	COMPRESSED_SRGB_ALPHA_S3TC_DXT5_EXT,

	@JsInt(35916)
	COMPRESSED_SRGB_S3TC_DXT1_EXT,

	@JsInt(37488)
	COMPRESSED_R11_EAC,

	@JsInt(37489)
	COMPRESSED_SIGNED_R11_EAC,

	@JsInt(37490)
	COMPRESSED_RG11_EAC,

	@JsInt(37491)
	COMPRESSED_SIGNED_RG11_EAC,

	@JsInt(37492)
	COMPRESSED_RGB8_ETC2,

	@JsInt(37496)
	COMPRESSED_RGBA8_ETC2_EAC,

	@JsInt(37493)
	COMPRESSED_SRGB8_ETC2,

	@JsInt(37497)
	COMPRESSED_SRGB8_ALPHA8_ETC2_EAC,

	@JsInt(37494)
	COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2,

	@JsInt(37495)
	COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2,

	@JsInt(35840)
	COMPRESSED_RGB_PVRTC_4BPPV1_IMG,

	@JsInt(35842)
	COMPRESSED_RGBA_PVRTC_4BPPV1_IMG,

	@JsInt(35841)
	COMPRESSED_RGB_PVRTC_2BPPV1_IMG,

	@JsInt(35843)
	COMPRESSED_RGBA_PVRTC_2BPPV1_IMG,

	@JsInt(36196)
	COMPRESSED_RGB_ETC1_WEBGL,

	@JsInt(35986)
	COMPRESSED_RGB_ATC_WEBGL,

	@JsInt(35986)
	COMPRESSED_RGBA_ATC_EXPLICIT_ALPHA_WEBGL,

	@JsInt(34798)
	COMPRESSED_RGBA_ATC_INTERPOLATED_ALPHA_WEBGL;
}

open external class KTXLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<String, Unit>)
	}
}

external val TYPES_TO_BYTES_PER_COMPONENT: Indexed<Number, Number>

external val TYPES_TO_BYTES_PER_PIXEL: Indexed<Number, Number>
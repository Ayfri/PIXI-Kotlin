@file:JsModule("@pixi/compressed-textures")

package typings.compressed_textures

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import typings.Indexed
import typings.Number
import typings.VarArgFun
import typings.core.BaseTexture
import typings.core.BufferResource
import typings.core.GLTexture
import typings.core.IAutoDetectOptions
import typings.core.Renderer
import typings.core.Resource
import typings.core.ViewableBuffer
import typings.loaders.LoaderResource
import kotlin.js.Promise

abstract external class BlobResource(source: String, options: IBlobOptions = definedExternally) : BufferResource {
	constructor(source: Uint32Array, options: IBlobOptions = definedExternally)
	constructor(source: Uint8Array, options: IBlobOptions = definedExternally)
	constructor(source: Float32Array, options: IBlobOptions = definedExternally)
	
	protected open var origin: String
	protected open var buffer: ViewableBuffer
	protected open var loaded: Boolean
	
	protected open fun onBlobLoaded(_data: ArrayBuffer)
	
	override fun load(): Promise<Resource>
}

external interface CompressedLevelBuffer {
	var levelID: Number
	var levelWidth: Number
	var levelHeight: Number
	var levelBuffer: Uint8Array
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
		var textureFormats: Indexed<INTERNAL_FORMATS, Number>
		fun use(resource: LoaderResource, next: VarArgFun<Any?, Unit>)
		fun add()
	}
}

external interface TextureManifest {
	var src: String
	var format: KEYOF_INTERNAL_FORMATS?
}

external interface CompressedTextureManifest {
	var textures: Array<TextureManifest>
	var cacheID: String
}

open external class CompressedTextureResource(source: String, options: ICompressedTextureResourceOptions) : BlobResource {
	constructor(source: Uint8Array, options: ICompressedTextureResourceOptions)
	constructor(source: Uint32Array, options: ICompressedTextureResourceOptions)
	
	open var format: INTERNAL_FORMATS
	open var levels: Number
	override fun upload(renderer: Renderer, _texture: BaseTexture<Resource, IAutoDetectOptions>, _glTexture: GLTexture): Boolean
	
	protected open fun onBlobLoaded()
}

external class DDSLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any?, Unit>)
	}
}

external val FORMATS_TO_COMPONENTS: Indexed<Number, Number>

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

external class KTXLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any?, Unit>)
	}
}

external val TYPES_TO_BYTES_PER_COMPONENT: Indexed<Number, Number>

external val TYPES_TO_BYTES_PER_PIXEL: Indexed<Number, Number>

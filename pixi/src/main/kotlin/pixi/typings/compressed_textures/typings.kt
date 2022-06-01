@file:JsModule("@pixi/compressed-textures")

package pixi.typings.compressed_textures

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import org.khronos.webgl.Uint8Array
import pixi.typings.Object
import pixi.typings.VarArgFun
import pixi.typings.constants.FORMATS
import pixi.typings.constants.TYPES
import pixi.typings.core.BaseTexture
import pixi.typings.core.BufferResource
import pixi.typings.core.GLTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.ViewableBuffer
import pixi.typings.loaders.LoaderResource
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
	var levelID: Int
	var levelWidth: Int
	var levelHeight: Int
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

external interface CompressedTextureExtensionsPartial {
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
		var textureExtensions: CompressedTextureExtensionsPartial
		var textureFormats: Object<INTERNAL_FORMATS, Number>
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
	open var levels: Int
	override fun upload(renderer: Renderer, _texture: BaseTexture<Resource, IAutoDetectOptions>, _glTexture: GLTexture): Boolean
	
	protected open fun onBlobLoaded()
}

external class DDSLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any?, Unit>)
	}
}

external val FORMATS_TO_COMPONENTS: Object<Int, Int>

external interface IBlobOptions {
	var autoLoad: Boolean?
	var width: Int
	var height: Int
}

external interface ICompressedTextureResourceOptions {
	var format: INTERNAL_FORMATS
	var width: Int
	var height: Int
	var levels: Int?
	var levelBuffers: Array<CompressedLevelBuffer>?
}

external val INTERNAL_FORMAT_TO_BYTES_PER_PIXEL: Object<INTERNAL_FORMATS, Double>

external class KTXLoader {
	companion object {
		var loadKeyValueData: Boolean
		fun use(resource: LoaderResource, next: VarArgFun<Any?, Unit>)
	}
}

external val TYPES_TO_BYTES_PER_COMPONENT: Object<FORMATS, Int>

external val TYPES_TO_BYTES_PER_PIXEL: Object<TYPES, Int>

@file:JsModule("@pixi/basis")

package pixi.typings.basis

import kotlinx.js.JsPair
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.w3c.dom.MessageEvent
import org.w3c.dom.Worker
import pixi.typings.Object
import pixi.typings.VarArgFun
import pixi.typings.compressed_textures.INTERNAL_FORMATS
import pixi.typings.compressed_textures.WEBGL_compressed_texture_astc
import pixi.typings.compressed_textures.WEBGL_compressed_texture_s3tc
import pixi.typings.compressed_textures.WEBGL_compressed_texture_s3tc_srgb
import pixi.typings.constants.TYPES
import pixi.typings.loaders.LoaderResource
import pixi.typings.runner.Runner
import kotlin.js.Promise

external interface BASIS_Transcoder {
	var wasmBinary: ArrayBuffer
}

external interface BASIS {
	fun invoke(opts: BASIS_Transcoder): Promise<BasisBinding>
}

external object BASIS_FORMAT_TO_INTERNAL_FORMAT : Object<Int, INTERNAL_FORMATS>

external object BASIS_FORMAT_TO_TYPE : Object<Int, TYPES>

external object BASIS_FORMATS_ALPHA : Object<Int, Boolean>

external interface BasisBinding {
	var BasisFile: BasisFile
	fun initializeBasis()
}

open external class BasisFile(buffer: Uint8Array) {
	open fun getNumImages(): Int
	open fun getNumLevels(imageId: Int): Int
	open fun getImageWidth(imageId: Int, level: Int): Int
	open fun getImageHeight(imageId: Int, level: Int): Int
	open fun getHasAlpha(): Boolean
	open fun startTranscoding(): Boolean
	open fun getImageTranscodedSizeInBytes(imageId: Int, level: Int, basisFormat: Int): Int
	open fun transcodeImage(dstBuff: Uint8Array, imageId: Int, level: Int, basisFormat: BASIS_FORMATS, pvrtcWrapAddressing: Boolean, getAlphaForOpaqueFormats: Boolean): Int
	open fun close()
	open fun delete()
}

open external class BasisLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any, Unit>)
		fun autoDetectFormats(extensions: BasisTextureExtensionsPartial = definedExternally)
		fun bindTranscoder(basisLibrary: BasisBinding)
		fun loadTranscoder(jsURL: String, wasmURL: String): Promise<JsPair<Unit, Unit>>
		fun setTranscoder(jsSource: String, wasmSource: ArrayBuffer)
		var TranscoderWorker: TranscoderWorker
		var TRANSCODER_WORKER_POOL_LIMIT: Int
	}
}

external interface BasisTextureExtensions {
	val s3tc: WEBGL_compressed_texture_s3tc?
	val s3tc_sRGB: WEBGL_compressed_texture_s3tc_srgb
	val etc: Any
	val etc1: Any
	val pvrtc: Any
	val atc: Any
	val astc: WEBGL_compressed_texture_astc
}

external interface BasisTextureExtensionsPartial {
	val s3tc: WEBGL_compressed_texture_s3tc?
	val s3tc_sRGB: WEBGL_compressed_texture_s3tc_srgb?
	val etc: Any?
	val etc1: Any?
	val pvrtc: Any?
	val atc: Any?
	val astc: WEBGL_compressed_texture_astc?
}

external object INTERNAL_FORMAT_TO_BASIS_FORMAT : Object<Int, Int>

external interface ITranscodeResponseImageLevel {
	val levelID: Int
	val levelWidth: Int
	val levelHeight: Int
	val levelBuffer: Uint8Array
}

external interface ITranscodeResponseImage {
	val imageID: Int
	val levelArray: Array<ITranscodeResponseImageLevel>
	val width: Int
	val height: Int
}

external interface ITranscodeResponse {
	var type: ITranscodeResponseType
	var requestID: Int?
	var success: Boolean
	var basisFormat: BASIS_FORMATS?
	var imageArray: Array<ITranscodeResponseImage>?
}

external interface TranscoderWorkerRequest {
	var resolve: (data: ITranscodeResponse) -> Unit
	var reject: () -> Unit
}

open external class TranscoderWorker {
	open var isInit: Boolean
	open var load: Int
	open var requests: Object<Int, TranscoderWorkerRequest>
	protected open var worker: Worker
	protected open var initPromise: Promise<Unit>
	protected open var onInit: (() -> Unit)
	
	open fun initAsync(): Promise<Unit>
	open fun transcodeAsync(basisData: Uint8Array, rgbaFORMATS: BASIS_FORMATS, rgbFORMATS: BASIS_FORMATS): Promise<ITranscodeResponse>
	protected open var onMessage: (e: MessageEvent) -> Unit
	
	companion object {
		var bindingURL: String
		var jsSource: String
		var wasmSource: ArrayBuffer
		var onTranscoderInitialized: Runner
		
		val workerURL: String
		
		fun loadTranscoder(jsURL: String, wasmURL: String): Promise<JsPair<Unit, Unit>>
		fun setTranscoder(jsSource: String, wasmSource: ArrayBuffer)
	}
}

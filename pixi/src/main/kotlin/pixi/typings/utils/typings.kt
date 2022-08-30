@file:JsModule("@pixi/utils")

package pixi.typings.utils

import org.khronos.webgl.ArrayBufferView
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.Uint32Array
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.ImageData
import org.w3c.dom.Location
import org.w3c.dom.url.URL
import pixi.externals.Color
import pixi.externals.ColorArr
import pixi.typings.Object
import pixi.typings.constants.BLEND_MODES
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.ITypedArray
import pixi.typings.core.Program
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import kotlin.js.RegExp

external val BaseTextureCache: Object<String, BaseTexture<Resource, IAutoDetectOptions>>

open external class CanvasRenderTarget(width: Int, height: Int, resolution: Double = definedExternally) {
	open var canvas: HTMLCanvasElement
	open var context: CanvasRenderingContext2D
	open var resolution: Double
	
	open var width: Int
	open var height: Int
	
	open fun clear()
	open fun resize(desiredWidth: Int, desiredHeight: Int)
	open fun destroy()
}

external fun clearTextureCache()
external fun correctBlendMode(blendMode: Short, premultiplied: Boolean): BLEND_MODES
external fun createIndicesForQuads(
	size: Int,
	outBuffer: Uint16Array = definedExternally,
): ArrayBufferView /* Uint16Array | Uint32Array */

external fun createIndicesForQuads(
	size: Int,
	outBuffer: Uint32Array = definedExternally,
): ArrayBufferView /* Uint16Array | Uint32Array */

external val DATA_URI: RegExp

external fun decomposeDataUri(dataUri: String): DecomposedDataUri

external interface DecomposedDataUri {
	var mediaType: String
	var subType: String
	var charset: String
	var encoding: String
	var data: String
}

external fun deprecation(version: String, message: String, ignoreDepth: Int = definedExternally)
external fun destroyTextureCache()
external fun determineCrossOrigin(url: String, loc: Location = definedExternally): String

external interface FormatFunction {
	operator fun invoke(URL: URL, options: URLFormatOptions = definedExternally): String
	operator fun invoke(urlObject: URLFormatOptions): String
	operator fun invoke(urlObject: String): String
}

external fun getBufferType(array: ITypedArray): BufferType?
external fun getResolutionOfUrl(url: String, default: Int = definedExternally): Double
external fun hex2rgb(hex: Color, out: ColorArr = definedExternally): ColorArr
external fun hex2rgb(hex: Color, out: Float32Array): Float32Array
external fun hex2string(hex: Color): String
external fun interleaveTypedArrays(arrays: Array<PackedArray>, sizes: Array<Int>): Float32Array
external fun isPow2(v: Int): Boolean
external fun isWebGLSupported(): Boolean
external fun log2(v: Number): Int
external fun nextPow2(v: Number): Int

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ParsedUrlQuery : Object<String, dynamic /* String | Array<String> */>

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ParsedUrlQueryInput : Object<String, Any? /* unknown */>

external interface ParseFunction {
	operator fun invoke(urlStr: String): UrlWithStringQuery
	operator fun invoke(urlString: String, parseQueryString: Boolean?, slashesDenoteHost: Boolean = definedExternally): UrlWithStringQuery
	operator fun invoke(urlString: String, parseQueryString: Boolean, slashesDenoteHost: Boolean = definedExternally): Url
}

external interface PathParseResult {
	var root: String?
	var dir: String?
	var base: String?
	var ext: String?
	var name: String?
}

external interface Path {
	var sep: String
	var delimiter: String
	
	fun toPosix(path: String): String
	fun toAbsolute(path: String, baseUrl: String = definedExternally, rootUrl: String = definedExternally): String
	fun isUrl(path: String): Boolean
	fun isDataUrl(path: String): Boolean
	fun hasProtocol(path: String): Boolean
	fun getProtocol(path: String): String
	fun normalize(path: String): String
	fun join(vararg paths: String): String
	fun isAbsolute(path: String): Boolean
	fun dirname(path: String): String
	fun rootname(path: String): String
	fun basename(path: String, ext: String = definedExternally): String
	fun extname(path: String): String
	fun parse(path: String): PathParseResult
}

external val path: Path

external val premultiplyBlendMode: Array<Array<BLEND_MODES>>

external fun premultiplyRgba(rg: ColorArr, alpha: Double, out: Float32Array = definedExternally, premultiply: Boolean = definedExternally): Float32Array
external fun premultiplyRgba(rg: Float32Array, alpha: Double, out: Float32Array = definedExternally, premultiply: Boolean = definedExternally): Float32Array
external fun premultiplyTint(tint: Color, alpha: Double): Int
external fun premultiplyTintToRgba(tint: Color, alpha: Double, out: Float32Array, premultiply: Boolean = definedExternally): Float32Array

external val ProgramCache: Object<String, Program>

external fun removeItems(arr: Array<Any?>, startIdx: Int, removeCount: Int)

external fun rgb2hex(rgb: ColorArr): Color
external fun rgb2hex(rgb: Float32Array): Color
external fun sayHello(type: String)

external fun sign(n: Number): Sign
external fun skipHello()
external fun string2Hex(string: String): Color

external val TextureCache: Object<String, Texture<Resource>>

external interface TrimmedCanvas {
	var width: Int
	var height: Int
	var data: ImageData?
}

external fun trimCanvas(canvas: HTMLCanvasElement): TrimmedCanvas
external fun uid(): Int

external interface Url : UrlObjectCommon {
	var port: String?
	var query: dynamic? /* String | null | ParsedUrlQuery */
}

external interface UrlUtil {
	var parse: ParseFunction
	var format: FormatFunction
	var resolve: ResolveFunction
}

external val url: UrlUtil

external interface URLFormatOptions {
	var auth: Boolean?
	var fragment: Boolean?
	var search: Boolean?
	var unicode: Boolean?
}

external interface UrlObject : UrlObjectCommon {
	var port: dynamic? /* undefined | string | number */
	var query: dynamic? /* string | null | ParsedUrlQueryInput */
}

external interface UrlObjectCommon {
	var auth: Boolean?
	var hash: String?
	var host: String?
	var hostname: String?
	var href: String?
	var path: String?
	var pathname: String?
	var protocol: String?
	var search: String?
	var slashes: String?
}

external interface UrlWithParsedQuery : Url {
	override var query: ParsedUrlQuery
}

external interface UrlWithStringQuery : Url {
	override var query: String?
}

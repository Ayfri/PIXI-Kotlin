@file:JsModule("@pixi/utils")

package typings.utils

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.khronos.webgl.Uint32Array
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.ImageData
import org.w3c.dom.Location
import org.w3c.dom.url.URL
import typings.IIndexed
import typings.Indexed
import typings.core.BaseTexture
import typings.core.IAutoDetectOptions
import typings.core.ITypedArray
import typings.core.Program
import typings.core.Resource
import kotlin.js.RegExp
import typings.Number

external val BaseTextureCache: Indexed<String, BaseTexture<Resource, IAutoDetectOptions>>

open external class CanvasRenderTarget(width: Number, height: Number, resolution: Number = definedExternally) {
	open var canvas: HTMLCanvasElement
	open var context: CanvasRenderingContext2D
	open var resolution: Number
	open var width: Number
	open var height: Number
	
	open fun clear()
	open fun resize(desiredWidth: Number, desiredHeight: Number)
	open fun destroy()
}

external fun clearTextureCache()
external fun correctBlendMode(blendMode: Number, premultiplied: Boolean): Number
external fun createIndicesForQuads(
	size: Number,
	outBuffer: Uint16Array = definedExternally
): dynamic /* Uint16Array | Uint32Array */

external fun createIndicesForQuads(
	size: Number,
	outBuffer: Uint32Array = definedExternally
): dynamic /* Uint16Array | Uint32Array */

external val DATA_URI: RegExp

external fun decomposeDataUri(dataUri: String): DecomposedDataUri

external interface DecomposedDataUri {
	var mediaType: String
	var subType: String
	var charset: String
	var encoding: String
	var data: String
}

external fun deprecation(version: String, message: String, ignoreDepth: Number = definedExternally)
external fun destroyTextureCache()
external fun determineCrossOrigin(url: String, loc: Location = definedExternally): String

external interface FormatFunction {
	operator fun invoke(URL: URL, options: URLFormatOptions = definedExternally): String
	operator fun invoke(urlObject: URLFormatOptions): String
	operator fun invoke(urlObject: String): String
}

external fun getBufferType(array: ITypedArray): BufferType?
external fun getResolutionOfUrl(url: String, default: Number = definedExternally): Number
external fun hex2rgb(hex: Number, out: Array<Number> = definedExternally): dynamic /* Array<Number> | Float32Array */
external fun hex2rgb(hex: Number, out: Float32Array = definedExternally): dynamic /* Array<Number> | Float32Array */
external fun hex2string(hex: Number): String
external fun interleaveTypedArrays(arrays: Array<PackedArray>, sizes: Array<Number>): Float32Array
external fun isPow2(v: Number): Boolean
external fun isWebGLSupported(): Boolean
external fun log2(v: Number): Number
external fun nextPow2(v: Number): Number

external interface ParsedUrlQuery : IIndexed<String, dynamic /* String | Array<String> */>

external interface ParsedUrlQueryInput : IIndexed<String, Any? /* unknown */>

external interface ParseFunction {
	operator fun invoke(urlStr: String): UrlWithStringQuery
	operator fun invoke(urlString: String, parseQueryString: Boolean?, slashesDenoteHost: Boolean = definedExternally): UrlWithStringQuery
	operator fun invoke(urlString: String, parseQueryString: Boolean, slashesDenoteHost: Boolean = definedExternally): Url
}

external val premultiplyBlendMode: Array<Array<Number>>

external fun premultiplyRgba(rg: Array<Number>, alpha: Number, out: Float32Array = definedExternally, premultiply: Boolean): Float32Array
external fun premultiplyRgba(rg: Float32Array, alpha: Number, out: Float32Array = definedExternally, premultiply: Boolean): Float32Array
external fun premultiplyTint(tint: Number, alpha: Number): Number
external fun premultiplyTintToRgba(tint: Number, alpha: Number, out: Float32Array, premultiply: Boolean = definedExternally): Float32Array

external val ProgramCache: Indexed<String, Program>

external fun removeItems(arr: Array<Any>, startIdx: Number, removeCount: Number)

external interface ResolveFunction {
	operator fun invoke(from: String, to: String): String
}

external fun rgb2hex(rgb: Array<Number>): Number
external fun rgb2hex(rgb: Float32Array): Number
external fun sayHello(type: String)

external fun sign(n: Number): Sign
external fun skipHello()
external fun string2Hex(string: String): Number

external interface TrimmedCanvas {
	var width: Number
	var height: Number
	var data: ImageData?
}

external fun trimCanvas(canvas: HTMLCanvasElement): TrimmedCanvas
external fun uid(): Number

external interface Url : UrlObjectCommon {
	var port: String?
	var query: dynamic? /* string | null | ParsedUrlQuery */
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

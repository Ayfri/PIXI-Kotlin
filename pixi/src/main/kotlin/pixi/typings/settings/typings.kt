@file:JsModule("@pixi/settings")

package pixi.typings.settings

import org.w3c.dom.HTMLCanvasElement
import pixi.externals.Color
import pixi.typings.constants.ENV
import pixi.typings.constants.GC_MODES
import pixi.typings.constants.MIPMAP_MODES
import pixi.typings.constants.MSAA_QUALITY
import pixi.typings.constants.PRECISION
import pixi.typings.constants.SCALE_MODES
import pixi.typings.constants.WRAP_MODES
import web.http.RequestInit
import web.http.Response
import webgl.WebGLRenderingContext
import kotlin.js.Promise
import kotlin.js.RegExp

external val BrowserAdapter: IAdapter

external interface GetNavigator {
	var userAgent: String
}

external interface IAdapter {
	fun createCanvas(width: Number = definedExternally, height: Number = definedExternally): HTMLCanvasElement
	var getWebGLRenderingContext: () -> WebGLRenderingContext
	var getNavigator: () -> GetNavigator
	var getBaseUrl: () -> String
	fun fetch(url: RequestInfo, options: RequestInit = definedExternally): Promise<Response>
}

external interface IRenderOptions {
	var view: HTMLCanvasElement
	var antialias: Boolean
	var autoDensity: Boolean
	var backgroundColor: Color
	var backgroundAlpha: Double
	var useContextAlpha: dynamic /* boolean | 'notMultiplied' */
	var clearBeforeRender: Boolean
	var preserveDrawingBuffer: Boolean
	var width: Int
	var height: Int
	var legacy: Boolean
}

external interface ISettings {
	var MIPMAP_TEXTURES: MIPMAP_MODES
	var ANISOTROPIC_LEVEL: Short
	var RESOLUTION: Double
	var FILTER_RESOLUTION: Double
	var FILTER_MULTISAMPLE: MSAA_QUALITY
	var SPRITE_MAX_TEXTURES: Short
	var SPRITE_BATCH_SIZE: Int
	var RENDER_OPTIONS: IRenderOptions
	var GC_MODE: GC_MODES
	var GC_MAX_IDLE: Int
	var GC_MAX_CHECK_COUNT: Int
	var WRAP_MODE: WRAP_MODES
	var SCALE_MODE: SCALE_MODES
	var PRECISION_VERTEX: PRECISION
	var PRECISION_FRAGMENT: PRECISION
	var CAN_UPLOAD_SAME_BUFFER: Boolean
	var CREATE_IMAGE_BITMAP: Boolean
	var ROUND_PIXELS: Boolean
	var RETINA_PREFIX: RegExp?
	var FAIL_IF_MAJOR_PERFORMANCE_CAVEAT: Boolean?
	var UPLOADS_PER_FRAME: Short?
	var SORTABLE_CHILDREN: Boolean?
	var PREFER_ENV: ENV?
	var STRICT_TEXTURE_CACHE: Boolean?
	var MESH_CANVAS_PADDING: Double?
	var TARGET_FPMS: Int?
}

external val isMobile: isMobileResult

external val settings: ISettings

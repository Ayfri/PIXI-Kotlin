@file:JsModule("@pixi/settings")

package typings.settings

import org.w3c.dom.HTMLCanvasElement
import typings.Number
import typings.constants.ENV
import typings.constants.GC_MODES
import typings.constants.MIPMAP_MODES
import typings.constants.MSAA_QUALITY
import typings.constants.PRECISION
import typings.constants.SCALE_MODES
import typings.constants.WRAP_MODES
import typings.isMobileResult
import kotlin.js.RegExp

external interface IRenderOptions {
	var view: HTMLCanvasElement
	var antialias: Boolean
	var autoDdensity: Boolean
	var backgroundColor: Number
	var backgroundAlpha: Number
	var useContextAlpha: dynamic /* boolean | 'notMultiplied' */
	var clearBeforeRender: Boolean
	var width: Number
	var height: Number
	var legacy: Boolean
}

external interface ISettings {
	var MIPMAP_TEXTURES: MIPMAP_MODES
	var ANISOTROPIC_LEVEL: Number
	var RESOLUTION: Number
	var FILTER_RESOLUTION: Number
	var FILTER_MULTISAMPLE: MSAA_QUALITY
	var SPRITE_MAX_TEXTURES: Number
	var SPRITE_BATCH_SIZE: Number
	var RENDER_OPTIONS: IRenderOptions
	var GC_MODE: GC_MODES
	var GC_MAX_IDLE: Number
	var GC_MAX_CHECK_COUNT: Number
	var WRAP_MODE: WRAP_MODES
	var SCALE_MODE: SCALE_MODES
	var PRECISION_VERTEX: PRECISION
	var PRECISION_FRAGMENT: PRECISION
	var CAN_UPLOAD_SAME_BUFFER: Boolean
	var CREATE_IMAGE_BITMAP: Boolean
	var ROUND_PIXELS: Boolean
	var RETINA_PREFIX: RegExp?
	var FAIL_IF_MAJOR_PERFORMANCE_CAVEAT: Boolean?
	var UPLOADS_PER_FRAME: Number?
	var SORTABLE_CHILDREN: Boolean?
	var PREFER_ENV: ENV?
	var STRICT_TEXTURE_CACHE: Boolean?
	var MESH_CANVAS_PADDING: Number?
	var TARGET_FPMS: Number?
}

external val isMobile: isMobileResult

external val settings: ISettings

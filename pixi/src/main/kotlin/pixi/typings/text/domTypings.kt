package pixi.typings.text

import org.khronos.webgl.WebGLContextAttributes
import org.w3c.dom.*
import org.w3c.dom.events.EventTarget
import org.w3c.files.Blob
import webgl.WebGLRenderingContextBase
import kotlin.js.Promise

external interface ImageEncodeOptions {
	var quality: Double?
	var type: String?
}

external interface OffscreenCanvasRenderingContext2D : CanvasCompositing, CanvasDrawImage, CanvasDrawPath, CanvasFillStrokeStyles, CanvasFilters, CanvasImageData,
	CanvasImageSmoothing, CanvasPath, CanvasPathDrawingStyles, CanvasRect, CanvasShadowStyles, CanvasState, CanvasText, CanvasTextDrawingStyles, CanvasTransform {
	var canvas: OffscreenCanvas
	fun commit()
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface OffscreenCanvas : EventTarget {
	var height: Int
	var width: Int
	fun convertToBlob(options: ImageEncodeOptions): Promise<Blob>
	fun getContext(contextId: String /* "2d" */, options: CanvasRenderingContext2DSettings = definedExternally): OffscreenCanvasRenderingContext2D?
	fun getContext(contextId: String /* "2d" | "bitmaprenderer" | "webgl" | "webgl2" */): dynamic /* WebGL2RenderingContext | OffscreenCanvasRenderingContext2D? | ImageBitmapRenderingContext? | WebGLRenderingContext? | WebGL2RenderingContext? */
	fun getContext(contextId: String /* "bitmaprenderer" */, options: ImageBitmapRenderingContextSettings = definedExternally): ImageBitmapRenderingContext?
	fun getContext(
		contextId: String /* "webgl" | "webgl2" */,
		options: WebGLContextAttributes = definedExternally
	): WebGLRenderingContextBase /* WebGLRenderingContext | WebGL2RenderingContext */
	
	fun getContext(
		contextId: String /* "2d" | "bitmaprenderer" | "webgl" | "webgl2" */,
		options: Any? = definedExternally
	): dynamic /* OffscreenCanvasRenderingContext2D? | ImageBitmapRenderingContext? | WebGLRenderingContext? | WebGL2RenderingContext? */
	
	fun transferToImageBitmap(): ImageBitmap
}

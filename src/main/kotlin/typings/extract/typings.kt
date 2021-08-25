@file:JsModule("@pixi/extract")

package typings.extract

import org.khronos.webgl.Uint8Array
import org.khronos.webgl.Uint8ClampedArray
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import typings.core.IRendererPlugin
import typings.core.RenderTexture
import typings.core.Renderer
import typings.display.DisplayObject

open external class Extract(renderer: Renderer) : IRendererPlugin {
	open fun image(target: DisplayObject, format: String, quality: Number): HTMLImageElement
	open fun image(target: DisplayObject, format: String): HTMLImageElement
	open fun image(target: DisplayObject): HTMLImageElement
	open fun image(target: RenderTexture, format: String, quality: Number): HTMLImageElement
	open fun image(target: RenderTexture, format: String): HTMLImageElement
	open fun image(target: RenderTexture): HTMLImageElement

	open fun base64(target: DisplayObject, format: String, quality: Number): String
	open fun base64(target: DisplayObject, format: String): String
	open fun base64(target: DisplayObject): String
	open fun base64(target: RenderTexture, format: String, quality: Number): String
	open fun base64(target: RenderTexture, format: String): String
	open fun base64(target: RenderTexture): String

	open fun canvas(target: DisplayObject): HTMLCanvasElement
	open fun canvas(target: RenderTexture): HTMLCanvasElement

	override fun destroy()

	companion object {
		fun arrayPostDivide(pixels: Array<Number>, out: Array<Number>)
		fun arrayPostDivide(pixels: Array<Number>, out: Uint8Array)
		fun arrayPostDivide(pixels: Array<Number>, out: Uint8ClampedArray)
		fun arrayPostDivide(pixels: Uint8Array, out: Array<Number>)
		fun arrayPostDivide(pixels: Uint8Array, out: Uint8Array)
		fun arrayPostDivide(pixels: Uint8Array, out: Uint8ClampedArray)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Array<Number>)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Uint8Array)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Uint8ClampedArray)
	}
}
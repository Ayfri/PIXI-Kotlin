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
	open fun image(target: DisplayObject, format: String = definedExternally, quality: Number = definedExternally): HTMLImageElement
	open fun image(target: RenderTexture, format: String = definedExternally, quality: Number = definedExternally): HTMLImageElement
	
	open fun base64(target: DisplayObject, format: String = definedExternally, quality: Number = definedExternally): String
	open fun base64(target: RenderTexture, format: String = definedExternally, quality: Number = definedExternally): String
	
	open fun canvas(target: DisplayObject): HTMLCanvasElement
	open fun canvas(target: RenderTexture): HTMLCanvasElement
	
	open fun pixels(target: DisplayObject = definedExternally): Uint8Array
	open fun pixels(target: RenderTexture = definedExternally): Uint8Array
	
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

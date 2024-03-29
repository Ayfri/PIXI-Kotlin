@file:JsModule("@pixi/extract")

package pixi.typings.extract

import org.khronos.webgl.Uint8Array
import org.khronos.webgl.Uint8ClampedArray
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import pixi.typings.core.IRendererPlugin
import pixi.typings.core.RenderTexture
import pixi.typings.core.Renderer
import pixi.typings.display.DisplayObject
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.math.Rectangle

@Deprecated("Use Rectangle instead, since pixi v6.5.0.", ReplaceWith("Rectangle"))
external interface PixelExtractOptions {
	var x: Number
	var t: Number
	var height: Number
	var resolution: Number
	var width: Number
}

open external class Extract(renderer: Renderer) : IRendererPlugin {
	open fun image(target: DisplayObject, format: String = definedExternally, quality: Double = definedExternally): HTMLImageElement
	open fun image(target: RenderTexture, format: String = definedExternally, quality: Double = definedExternally): HTMLImageElement
	
	open fun base64(target: DisplayObject, format: String = definedExternally, quality: Double = definedExternally): String
	open fun base64(target: RenderTexture, format: String = definedExternally, quality: Double = definedExternally): String
	
	open fun canvas(target: DisplayObject, frame: Rectangle = definedExternally): HTMLCanvasElement
	open fun canvas(target: RenderTexture, frame: Rectangle = definedExternally): HTMLCanvasElement
	
	open fun pixels(target: DisplayObject = definedExternally, frame: Rectangle = definedExternally): Uint8Array
	open fun pixels(target: RenderTexture = definedExternally, frame: Rectangle = definedExternally): Uint8Array
	open fun pixels(target: DisplayObject = definedExternally, frame: PixelExtractOptions = definedExternally): Uint8Array
	open fun pixels(target: RenderTexture = definedExternally, frame: PixelExtractOptions = definedExternally): Uint8Array
	
	override fun destroy()
	
	companion object {
		var extension: ExtensionMetadata
		
		fun arrayPostDivide(pixels: Array<Int>, out: Array<Int>)
		fun arrayPostDivide(pixels: Array<Int>, out: Uint8Array)
		fun arrayPostDivide(pixels: Array<Int>, out: Uint8ClampedArray)
		fun arrayPostDivide(pixels: Uint8Array, out: Array<Int>)
		fun arrayPostDivide(pixels: Uint8Array, out: Uint8Array)
		fun arrayPostDivide(pixels: Uint8Array, out: Uint8ClampedArray)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Array<Int>)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Uint8Array)
		fun arrayPostDivide(pixels: Uint8ClampedArray, out: Uint8ClampedArray)
	}
}

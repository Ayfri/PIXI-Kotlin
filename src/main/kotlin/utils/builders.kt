package utils

import org.w3c.dom.HTMLCanvasElement
import typings.app.Application
import typings.app.IApplicationOptions
import typings.core.IRenderingContext
import typings.core.WebGLPowerPreference

fun Application.Companion.create(block: ApplicationsOptions.() -> Unit) = Application(ApplicationsOptions().apply(block))

class ApplicationsOptions : IApplicationOptions {
	override var resizeTo: dynamic = null
	override var forceCanvas: Boolean? = null
	override var width: Number? = null
	override var height: Number? = null
	override var view: HTMLCanvasElement? = null
	override var useContextAlpha: dynamic = null
	override var transparent: Boolean? = null
	override var autoDensity: Boolean? = null
	override var antiAlias: Boolean? = null
	override var resolution: Number? = null
	override var preserveDrawingBuffer: Boolean? = null
	override var clearBeforeRender: Boolean? = null
	override var backgroundColor: Number? = null
	override var backgroundAlpha: Number? = null
	override var powerPreference: WebGLPowerPreference? = null
	override var context: IRenderingContext? = null
}

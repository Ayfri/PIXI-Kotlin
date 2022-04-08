@file:JsModule("@pixi/app")

package pixi.typings.app

import org.w3c.dom.HTMLCanvasElement
import pixi.typings.core.AbstractRenderer
import pixi.typings.core.IRendererOptionsAuto
import pixi.typings.display.Container
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.Rectangle

open external class Application(options: IApplicationOptions = definedExternally) {
	open var stage: Container
	open var renderer: AbstractRenderer
	open val view: HTMLCanvasElement
	open val screen: Rectangle
	
	open fun render()
	open fun destroy(removeView: Boolean = definedExternally, stageOptions: IDestroyOptions = definedExternally)
	open fun destroy(removeView: Boolean = definedExternally, stageOptions: Boolean = definedExternally)
	
	companion object {
		fun registerPlugin(plugin: IApplicationPlugin)
	}
}

external interface IApplicationOptions : IRendererOptionsAuto {
	var resizeTo: dynamic? /* undefined | Window | HTMLElement */
}

external interface IApplicationPlugin {
	fun init(options: IApplicationOptions)
	fun destroy()
}

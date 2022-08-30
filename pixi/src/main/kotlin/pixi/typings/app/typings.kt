@file:JsModule("@pixi/app")

package pixi.typings.app

import org.w3c.dom.HTMLCanvasElement
import pixi.typings.core.AbstractRenderer
import pixi.typings.core.IRendererOptionsAuto
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.display.IDestroyOptions
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.math.Rectangle

open external class Application(options: IApplicationOptions = definedExternally) {
	open var stage: Container<DisplayObject>
	open var renderer: AbstractRenderer
	open val view: HTMLCanvasElement
	open val screen: Rectangle
	
	open fun render()
	open fun destroy(removeView: Boolean = definedExternally, stageOptions: IDestroyOptions = definedExternally)
	open fun destroy(removeView: Boolean = definedExternally, stageOptions: Boolean = definedExternally)
	
	companion object {
		var _plugins: Array<IApplicationPlugin>
		
		@Deprecated("Use extensions.add(plugin) instead", ReplaceWith("extensions.add"))
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

external class ResizePlugin {
	companion object {
		var extension: ExtensionMetadata
		var resizeTo: dynamic /* Window | HTMLElement */
		var resize: () -> Unit
		var renderer: ResizeableRenderer
		var queueResize: () -> Unit
		
		fun init(options: IApplicationOptions = definedExternally)
		fun destroy()
	}
}
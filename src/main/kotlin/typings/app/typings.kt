@file:JsModule("@pixi/app")

package typings.app

import org.w3c.dom.HTMLCanvasElement
import typings.core.IRendererOptionsAuto
import typings.display.Container
import typings.display.IDestroyOptions
import typings.math.Rectangle

open external class Application(options: IApplicationOptions) {
	constructor()
    
    open var stage: Container
    open var renderer: dynamic /* AbstractRenderer | Renderer */
    open val view: HTMLCanvasElement
	open val screen: Rectangle

    open fun render()
    open fun destroy(removeView: Boolean, stageOptions: IDestroyOptions)
    open fun destroy(removeView: Boolean, stageOptions: Boolean)
    open fun destroy(removeView: Boolean)
    open fun destroy()

    open fun resize()
    open var resizeTo: dynamic /* Window | HTMLElement */
    
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
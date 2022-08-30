@file:JsModule("@pixi/ticker")

package pixi.typings.ticker

import pixi.typings.app.IApplicationOptions
import pixi.typings.extensions.ExtensionMetadata

open external class Ticker {
	open var autoStart: Boolean
	open var deltaTime: Double
	open var deltaMS: Double
	open var elapsedMS: Double
	open var lastTime: Double
	open var speed: Double
	open val started: Boolean
	
	open val count: Int
	open val FPS: Double
	open var minFPS: Double
	open var maxFPS: Int
	
	open fun <T /* = Any */> add(
		fn: TickerCallback<T>,
		context: T = definedExternally,
		priority: UPDATE_PRIORITY = definedExternally,
	): Ticker /* this */
	
	open fun <T /* = Any */> addOnce(
		fn: TickerCallback<T>,
		context: T = definedExternally,
		priority: UPDATE_PRIORITY = definedExternally,
	): Ticker /* this */
	
	open fun <T /* = Any */> remove(fn: TickerCallback<T>, context: T = definedExternally): Ticker /* this */
	open fun start()
	open fun stop()
	open fun destroy()
	open fun update(currentTime: Int = definedExternally)
	
	companion object {
		val shared: Ticker
		val system: Ticker
	}
}


open external class TickerPlugin {
	companion object {
		var extension: ExtensionMetadata
		
		var start: () -> Unit
		var stop: () -> Unit
		var _ticker: Ticker
		var ticker: Ticker
		fun init(options: IApplicationOptions = definedExternally)
		fun destroy()
	}
}

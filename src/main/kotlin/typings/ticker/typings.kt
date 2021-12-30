@file:JsModule("@pixi/ticker")

package typings.ticker

import typings.Number
import typings.app.IApplicationOptions

open external class Ticker {
	open var autoStart: Boolean
	open var deltaTime: Number
	open var deltaMS: Number
	open var elapsedMS: Number
	open var lastTime: Number
	open var speed: Number
	open val started: Boolean
	
	open val count: Number
	open val FPS: Number
	open var minFPS: Number
	open var maxFPS: Number
	
	open fun <T /* = Any */> add(
		fn: TickerCallback<T>,
		context: T = definedExternally,
		priority: UPDATE_PRIORITY = definedExternally
	): Ticker /* this */
	
	open fun <T /* = Any */> addOnce(
		fn: TickerCallback<T>,
		context: T = definedExternally,
		priority: UPDATE_PRIORITY = definedExternally
	): Ticker /* this */
	
	open fun <T /* = Any */> remove(fn: TickerCallback<T>, context: T = definedExternally): Ticker /* this */
	open fun start()
	open fun stop()
	open fun destroy()
	open fun update(currentTime: Number = definedExternally)
	
	companion object {
		val shared: Ticker
		val system: Ticker
	}
}


open external class TickerPlugin {
	companion object {
		var start: () -> Unit
		var stop: () -> Unit
		var _ticker: Ticker
		var ticker: Ticker
		fun init(options: IApplicationOptions = definedExternally)
		fun destroy()
	}
}

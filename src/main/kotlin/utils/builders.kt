package utils

import kotlinext.js.jso
import pixi.typings.app.Application
import pixi.typings.app.IApplicationOptions
import pixi.typings.loaders.Loader
import pixi.typings.loaders.OnCompleteSignal
import pixi.typings.ticker.Ticker
import pixi.typings.ticker.TickerCallback
import pixi.typings.ticker.TickerCallback2
import pixi.typings.ticker.UPDATE_PRIORITY

fun Application(block: IApplicationOptions.() -> Unit) = Application(jso(block))

fun Loader.load(fn: (Loader) -> Unit) = load(fn as OnCompleteSignal)
fun Loader.load(fn: () -> Unit) = load(fn as OnCompleteSignal)

fun Ticker.add(priority: UPDATE_PRIORITY = UPDATE_PRIORITY.NORMAL, fn: TickerCallback2) = add(fn as TickerCallback<Any?>, null, priority)
fun Ticker.addOnce(priority: UPDATE_PRIORITY = UPDATE_PRIORITY.NORMAL, fn: TickerCallback2) = addOnce(fn as TickerCallback<Any?>, null, priority)
fun <T> Ticker.remove(context: T? = null, fn: TickerCallback2) = remove(fn as TickerCallback<T?>, context)

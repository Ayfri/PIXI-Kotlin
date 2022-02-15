package pixi.externals.extensions

import pixi.typings.ticker.Ticker
import pixi.typings.ticker.TickerCallback
import pixi.typings.ticker.TickerCallback2
import pixi.typings.ticker.UPDATE_PRIORITY

class _Ticker

fun Ticker.add(priority: UPDATE_PRIORITY = UPDATE_PRIORITY.NORMAL, fn: TickerCallback2) = add(fn as TickerCallback<Any?>, null, priority)
fun Ticker.addOnce(priority: UPDATE_PRIORITY = UPDATE_PRIORITY.NORMAL, fn: TickerCallback2) = addOnce(fn as TickerCallback<Any?>, null, priority)
fun <T> Ticker.remove(context: T? = null, fn: TickerCallback2) = remove(fn as TickerCallback<T?>, context)

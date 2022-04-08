package pixi.externals.extensions

import pixi.typings.ListenerFn
import pixi.typings.utils.EventEmitter

fun EventEmitter.on(event: String, fn: ListenerFn) = on(event, fn, null)
fun EventEmitter.once(event: String, fn: ListenerFn) = once(event, fn, null)
fun EventEmitter.off(event: String, fn: ListenerFn) = off(event, fn, null)

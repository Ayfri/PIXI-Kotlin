package pixi.typings.utils

import kotlinx.js.Record
import org.khronos.webgl.ArrayBufferView
import pixi.typings.EventEmitter as EventEmitter2

typealias Dict<T> = Record<String, T>
typealias EventEmitter = EventEmitter2<String>
typealias PackedArray = ArrayBufferView /* Float32Array | Uint32Array | Int32Array | Uint8Array */
typealias ResolveFunction = (from: String, to: String) -> String

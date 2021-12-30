package typings.utils

import kotlinext.js.Record
import org.khronos.webgl.ArrayBufferView
import typings.EventEmitter as EventEmitter2

typealias Dict<T> = Record<String, T>
typealias EventEmitter = EventEmitter2<String>
typealias PackedArray = ArrayBufferView /* Float32Array | Uint32Array | Int32Array | Uint8Array */
typealias ResolveFunction = (from: String, to: String) -> String

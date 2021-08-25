package typings.utils

import kotlinext.js.Record
import typings.EventEmitter as EventEmitter2

typealias Dict<T> = Record<String, T>
typealias EventEmitter = EventEmitter2<String>
typealias PackedArray = Any /* Float32Array | Uint32Array | Int32Array | Uint8Array */
@file:Suppress("unused")

package typings

import seskar.js.JsInt
import seskar.js.JsUnion

@JsModule("resource-loader")
@JsNonModule
open external class Loader(baseUrl: String = definedExternally, concurrency: Number = definedExternally) {
	open var baseUrl: String
	open var progress: Number
	open var loading: Boolean
	open fun reset(): Loader /* this */
	open fun load(cb: OnCompleteSignalLoader = definedExternally): Loader /* this */
	open fun add(name: String, url: String, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(name: String, url: String): Loader /* this */
	open fun add(name: String, url: String, options: IAddOptions = definedExternally, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(name: String, url: String, options: IAddOptions = definedExternally): Loader /* this */
	open fun add(url: String, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(url: String): Loader /* this */
	open fun add(url: String, options: IAddOptions = definedExternally, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(url: String, options: IAddOptions = definedExternally): Loader /* this */
	open fun add(options: IAddOptions, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(options: IAddOptions): Loader /* this */
	open fun add(resources: Array<dynamic /* typings.IAddOptions | typings.getString */>, callback: OnCompleteSignal = definedExternally): Loader /* this */
	open fun add(resources: Array<dynamic /* typings.IAddOptions | typings.getString */>): Loader /* this */
	open fun use(fn: MiddlewareFn, priority: Number = definedExternally): Loader /* this */
	open fun destroy()
}

external interface IAddOptions {
	var name: String?
		get() = definedExternally
		set(value) = definedExternally
	var key: String?
		get() = definedExternally
		set(value) = definedExternally
	var url: String?
		get() = definedExternally
		set(value) = definedExternally
	var crossOrigin: dynamic /* typings.getString? | Boolean? */
		get() = definedExternally
		set(value) = definedExternally
	var timeout: Number?
		get() = definedExternally
		set(value) = definedExternally
	var loadType: LOAD_TYPE?
		get() = definedExternally
		set(value) = definedExternally
	var onComplete: OnCompleteSignal?
		get() = definedExternally
		set(value) = definedExternally
	var callback: OnCompleteSignal?
		get() = definedExternally
		set(value) = definedExternally
}

@JsUnion
external enum class LOAD_TYPE {
	@JsInt(1)
	XHR,
	
	@JsInt(2)
	IMAGE,
	
	@JsInt(3)
	AUDIO,
	
	@JsInt(4)
	VIDEO
}

external enum class TYPE {
	@JsInt(0)
	UNKNOWN,
	
	@JsInt(1)
	JSON,
	
	@JsInt(2)
	XML,
	
	@JsInt(3)
	IMAGE,
	
	@JsInt(4)
	AUDIO,
	
	@JsInt(5)
	VIDEO,
	
	@JsInt(6)
	TEXT
}

open external class Resource {
	open val name: String
	open val url: String
	open val extension: String
	open var timeout: Number
	open var loadType: LOAD_TYPE
	open val children: Array<Resource>
	open val type: TYPE
	open val progressChunk: Number
	open val isDataUrl: Boolean
	open val isComplete: Boolean
	open val isLoading: Boolean
	open fun complete()
	open fun abort(message: String)
	open fun load(cb: OnCompleteSignal = definedExternally)
}

typealias OnCompleteSignal = (resource: Resource) -> Unit
typealias OnCompleteSignalLoader = (loader: Loader, resources: dynamic) -> Unit
typealias MiddlewareFn = (resource: Resource, next: () -> Unit) -> Unit

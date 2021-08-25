@file:JsModule("@pixi/loaders")

package typings.loaders

import org.w3c.files.Blob
import org.w3c.xhr.XMLHttpRequest
import seskar.js.JsInt
import seskar.js.JsString
import seskar.js.JsUnion
import typings.*
import typings.app.IApplicationOptions
import typings.core.IBaseTextureOptions
import typings.core.Resource
import typings.core.Texture
import typings.utils.Dict

external object AppLoaderPlugin {
	var loader: Loader
	fun init(options: IApplicationOptions)
	fun init()
	fun destroy()
}

open external class AsyncQueue<TaskData> internal constructor(
	worker: (x: TaskData, next: () -> Unit) -> Unit,
	concurrency: Number
) {
	internal constructor(worker: (x: TaskData, next: () -> Unit) -> Unit)

	open var workers: Number
	open var concurrency: Number
	open var buffer: Number
	open var saturated: () -> Unit
	open var unsaturated: () -> Unit
	open var empty: () -> Unit
	open var drain: () -> Unit
	open var error: (err: Error, task: TaskData) -> Unit
	open var started: Boolean
	open var paused: Boolean
	open var _tasks: Array<AsyncQueueItem<TaskData>>
	open var process: () -> Unit
	open fun _next(task: AsyncQueueItem<TaskData>): VarArgFun<Any, Unit>
	open fun push(data: Any, callback: VarArgFun<Any, Unit>)
	open fun push(data: Any)
	open fun kill()
	open fun unshift(data: Any, callback: VarArgFun<Any, Unit>)
	open fun unshift(data: Any)
	open fun length(): Number
	open fun running(): Number
	open fun idle(): Boolean
	open fun pause()
	open fun resume()

	companion object {
		fun eachSeries(
			array: Array<Any>,
			iterator: (x: Any, next: (err: Any) -> Unit) -> Unit,
			callback: (err: Any) -> Unit,
			deferNext: Boolean
		)

		fun eachSeries(
			array: Array<Any>,
			iterator: (x: Any, next: (err: Any) -> Unit) -> Unit,
			callback: (err: Any) -> Unit
		)

		fun eachSeries(
			array: Array<Any>,
			iterator: (x: Any, next: (err: Any) -> Unit) -> Unit,
			callback: () -> Unit,
			deferNext: Boolean
		)

		fun eachSeries(array: Array<Any>, iterator: (x: Any, next: (err: Any) -> Unit) -> Unit, callback: () -> Unit)
		fun eachSeries(array: Array<Any>, iterator: (x: Any, next: (err: Any) -> Unit) -> Unit)
		fun eachSeries(
			array: Array<Any>,
			iterator: (x: Any, next: () -> Unit) -> Unit,
			callback: (err: Any) -> Unit,
			deferNext: Boolean
		)

		fun eachSeries(array: Array<Any>, iterator: (x: Any, next: () -> Unit) -> Unit, callback: (err: Any) -> Unit)
		fun eachSeries(
			array: Array<Any>,
			iterator: (x: Any, next: () -> Unit) -> Unit,
			callback: () -> Unit,
			deferNext: Boolean
		)

		fun eachSeries(array: Array<Any>, iterator: (x: Any, next: () -> Unit) -> Unit, callback: () -> Unit)
		fun eachSeries(array: Array<Any>, iterator: (x: Any, next: () -> Unit) -> Unit)
		fun queue(worker: (x: Any, next: VarArgFun<Any, Unit>) -> Unit, concurrency: Number): AsyncQueue<Any>
		fun queue(worker: (x: Any, next: VarArgFun<Any, Unit>) -> Unit): AsyncQueue<Any>
	}
}

open external class AsyncQueueItem<TaskData>(data: TaskData, callback: VarArgFun<Any, Unit>) {
	open var data: TaskData
	open var callback: VarArgFun<Any, Unit>
}

external interface IAddOptions {
	var name: String?
	var key: String?
	var url: String?
	var crossOrigin: dynamic? /* undefined | String | Boolean */
	var timeout: Number?
	var parentResource: LoaderResource?
	var loadType: LoaderResource.LOAD_TYPE?
	var xhrType: LoaderResource.XHR_RESPONSE_TYPE
	var onComplete: OnCompleteSignal?
	var callback: OnCompleteSignal?
	var metadata: IResourceMetadata?
}

external interface ILoaderAdd {
	operator fun invoke(
		name: String,
		url: String,
		options: IAddOptions = definedExternally,
		callback: OnCompleteSignal = definedExternally
	): Loader

	operator fun invoke(name: String, url: String, callback: OnCompleteSignal = definedExternally): Loader
	operator fun invoke(
		name: String,
		options: IAddOptions = definedExternally,
		callback: OnCompleteSignal = definedExternally
	): Loader

	operator fun invoke(name: String, callback: OnCompleteSignal = definedExternally): Loader
	operator fun invoke(resources: Array<IAddOptions>, callback: OnCompleteSignal = definedExternally): Loader
	operator fun invoke(resources: Array<String>, callback: OnCompleteSignal = definedExternally): Loader
	operator fun invoke(options: IAddOptions, callback: OnCompleteSignal = definedExternally): Loader
}

external interface ILoaderPlugin {
	val add: (() -> Unit)?
	val pre: ((resource: LoaderResource, next: VarArgFun<Any, Unit>) -> Unit)?
	val use: ((resource: LoaderResource, next: VarArgFun<Any, Unit>) -> Unit)?
}

external interface IResourceMetadata : IBaseTextureOptions<Any> {
	var loadElement: dynamic? /* HTMLImageElement | HTMLAudioElement | HTMLVideoElement*/
	var skipSource: Boolean?
	var mimeType: dynamic? /* string | string[] */
	var imageMetadata: IResourceMetadata?
}

open external class Loader(baseUrl: String, concurrency: Number) {
	constructor(baseUrl: String)
	constructor()

	open var baseUrl: String
	open var progress: Number
	open var loading: Boolean
	open var defaultQueryString: String
	open var _boundLoadResource: (r: LoaderResource, d: () -> Unit) -> Unit
	open var _queue: AsyncQueue<Any>
	open val resources: Dict<LoaderResource>
	open val onProgress: Signal<OnProgressSignal>
	open val onError: Signal<OnErrorSignal>
	open val onLoad: Signal<OnLoadSignal>
	open val onStart: Signal<OnStartSignal>
	open val onComplete: Signal<OnCompleteSignal>
	open val add: ILoaderAdd
	open var currency: Number

	protected open fun _add(
		name: String,
		url: String,
		options: IAddOptions,
		callback: OnCompleteSignalResource
	): Loader /* this */

	protected open fun _add(name: String, url: String, options: IAddOptions): Loader /* this */
	open fun pre(fn: ILoaderMiddleware)
	open fun use(fn: ILoaderMiddleware)
	open fun reset(): Loader /* this */
	open fun load(cb: OnCompleteSignal): Loader /* this */
	open fun load(): Loader /* this */
	open fun _loadResouce(resource: LoaderResource, dequeue: () -> Unit)
	open fun destroy()

	companion object {
		val shared: Loader
		fun registerPlugin(plugin: ILoaderPlugin): Loader
	}
}

open external class LoaderResource(name: String, url: Array<String>, options: LoaderOptions) {
	constructor(name: String, url: String, options: LoaderOptions)
	constructor(name: String, url: Array<String>)
	constructor(name: String, url: String)

	open var texture: Texture<Resource>?
	open var blob: Blob?
	open val name: String
	open val url: String
	open val extension: String
	open var data: Any
	open var crossOrigin: dynamic /* string | boolean */
	open var timeout: Number
	open var loadType: LOAD_TYPE
	open var xhrType: String
	open var metadata: IResourceMetadata
	open var error: Error
	open var xhr: XMLHttpRequest
	open val children: Array<LoaderResource>
	open val type: TYPE
	open val progressChunk: Number
	open val onStart: Signal<OnStartSignalResource>
	open val onProgress: Signal<OnProgressSignalResource>
	open val onComplete: Signal<OnCompleteSignalResource>
	open val onAfterMiddleware: Signal<OnCompleteSignalResource>
	open var _dequeue: Any
	open var _onLoadBinding: Any
	open val isDataUrl: Boolean
	open val isComplete: Boolean
	open val isLoading: Boolean
	open fun complete()
	open fun abort(message: String)
	open fun load(cb: OnCompleteSignal)
	open fun load()
	open fun _loadElement(type: String)
	open fun _determineCrossOrigin(url: String, loc: Any): String
	open fun _determineCrossOrigin(url: String): String
	open fun _getMimeFromXhrType(type: XHR_RESPONSE_TYPE)

	companion object {
		fun setExtensionLoadType(extname: String, loadType: LOAD_TYPE)
		fun setExtensionXhrType(extname: String, xhrType: XHR_RESPONSE_TYPE)
	}

	interface LoaderOptions {
		var crossOrigin: dynamic? /* string | boolean */
		var timeout: Number?
		var loadType: LOAD_TYPE?
		var xhrType: XHR_RESPONSE_TYPE?
		var metadata: IResourceMetadata?
	}

	@JsUnion
	enum class STATUS_FLAGS {
		@JsInt(0)
		NONE,

		@JsInt(1)
		DATA_URL,

		@JsInt(2)
		COMPLETE,

		@JsInt(4)
		LOADING
	}

	@JsUnion
	enum class TYPE {
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

	@JsUnion
	enum class LOAD_TYPE {
		@JsInt(1)
		XHR,

		@JsInt(2)
		IMAGE,

		@JsInt(3)
		AUDIO,

		@JsInt(4)
		VIDEO
	}

	@JsUnion
	enum class XHR_RESPONSE_TYPE {
		@JsString("text")
		DEFAULT,

		@JsString("arraybuffer")
		BUFFER,

		@JsString("blob")
		BLOB,

		@JsString("document")
		DOCUMENT,

		@JsString("json")
		JSON,

		@JsString("text")
		TEXT
	}

	val _loadTypeMap: Dict<Number>
	val _xhrTypeMap: Dict<XHR_RESPONSE_TYPE>
	val EMPTY_GIF: String
}


open external class Signal<CbType /* = VarArgFun<Any, Unit> */> {
	open var _head: SignalBending<CbType>
	open var _tail: SignalBending<CbType>
	open fun handlers(exists: Boolean): dynamic /* Array<SignalBending<CbType>> | Boolean */
	open fun handlers(): dynamic /* Array<SignalBending<CbType>> | Boolean */
	open fun has(node: SignalBending<CbType>): Boolean
	open fun dispatch(vararg args: Array<Any>): Boolean
	open fun add(fn: CbType, thisArg: Any): SignalBending<CbType>
	open fun add(fn: CbType): SignalBending<CbType>
	open fun once(fn: CbType, thisArg: Any): SignalBending<CbType>
	open fun once(fn: CbType): SignalBending<CbType>
	open fun detach(node: SignalBending<CbType>): Signal<CbType> /* this */
	open fun detachAll(): Signal<CbType> /* this */
}

open external class SignalBending<CbType>(fn: CbType, once: Boolean, thisArg: Any?) {
	constructor(fn: CbType, once: Boolean)

	open var _fn: Any
	open var _once: Boolean
	open var _next: SignalBending<CbType>
	open var _prev: SignalBending<CbType>
	open var _owner: Signal<CbType>
	open var _thisArg: Any

	open fun detach(): Boolean
}

@Suppress("ABSTRACT_MEMBER_NOT_IMPLEMENTED")
open external class TextureLoader : ILoaderPlugin {
	companion object {
		fun add()
		fun use(resource: LoaderResource, next: VarArgFun<Any, Unit>)
	}
}
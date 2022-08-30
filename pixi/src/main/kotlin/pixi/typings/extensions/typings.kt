@file:JsModule("@pixi/extensions")

package pixi.typings.extensions

import kotlinx.js.Record

external interface ExtensionFormat : ExtensionFormatLoose {
	override var type: Array<ExtensionType>
}

external interface ExtensionFormatLoose {
	var type: dynamic /* ExtensionType | Array<ExtensionType> */
	var name: String?
	var ref: Any
}

external interface ExtensionMetadataDetails {
	var type: dynamic /* ExtensionType | Array<ExtensionType> */
	var name: String?
}

external object extensions {
	var _addHandlers: Record<ExtensionType, ExtensionHandler>
	var _removeHandlers: Record<ExtensionType, ExtensionHandler>
	var _queue: Record<ExtensionType, Array<ExtensionFormat>>
	
	fun remove(vararg extensions: Array<ExtensionFormatLoose>): Any
	fun remove(vararg extensions: Array<Any>): Any
	fun add(vararg extensions: Array<ExtensionFormatLoose>): Any
	fun add(vararg extensions: Array<Any>): Any
	fun handle(type: ExtensionType, onAdd: ExtensionHandler, onRemove: ExtensionHandler): Any
	fun handleByMap(type: ExtensionType, map: Record<String, Any>): Any
	fun handleByList(type: ExtensionType, list: Array<Any>): Any
}


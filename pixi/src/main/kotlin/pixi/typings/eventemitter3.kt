package pixi.typings

@JsModule("eventemitter3")
open external class EventEmitter<EventTypes : String> {
	open fun eventNames(): Array<EventTypes>
	open fun listeners(event: EventTypes): Array<ListenerFn>
	open fun listenerCount(event: EventTypes): Int
	open fun emit(event: EventTypes, vararg args: Array<Any?>): Boolean
	open fun on(event: EventTypes, fn: ListenerFn, context: Any? = definedExternally): EventEmitter<EventTypes> /* this */
	open fun addListener(event: EventTypes, fn: ListenerFn, context: Any? = definedExternally): EventEmitter<EventTypes> /* this */
	open fun once(event: EventTypes, fn: ListenerFn, context: Any? = definedExternally): EventEmitter<EventTypes> /* this */
	open fun removeListener(
		event: EventTypes,
		fn: ListenerFn = definedExternally,
		context: Any? = definedExternally,
		once: Boolean = definedExternally,
	): EventEmitter<EventTypes> /* this */
	
	open fun off(event: EventTypes, fn: ListenerFn, context: Any? = definedExternally, once: Boolean = definedExternally): EventEmitter<EventTypes> /* this */
	open fun removeAllListeners(event: EventTypes = definedExternally): EventEmitter<EventTypes> /* this */
	
	companion object {
		var prefixed: dynamic /* string | boolean */
		val EventEmitter: EventEmitterStatic
	}
	
	interface EventEmitterStatic {
		operator fun invoke(): EventEmitter<String>
	}
}

typealias ListenerFn = VarArgFun<Any?, Unit>

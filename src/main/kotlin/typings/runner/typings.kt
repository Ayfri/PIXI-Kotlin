@file:JsModule("@pixi/runner")

package typings.runner

open external class Runner(name: String) {
	open var items: Array<Any?>
	
	open val empty: Boolean
	open val name: String
	
	open fun emit(
		a0: Any? /* unknown */ = definedExternally,
		a1: Any? /* unknown */ = definedExternally,
		a2: Any? /* unknown */ = definedExternally,
		a3: Any? /* unknown */ = definedExternally,
		a4: Any? /* unknown */ = definedExternally,
		a5: Any? /* unknown */ = definedExternally,
		a6: Any? /* unknown */ = definedExternally,
		a7: Any? /* unknown */ = definedExternally
	): Runner /* this */
	
	open fun add(item: Any? /* unknown */): Runner /* this */
	open fun remove(item: Any? /* unknown */): Runner /* this */
	open fun contains(item: Any?): Boolean
	open fun removeAll(): Runner /* this */
	open fun destroy()
}

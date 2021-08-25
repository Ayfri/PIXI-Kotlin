@file:JsModule("@pixi/runner")

package typings.runner

open external class Runner(name: String) {
	open var items: Array<Any>
	open val empty: Boolean
	open val name: String

	open fun emit(
		a0: Any? /* unknown */,
		a1: Any? /* unknown */,
		a2: Any? /* unknown */,
		a3: Any? /* unknown */,
		a4: Any? /* unknown */,
		a5: Any? /* unknown */,
		a6: Any? /* unknown */,
		a7: Any? /* unknown */
	): Runner /* this */

	open fun add(item: Any? /* unknown */): Runner /* this */
	open fun remove(item: Any? /* unknown */): Runner /* this */
	open fun contains(item: Any?): Boolean
	open fun removeAll(): Runner /* this */
	open fun destroy()
}

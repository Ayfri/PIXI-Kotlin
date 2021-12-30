package pixi.typings

external fun require(module: dynamic): dynamic

typealias Number = Double
typealias VarArgFun<E, R> = (items: Array<out E>) -> R

operator fun <E, R> VarArgFun<E, R>.invoke(vararg items: E): R = this(items)

open external class Object<K : Any?, V : Any?> {
	operator fun get(key: K): V
	operator fun set(key: K, value: V)
}

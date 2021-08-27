package typings

typealias VarArgFun<E, R> = (items: Array<out E>) -> R

operator fun <E, R> VarArgFun<E, R>.invoke(vararg items: E): R = this(items)

external interface IIndexed<K : Any?, V : Any?> {
	operator fun get(key: K): V
	operator fun set(key: K, value: V)
}

open external class Indexed<K : Any?, V : Any?> {
	operator fun get(key: K): V
	operator fun set(key: K, value: V)
}
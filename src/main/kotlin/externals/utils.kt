package externals

interface Tuple<T1, T2, T3> {
	operator fun component1(): T1
	operator fun component2(): T2
	operator fun component3(): T3
}

package pixi.externals.extensions

import pixi.typings.math.IPointData
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Point
import kotlin.math.abs
import kotlin.math.sqrt

val IPointData.length get() = sqrt(x * x + y * y)
val IPointData.squaredLength get() = x * x + y * y
val IPointData.xy get() = x to y

operator fun <T : IPointData> T.not(): T {
	x = -x
	y = -y
	return this
}

operator fun <T : IPointData> T.unaryMinus(): T {
	x = -x
	y = -y
	return this
}

operator fun <T : IPointData> T.unaryPlus(): T {
	x = abs(x)
	y = abs(y)
	return this
}

operator fun <T : IPointData> T.compareTo(other: IPointData) = squaredLength.compareTo(other.squaredLength)

operator fun <T : IPointData> T.rangeTo(other: IPointData) = Rectangle(this, other)

operator fun <T : IPointData> T.get(index: Int): Number = when (index) {
	0 -> x
	1 -> y
	else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
}

operator fun <T : IPointData> T.set(index: Int, value: Number) {
	when (index) {
		0 -> x = value.toDouble()
		1 -> y = value.toDouble()
		else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
	}
}

fun <T : IPointData> T.abs() = +this

infix fun IPointData.distanceTo(other: IPointData) = sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y))

infix fun IPointData.dot(other: IPointData) = x * other.x + y * other.y

fun <T : IPointData> T.negate() = -this

fun <T : IPointData> T.normalize(): T {
	val len = length
	if (len > 0) {
		x /= len
		y /= len
	}
	return this
}

fun IPointData.reset() {
	x = 0.0
	y = 0.0
}

fun <T : Any> IPointData.toObservable(context: T.() -> Unit = {}, scope: T? = null) = ObservablePoint(context, scope ?: null.unsafeCast<T>(), x, y)
fun IPointData.toObservable() = ObservablePoint({}, null.asDynamic(), x, y)
fun IPointData.toPoint() = Point(x, y)

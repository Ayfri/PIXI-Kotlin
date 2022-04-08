package pixi.externals.extensions

import pixi.typings.math.IPointData
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Point
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.sqrt

val IPointData.length get() = sqrt(x * x + y * y)
val IPointData.squaredLength get() = x * x + y * y
val IPointData.xy get() = Pair(x, y)

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

operator fun <T : IPointData> T.plus(other: IPointData): T {
	x += other.x
	y += other.y
	return this
}

operator fun <T : IPointData> T.plus(factor: Number): T {
	x += factor.toDouble()
	y += factor.toDouble()
	return this
}

operator fun IPointData.plusAssign(other: IPointData) {
	this + other
}

operator fun IPointData.plusAssign(factor: Number) {
	this + factor
}

operator fun <T : IPointData> T.minus(other: IPointData): T {
	x -= other.x
	y -= other.y
	return this
}

operator fun <T : IPointData> T.minus(factor: Number): T {
	x -= factor.toDouble()
	y -= factor.toDouble()
	return this
}

operator fun IPointData.minusAssign(other: IPointData) {
	this - other
}

operator fun <T : IPointData> T.minusAssign(factor: Number) {
	this - factor
}

operator fun <T : IPointData> T.times(other: IPointData): T {
	x *= other.x
	y *= other.y
	return this
}

operator fun <T : IPointData> T.times(factor: Number): T {
	x *= factor.toDouble()
	y *= factor.toDouble()
	return this
}

operator fun IPointData.timesAssign(other: IPointData) {
	this * other
}

operator fun <T : IPointData> T.timesAssign(factor: Number) {
	this * factor
}

operator fun <T : IPointData> T.div(other: IPointData): T {
	x /= other.x
	y /= other.y
	return this
}

operator fun <T : IPointData> T.div(factor: Number): T {
	x /= factor.toDouble()
	y /= factor.toDouble()
	return this
}

operator fun <T : IPointData> T.divAssign(other: IPointData) {
	this / other
}

operator fun <T : IPointData> T.divAssign(factor: Number) {
	this / factor
}

operator fun <T : IPointData> T.rem(other: IPointData): T {
	x %= other.x
	y %= other.y
	return this
}

operator fun <T : IPointData> T.rem(factor: Number): T {
	x %= factor.toDouble()
	y %= factor.toDouble()
	return this
}

operator fun <T : IPointData> T.remAssign(other: IPointData) {
	this % other
}

operator fun <T : IPointData> T.remAssign(factor: Number) {
	this % factor
}

operator fun <T : IPointData> T.compareTo(other: IPointData) = squaredLength.compareTo(other.squaredLength)

operator fun <T : IPointData> T.rangeTo(other: IPointData) = Rectangle(this, other)

operator fun <T : IPointData> T.get(index: Int): Number {
	return when (index) {
		0 -> x
		1 -> y
		else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
	}
}

operator fun <T : IPointData> T.set(index: Int, value: Number) {
	when (index) {
		0 -> x = value.toDouble()
		1 -> y = value.toDouble()
		else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
	}
}


fun <T : IPointData> T.abs() = +this

fun IPointData.ceil() {
	x = ceil(x)
	y = ceil(y)
}

infix fun IPointData.distanceTo(other: IPointData) = sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y))

infix fun IPointData.dot(other: IPointData) = x * other.x + y * other.y

fun IPointData.floor() {
	x = floor(x)
	y = floor(y)
}

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

fun IPointData.round() {
	x = x.roundToInt().toDouble()
	y = y.roundToInt().toDouble()
}

fun <T : Any> IPointData.toObservable(context: T.() -> Unit = {}, scope: T? = null) = ObservablePoint(context, scope ?: null.asDynamic(), x, y)
fun IPointData.toObservable() = ObservablePoint({}, null.asDynamic(), x, y)
fun IPointData.toPoint() = Point(x, y)

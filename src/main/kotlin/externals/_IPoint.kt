package externals

import pixi.typings.math.IPoint
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.sqrt

val IPoint.length get() = sqrt(x * x + y * y)
val IPoint.squaredLength get() = x * x + y * y
val IPoint.xy get() = Pair(x, y)

operator fun <T : IPoint> T.not(): T {
	x = -x
	y = -y
	return this
}

operator fun <T : IPoint> T.unaryMinus(): T {
	x = -x
	y = -y
	return this
}

operator fun <T : IPoint> T.unaryPlus(): T {
	x = abs(x)
	y = abs(y)
	return this
}

operator fun <T : IPoint> T.plus(other: IPoint): T {
	x += other.x
	y += other.y
	return this
}

operator fun <T : IPoint> T.plus(factor: Double): T {
	x += factor
	y += factor
	return this
}

operator fun IPoint.plusAssign(other: IPoint) {
	this + other
}

operator fun IPoint.plusAssign(factor: Double) {
	this + factor
}

operator fun <T : IPoint> T.minus(other: IPoint): T {
	x -= other.x
	y -= other.y
	return this
}

operator fun <T : IPoint> T.minus(factor: Double): T {
	x -= factor
	y -= factor
	return this
}

operator fun IPoint.minusAssign(other: IPoint) {
	this - other
}

operator fun <T : IPoint> T.minusAssign(factor: Double) {
	this - factor
}

operator fun <T : IPoint> T.times(other: IPoint): T {
	x *= other.x
	y *= other.y
	return this
}

operator fun <T : IPoint> T.times(factor: Double): T {
	x *= factor
	y *= factor
	return this
}

operator fun IPoint.timesAssign(other: IPoint) {
	this * other
}

operator fun <T : IPoint> T.timesAssign(factor: Double) {
	this * factor
}

operator fun <T : IPoint> T.div(other: IPoint): T {
	x /= other.x
	y /= other.y
	return this
}

operator fun <T : IPoint> T.div(factor: Double): T {
	x /= factor
	y /= factor
	return this
}

operator fun <T : IPoint> T.divAssign(other: IPoint) {
	this / other
}

operator fun <T : IPoint> T.divAssign(factor: Double) {
	this / factor
}

operator fun <T : IPoint> T.rem(other: IPoint): T {
	x %= other.x
	y %= other.y
	return this
}

operator fun <T : IPoint> T.rem(factor: Double): T {
	x %= factor
	y %= factor
	return this
}

operator fun <T : IPoint> T.remAssign(other: IPoint) {
	this % other
}

operator fun <T : IPoint> T.remAssign(factor: Double) {
	this % factor
}

operator fun <T : IPoint> T.compareTo(other: IPoint) = squaredLength.compareTo(other.squaredLength)

operator fun <T : IPoint> T.rangeTo(other: IPoint) = Rectangle(this, other)

operator fun <T : IPoint> T.get(index: Int): Double {
	return when (index) {
		0 -> x
		1 -> y
		else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
	}
}

operator fun <T : IPoint> T.set(index: Int, value: Double) {
	when (index) {
		0 -> x = value
		1 -> y = value
		else -> throw IndexOutOfBoundsException("Index must be 0 or 1")
	}
}


fun <T : IPoint> T.abs() = +this

fun IPoint.ceil() {
	x = ceil(x)
	y = ceil(y)
}

infix fun IPoint.distanceTo(other: IPoint) = sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y))

infix fun IPoint.dot(other: IPoint) = x * other.x + y * other.y

fun IPoint.floor() {
	x = floor(x)
	y = floor(y)
}

fun <T : IPoint> T.negate() = -this

fun <T : IPoint> T.normalize(): T {
	val len = length
	if (len > 0) {
		x /= len
		y /= len
	}
	return this
}

fun IPoint.reset() = set()

fun IPoint.round() {
	x = x.roundToInt().toDouble()
	y = y.roundToInt().toDouble()
}

package pixi.externals.extensions

import pixi.typings.math.IPointData
import pixi.typings.math.Point
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

operator fun Point.plus(other: IPointData) = Point(x + other.x, y + other.y)
operator fun Point.plus(factor: Number) = Point(x + factor.unsafeCast<Double>(), y + factor.unsafeCast<Double>())

operator fun Point.minus(other: IPointData) = Point(x - other.x, y - other.y)
operator fun Point.minus(factor: Number) = Point(x - factor.unsafeCast<Double>(), y - factor.unsafeCast<Double>())

operator fun Point.times(other: IPointData) = Point(x * other.x, y * other.y)
operator fun Point.times(factor: Number) = Point(x * factor.unsafeCast<Double>(), y * factor.unsafeCast<Double>())

operator fun Point.div(other: IPointData) = Point(x / other.x, y / other.y)
operator fun Point.div(factor: Number) = Point(x / factor.unsafeCast<Double>(), y / factor.unsafeCast<Double>())

operator fun Point.rem(other: IPointData) = Point(x % other.x, y % other.y)
operator fun Point.rem(factor: Number) = Point(x % factor.unsafeCast<Double>(), y % factor.unsafeCast<Double>())

fun Point.floored() = Point(floor(x), floor(y))
fun Point.ceiled() = Point(ceil(x), ceil(y))
fun Point.rounded() = Point(x.roundToInt().unsafeCast<Double>(), y.roundToInt().unsafeCast<Double>())

fun Point.max(vararg points: IPointData) = Point(maxOf(x, *points.map { it.x }.toDoubleArray()), maxOf(y, *points.map { it.y }.toDoubleArray()))
fun Point.min(vararg points: IPointData) = Point(minOf(x, *points.map { it.x }.toDoubleArray()), minOf(y, *points.map { it.y }.toDoubleArray()))
fun Point.clamp(min: Number, max: Number) = Point(x.coerceIn(min.unsafeCast<Double>(), max.unsafeCast<Double>()), y.coerceIn(min.unsafeCast<Double>(), max.unsafeCast<Double>()))
fun Point.clamp(min: IPointData, max: IPointData) = Point(x.coerceIn(min.x, max.x), y.coerceIn(min.y, max.y))

fun Point.middle(other: IPointData) = Point((x + other.x) / 2, (y + other.y) / 2)

fun Point.normalize(): Point {
	val len = length
	
	return if (len != 0.0) Point(x / len, y / len)
	else Point(0.0, 0.0)
}
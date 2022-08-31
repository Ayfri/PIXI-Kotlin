package pixi.externals.extensions

import pixi.typings.math.IPointData
import pixi.typings.math.ObservablePoint
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

operator fun <T> ObservablePoint<T>.plus(other: IPointData) = clone().set(x + other.x, y + other.y)
operator fun <T> ObservablePoint<T>.plus(factor: Number) = clone().set(x + factor.unsafeCast<Double>(), y + factor.unsafeCast<Double>())

operator fun <T> ObservablePoint<T>.minus(other: IPointData) = clone().set(x - other.x, y - other.y)
operator fun <T> ObservablePoint<T>.minus(factor: Number) = clone().set(x - factor.unsafeCast<Double>(), y - factor.unsafeCast<Double>())

operator fun <T> ObservablePoint<T>.times(other: IPointData) = clone().set(x * other.x, y * other.y)
operator fun <T> ObservablePoint<T>.times(factor: Number) = clone().set(x * factor.unsafeCast<Double>(), y * factor.unsafeCast<Double>())

operator fun <T> ObservablePoint<T>.div(other: IPointData) = clone().set(x / other.x, y / other.y)
operator fun <T> ObservablePoint<T>.div(factor: Number) = clone().set(x / factor.unsafeCast<Double>(), y / factor.unsafeCast<Double>())

operator fun <T> ObservablePoint<T>.rem(other: IPointData) = clone().set(x % other.x, y % other.y)
operator fun <T> ObservablePoint<T>.rem(factor: Number) = clone().set(x % factor.unsafeCast<Double>(), y % factor.unsafeCast<Double>())

fun <T> ObservablePoint<T>.floored() = clone().set(floor(x), floor(y))
fun <T> ObservablePoint<T>.ceiled() = clone().set(ceil(x), ceil(y))
fun <T> ObservablePoint<T>.rounded() = clone().set(x.roundToInt().unsafeCast<Double>(), y.roundToInt().unsafeCast<Double>())


fun <T> ObservablePoint<T>.max(vararg points: IPointData) = clone().set(maxOf(x, *points.map { it.x }.toDoubleArray()), maxOf(y, *points.map { it.y }.toDoubleArray()))
fun <T> ObservablePoint<T>.min(vararg points: IPointData) = clone().set(minOf(x, *points.map { it.x }.toDoubleArray()), minOf(y, *points.map { it.y }.toDoubleArray()))
fun <T> ObservablePoint<T>.clamp(min: Number, max: Number) = clone().set(x.coerceIn(min.unsafeCast<Double>(), max.unsafeCast<Double>()), y.coerceIn(min.unsafeCast<Double>(), max.unsafeCast<Double>()))
fun <T> ObservablePoint<T>.clamp(min: IPointData, max: IPointData) = clone().set(x.coerceIn(min.x, max.x), y.coerceIn(min.y, max.y))

fun <T> ObservablePoint<T>.middle(other: IPointData) = clone().set(x + (other.x - x) / 2, y + (other.y - y) / 2)

fun <T> ObservablePoint<T>.normalize(): ObservablePoint<T> {
	val len = length
	
	return if (len != 0.0) clone().set(x / len, y / len)
	else clone().set(0.0, 0.0)
}
package pixi.externals.extensions

import org.w3c.dom.Window
import pixi.typings.display.Container
import pixi.typings.math.IPoint
import pixi.typings.math.ISize
import pixi.typings.math.Rectangle
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt

val Rectangle.halfWidth get() = width / 2
val Rectangle.halfHeight get() = height / 2

operator fun Rectangle.not() {
	x = -x
	y = -y
	width = -width
	height = -height
}

operator fun Rectangle.unaryMinus(): Rectangle = -this

operator fun Rectangle.unaryPlus() {
	x = abs(x)
	y = abs(y)
	width = abs(width)
	height = abs(height)
}

operator fun Rectangle.plus(other: Rectangle): Rectangle {
	x -= other.x
	y -= other.y
	width += other.width
	height += other.height
	return this
}

operator fun Rectangle.plusAssign(other: Rectangle) {
	this + other
}

operator fun Rectangle.minus(other: Rectangle): Rectangle {
	x += other.x
	y += other.y
	width -= other.width
	height -= other.height
	return this
}

operator fun Rectangle.minusAssign(other: Rectangle) {
	this - other
}

operator fun Rectangle.times(other: Rectangle): Rectangle {
	x *= other.x
	y *= other.y
	width *= other.width
	height *= other.height
	return this
}

operator fun Rectangle.times(factor: Double) {
	x *= factor
	y *= factor
	width *= factor
	height *= factor
}

operator fun Rectangle.timesAssign(other: Rectangle) {
	this * other
}

operator fun Rectangle.timesAssign(factor: Double) {
	this * factor
}

operator fun Rectangle.div(other: Rectangle) {
	x /= other.x
	y /= other.y
	width /= other.width
	height /= other.height
}

operator fun Rectangle.div(factor: Double) {
	x /= factor
	y /= factor
	width /= factor
	height /= factor
}

operator fun Rectangle.divAssign(other: Rectangle) {
	this / other
}

operator fun Rectangle.divAssign(factor: Double) {
	this / factor
}

operator fun Rectangle.rem(other: Rectangle) {
	x %= other.x
	y %= other.y
	width %= other.width
	height %= other.height
}

operator fun Rectangle.rem(factor: Double) {
	x %= factor
	y %= factor
	width %= factor
	height %= factor
}

operator fun Rectangle.remAssign(other: Rectangle) {
	this % other
}

operator fun Rectangle.remAssign(factor: Double) {
	this % factor
}

operator fun Rectangle.rangeTo(other: Rectangle): Rectangle {
	x = x.coerceIn(other.x, other.x + other.width)
	y = y.coerceIn(other.y, other.y + other.height)
	width = width.coerceIn(0.0, other.width)
	height = height.coerceIn(0.0, other.height)
	return this
}

operator fun Rectangle.contains(other: Rectangle): Boolean = x <= other.x && other.x + other.width <= x + width && y <= other.y && other.y + other.height <= y + height

operator fun Rectangle.contains(point: IPoint) = contains(point.x, point.y)


fun Rectangle.abs() = +this

fun Rectangle.ceil() {
	x = kotlin.math.ceil(x)
	y = kotlin.math.ceil(y)
	width = kotlin.math.ceil(width)
	height = kotlin.math.ceil(height)
}

infix fun Rectangle.collidesWith(other: Rectangle) = x + width >= other.x && x <= other.x + other.width && y + height >= other.y && y <= other.y + other.height

fun Rectangle.floor() {
	x = floor(x)
	y = floor(y)
	width = floor(width)
	height = floor(height)
}

fun Rectangle.inflate(x: Double, y: Double) = Rectangle(this.x - x, this.y - y, this.width + x * 2, this.height + y * 2)

fun Rectangle.intersectSegment(start: IPoint, end: IPoint): Boolean {
	val x1 = start.x
	val y1 = start.y
	val x2 = end.x
	val y2 = end.y
	val x3 = x
	val y3 = y
	val x4 = x + width
	val y4 = y + height
	
	val denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4)
	if (denominator == 0.0) return false
	
	val ua = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / denominator
	val ub = ((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / denominator
	
	return ua in 0.0..1.0 && ub >= 0.0 && ub <= 1.0
}

infix fun Rectangle.maxTo(other: Rectangle) = enlarge(other)

infix fun Rectangle.minTo(other: Rectangle) = fit(other)

fun Rectangle.negate() = -this

fun Rectangle.reset() {
	x = 0.0
	y = 0.0
	width = 0.0
	height = 0.0
}

fun Rectangle.resetPosition() = setPosition(0.0, 0.0)

fun Rectangle.resetSize() = setSize(0.0, 0.0)

fun Rectangle.round() {
	x = x.roundToInt().toDouble()
	y = y.roundToInt().toDouble()
	width = width.roundToInt().toDouble()
	height = height.roundToInt().toDouble()
}

fun Rectangle.setPosition(x: Double, y: Double) {
	this.x = x
	this.y = y
}

fun Rectangle.setPosition(position: IPoint) {
	x = position.x
	y = position.y
}

fun Rectangle.setSize(width: Double, height: Double) {
	this.width = width
	this.height = height
}

fun Rectangle.setSize(size: ISize) {
	width = size.width
	height = size.height
}

fun Rectangle.setSize(point: IPoint) {
	width = point.x
	height = point.y
}

fun Rectangle(first: IPoint, second: IPoint) = Rectangle(first.x, first.y, second.x - first.x, second.y - first.y)

fun Rectangle(container: Container) = Rectangle(container.x, container.y, container.width, container.height)

fun Rectangle(window: Window) = Rectangle(0.0, 0.0, window.innerWidth.toDouble(), window.innerHeight.toDouble())

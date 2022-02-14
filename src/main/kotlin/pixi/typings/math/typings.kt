@file:JsModule("@pixi/math")

package pixi.typings.math

import org.khronos.webgl.Float32Array

open external class Circle(x: Double = definedExternally, y: Double = definedExternally, radius: Double = definedExternally) {
	open var x: Double
	open var y: Double
	open var radius: Double
	open val type: SHAPES
	open fun clone(): Circle
	open fun contains(x: Double, y: Double): Boolean
	open fun getBounds(): Rectangle
	override fun toString(): String
}

external val DEG_TO_RAD: Double

open external class Ellipse(x: Double = definedExternally, y: Double = definedExternally, width: Double = definedExternally, height: Double = definedExternally) {
	open var x: Double
	open var y: Double
	open var width: Double
	open var height: Double
	open val type: SHAPES
	open fun clone(): Circle
	open fun contains(x: Double, y: Double): Boolean
	open fun getBounds(): Rectangle
	override fun toString(): String
}

external object groupD8 {
	var E: Short
	var SE: Short
	var S: Short
	var SW: Short
	var W: Short
	var NW: Short
	var N: Short
	var NE: Short
	var MIRROR_VERTICAL: Short
	var MAIN_DIAGONAL: Short
	var MIRROR_HORIZONTAL: Short
	var REVERSE_DIAGONAL: Short
	var uX: (ind: GD8Symmetry) -> GD8Symmetry
	var uY: (ind: GD8Symmetry) -> GD8Symmetry
	var vX: (ind: GD8Symmetry) -> GD8Symmetry
	var vY: (ind: GD8Symmetry) -> GD8Symmetry
	var inv: (rotation: GD8Symmetry) -> GD8Symmetry
	var add: (rotationSecond: GD8Symmetry, rotationFirst: GD8Symmetry) -> GD8Symmetry
	var sub: (rotationSecond: GD8Symmetry, rotationFirst: GD8Symmetry) -> GD8Symmetry
	var rotate180: (rotation: Short) -> Short
	var isVertical: (rotation: GD8Symmetry) -> Boolean
	var byDirection: (dx: Short, dy: Short) -> GD8Symmetry
	var matrixAppendRotationInv: (matrix: Matrix, rotation: GD8Symmetry, tx: Short?, ty: Short?) -> Unit
}

external interface IPoint : IPointData {
	fun copyFrom(p: IPointData): IPoint /* this */
	fun <T : IPoint> copyTo(p: T): T
	fun equals(p: IPointData): Boolean
	fun set(x: Double = definedExternally, y: Double = definedExternally)
}

external interface IPointData {
	var x: Double
	var y: Double
}

external interface ISize {
	var width: Double
	var height: Double
}

open external class Matrix(
	a: Double = definedExternally,
	b: Double = definedExternally,
	c: Double = definedExternally,
	d: Double = definedExternally,
	tx: Double = definedExternally,
	ty: Double = definedExternally
) {
	open var a: Double
	open var b: Double
	open var c: Double
	open var d: Double
	open var tx: Double
	open var ty: Double
	open var array: Float32Array?
	
	open fun fromArray(array: Array<Double>)
	open fun set(a: Double, b: Double, c: Double, d: Double, tx: Double): Matrix /* this */
	open fun toArray(transpose: Boolean, out: Float32Array = definedExternally): Float32Array
	open fun <P : IPointData /* = Point */> apply(pos: IPointData, newPos: P = definedExternally): P
	open fun <P : IPointData /* = Point */> applyInverse(pose: IPointData, newPos: P = definedExternally): P
	open fun translate(x: Double, y: Double): Matrix /* this */
	open fun scale(x: Double, y: Double): Matrix /* this */
	open fun rotate(angle: Double): Matrix /* this */
	open fun append(matrix: Matrix): Matrix /* this */
	open fun setTransform(
		x: Double,
		y: Double,
		pivotX: Double,
		pivotY: Double,
		scaleX: Double,
		scaleY: Double,
		rotation: Double,
		skewX: Double,
		skewY: Double
	): Matrix /* this */
	
	open fun prepend(matrix: Matrix): Matrix /* this */
	open fun decompose(transform: Transform): Transform
	open fun invert(): Matrix /* this */
	open fun identity(): Matrix /* this */
	open fun clone(): Matrix
	open fun copyTo(matrix: Matrix): Matrix
	open fun copyFrom(matrix: Matrix): Matrix /* this */
	override fun toString(): String
	
	companion object {
		val IDENTITY: Matrix
		val TEMP_MATRIX: Matrix
	}
}

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class ObservablePoint<T /* = Any */>(context: (self: T) -> Any?, scope: T, x: Double = definedExternally, y: Double = definedExternally) : IPoint {
	open var cb: (self: T) -> Any?
	open var scope: Any?
	open var _x: Double
	open var _y: Double
	
	override var x: Double
	override var y: Double
	
	open fun clone(cb: (`this`: T) -> Any? = definedExternally, scope: Any? = definedExternally): ObservablePoint<Any?>
	override fun set(x: Double, y: Double): ObservablePoint<T> /* this */
	override fun copyFrom(p: IPointData): ObservablePoint<T> /* this */
	override fun <T : IPoint> copyTo(p: T): T
	override fun equals(p: IPointData): Boolean
	override fun toString(): String
}

external val PI_2: Double

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class Point(x: Double = definedExternally, y: Double = definedExternally) : IPoint {
	override var x: Double
	override var y: Double
	
	open fun clone(): Point
	override fun copyFrom(p: IPointData): Point
	override fun <T : IPoint> copyTo(p: T): T
	override fun equals(p: IPointData): Boolean
	override fun set(x: Double, y: Double): Point
	override fun toString(): String
}

open external class Polygon(points: Array<IPointData>) {
	constructor(points: Array<Double>)
	constructor(vararg points: Array<IPointData>)
	constructor(vararg points: Array<Double>)
	
	open var points: Array<Double>
	open var closeStroke: Boolean
	open val type: SHAPES
	
	open fun clone(): Polygon
	open fun contains(x: Double, y: Double): Boolean
	override fun toString(): String
}

external val RAD_TO_DEG: Double

open external class Rectangle(x: Double = definedExternally, y: Double = definedExternally, width: Double = definedExternally, height: Double = definedExternally) {
	open var x: Double
	open var y: Double
	open var width: Double
	open var height: Double
	open var type: SHAPES
	
	open val left: Double
	open val right: Double
	open val top: Double
	open val bottom: Double
	
	open fun clone(): Rectangle
	open fun copyFrom(rectangle: Rectangle): Rectangle
	open fun contains(x: Double, y: Double): Boolean
	open fun pad(paddingX: Double = definedExternally, paddingY: Double = definedExternally): Rectangle /* this */
	open fun fit(rectangle: Rectangle): Rectangle /* this */
	open fun ceil(resolution: Double = definedExternally, eps: Double = definedExternally): Rectangle /* this */
	open fun enlarge(rectangle: Rectangle): Rectangle /* this */
	override fun toString(): String
	
	companion object {
		val EMPTY: Rectangle
	}
}

open external class RoundedRectangle(
	x: Double = definedExternally,
	y: Double = definedExternally,
	width: Double = definedExternally,
	height: Double = definedExternally,
	radius: Double = definedExternally
) {
	open var x: Double
	open var y: Double
	open var width: Double
	open var height: Double
	open var radius: Double
	open val type: SHAPES
	
	open fun clone(): RoundedRectangle
	open fun contains(x: Double, y: Double): Boolean
	override fun toString(): String
}

open external class Transform {
	open var worldTransform: Matrix
	open var localTransform: Matrix
	open var position: ObservablePoint<Any?>
	open var scale: ObservablePoint<Any?>
	open var pivot: ObservablePoint<Any?>
	open var skew: ObservablePoint<Any?>
	open var _parentID: Int
	open var _worldID: Int
	protected open var _rotation: Double
	protected open var _cx: Double
	protected open var _sx: Double
	protected open var _cy: Double
	protected open var _sy: Double
	protected open var _localID: Int
	protected open var _currentLocalID: Int
	
	open var rotation: Double
	
	protected open fun onChange()
	protected open fun updateSkew()
	override fun toString(): String
	open fun updateLocalTransform()
	open fun updateTransform(parentTransform: Transform)
	open fun setFromMatrix(matrix: Matrix)
	
	
	companion object {
		val IDENTITY: Transform
	}
}

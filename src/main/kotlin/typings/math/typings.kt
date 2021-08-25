@file:JsModule("@pixi/math")

package typings.math

import kotlinx.html.P
import org.khronos.webgl.Float32Array
import seskar.js.JsInt
import seskar.js.JsUnion

open external class Circle(x: Number, y: Number, radius: Number) {
	constructor(x: Number, y: Number)
	constructor(x: Number)
	constructor()

	open var x: Number
	open var y: Number
	open var radius: Number
	open val type: SHAPES
	open fun clone(): Circle
	open fun contains(x: Number, y: Number): Boolean
	open fun getBounds(): Rectangle
	override fun toString(): String
}

external val DEG_TO_RAD: Number

open external class Ellipse(x: Number, y: Number, width: Number, height: Number) {
	constructor(x: Number, y: Number, width: Number)
	constructor(x: Number, y: Number)
	constructor(x: Number)
	constructor()

	open var x: Number
	open var y: Number
	open var width: Number
	open var height: Number
	open val type: SHAPES
	open fun clone(): Circle
	open fun contains(x: Number, y: Number): Boolean
	open fun getBounds(): Rectangle
	override fun toString(): String
}

external object groupD8 {
	var E: Number
	var SE: Number
	var S: Number
	var SW: Number
	var W: Number
	var NW: Number
	var N: Number
	var NE: Number
	var MIRROR_VERTICAL: Number
	var MAIN_DIAGONAL: Number
	var MIRROR_HORIZONTAL: Number
	var REVERSE_DIAGONAL: Number
	var uX: (ind: GD8Symmetry) -> GD8Symmetry
	var uY: (ind: GD8Symmetry) -> GD8Symmetry
	var vX: (ind: GD8Symmetry) -> GD8Symmetry
	var vY: (ind: GD8Symmetry) -> GD8Symmetry
	var inv: (rotation: GD8Symmetry) -> GD8Symmetry
	var add: (rotationSecond: GD8Symmetry, rotationFirst: GD8Symmetry) -> GD8Symmetry
	var sub: (rotationSecond: GD8Symmetry, rotationFirst: GD8Symmetry) -> GD8Symmetry
	var rotate180: (rotation: Number) -> Number
	var isVertical: (rotation: GD8Symmetry) -> Boolean
	var byDirection: (dx: Number, dy: Number) -> GD8Symmetry
	var matrixAppendRotationInv: (matrix: Matrix, rotation: GD8Symmetry, tx: Number, ty: Number) -> Unit
}

external interface IPoint : IPointData {
	fun copyFrom(p: IPointData): IPoint /* this */
	fun <T : IPoint> copyTo(p: T): T
	fun equals(p: IPointData): Boolean
	fun set(x: Number, y: Number)
	fun set(x: Number)
	fun set()
}

external interface IPointData {
	var x: Number
	var y: Number
}

external interface ISize {
	var width: Number
	var height: Number
}

open external class Matrix(a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number) {
	constructor(a: Number, b: Number, c: Number, d: Number, tx: Number)
	constructor(a: Number, b: Number, c: Number, d: Number)
	constructor(a: Number, b: Number, c: Number)
	constructor(a: Number, b: Number)
	constructor(a: Number)
	constructor()

	open var a: Number
	open var b: Number
	open var c: Number
	open var d: Number
	open var tx: Number
	open var ty: Number
	open var array: Float32Array?

	open fun fromArray(array: Array<Number>)
	open fun set(a: Number, b: Number, c: Number, d: Number, tx: Number): Matrix /* this */
	open fun toArray(transpose: Boolean, out: Float32Array): Float32Array
	open fun toArray(transpose: Boolean): Float32Array
	open fun <P : IPointData /* = Point */> apply(pos: IPointData, newPos: P): P
	open fun <P : IPointData /* = Point */> apply(pos: IPointData): P
	open fun <P : IPointData /* = Point */> applyInverse(pose: IPointData, newPos: P): P
	open fun <P : IPointData /* = Point */> applyInverse(pose: IPointData): P
	open fun translate(x: Number, y: Number): Matrix /* this */
	open fun scale(x: Number, y: Number): Matrix /* this */
	open fun rotate(angle: Number): Matrix /* this */
	open fun append(matrix: Matrix): Matrix /* this */
	open fun setTransform(
		x: Number,
		y: Number,
		pivotX: Number,
		pivotY: Number,
		scaleX: Number,
		scaleY: Number,
		rotation: Number,
		skewX: Number,
		skewY: Number
	): Matrix /* this */

	open fun prepend(matrix: Matrix): Matrix /* this */
	open fun decompose(transform: Transform): Transform
	open fun invert(): Matrix /* this */
	open fun identity(): Matrix /* this */
	open fun clone(): Matrix
	open fun copyTo(matrix: Matrix): Matrix
	open fun copyFrom(matrix: Matrix): Matrix /* this */

	companion object {
		val IDENTITY: Matrix
		val TEMP_MATRIX: Matrix
	}
}

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class ObservablePoint<T>(context: (self: T) -> Any, scope: T, x: Number, y: Number) : IPoint {
	constructor(context: (self: T) -> Any, scope: T, x: Number)
	constructor(context: (self: T) -> Any, scope: T)

	open var cb: (self: T) -> Any
	open var scope: T
	open var _x: Number
	open var _y: Number
	override var x: Number
	override var y: Number
	open fun clone(cb: (`this`: T) -> Any, scope: Any): ObservablePoint<Any>

	open fun clone(cb: (`this`: T) -> Any): ObservablePoint<Any>
	open fun clone(): ObservablePoint<Any>
	override fun set(x: Number, y: Number): ObservablePoint<T> /* this */
	override fun set(x: Number): ObservablePoint<T> /* this */
	override fun set(): ObservablePoint<T> /* this */
	override fun copyFrom(p: IPointData): ObservablePoint<T> /* this */
	override fun <T : IPoint> copyTo(p: T): T
	override fun equals(p: IPointData): Boolean
	override fun toString(): String
}

external val PI_2: Number

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class Point(x: Number, y: Number) : IPoint {
	constructor(x: Number)
	constructor()

	override var x: Number

	override var y: Number
	open fun clone(): Point
	override fun copyFrom(p: IPointData): Point
	override fun <T : IPoint> copyTo(p: T): T
	override fun equals(p: IPointData): Boolean
	override fun set(x: Number, y: Number): Point
	override fun set(x: Number): Point
	override fun set(): Point
	override fun toString(): String
}

open external class Polygon(points: Array<IPointData>) {
	constructor(points: Array<Number>)
	constructor(vararg points: Array<IPointData>)
	constructor(vararg points: Array<Number>)

	open var points: Array<Number>
	open var closeStroke: Boolean
	open val type: SHAPES

	open fun clone(): Polygon
	open fun contains(x: Number, y: Number): Boolean
	override fun toString(): String
}

external val RAD_TO_DEG: Number

open external class Rectangle(x: Number, y: Number, width: Number, height: Number) {
	constructor(x: Number, y: Number, width: Number)
	constructor(x: Number, y: Number)
	constructor(x: Number)
	constructor()

	open var x: Number
	open var y: Number
	open var width: Number
	open var height: Number
	open var type: SHAPES
	open val left: Number
	open val right: Number
	open val top: Number
	open val bottom: Number

	open fun clone(): Rectangle
	open fun copyFrom(rectangle: Rectangle): Rectangle
	open fun contains(x: Number, y: Number): Boolean
	open fun pad(paddingX: Number, paddingY: Number): Rectangle /* this */
	open fun fit(rectangle: Rectangle): Rectangle /* this */
	open fun ceil(resolution: Number, eps: Number): Rectangle /* this */
	open fun ceil(resolution: Number): Rectangle /* this */
	open fun ceil(): Rectangle /* this */
	open fun enlarge(rectangle: Rectangle): Rectangle /* this */
	override fun toString(): String

	companion object {
		val EMPTY: Rectangle
	}
}

open external class RoundedRectangle(x: Number, y: Number, width: Number, height: Number, radius: Number) {
	constructor(x: Number, y: Number, width: Number, height: Number)
	constructor(x: Number, y: Number, width: Number)
	constructor(x: Number, y: Number)
	constructor(x: Number)
	constructor()

	open var x: Number
	open var y: Number
	open var width: Number
	open var height: Number
	open var radius: Number
	open val type: SHAPES

	open fun clone(): RoundedRectangle
	open fun contains(x: Number, y: Number): Boolean
	override fun toString(): String
}

@JsUnion
external enum class SHAPES {
	@JsInt(0)
	POLY,

	@JsInt(1)
	RECT,

	@JsInt(2)
	CIRC,

	@JsInt(3)
	ELIP,

	@JsInt(4)
	RREC
}

open external class Transform {
	open var worldTransform: Matrix
	open var localTransform: Matrix
	open var position: ObservablePoint<Any>
	open var scale: ObservablePoint<Any>
	open var pivot: ObservablePoint<Any>
	open var skew: ObservablePoint<Any>
	open var _parentID: Number
	open var _worldID: Number
	protected open var _rotation: Number
	protected open var _cx: Number
	protected open var _cy: Number
	protected open var _localID: Number
	protected open var _currentLocalID: Number
	open var rotation: Number

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

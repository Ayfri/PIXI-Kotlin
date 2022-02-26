@file:JsModule("@pixi/math-extras")

package pixi.typings.math_extras

import pixi.typings.math.IPointData
import pixi.typings.math.Point

external fun floatEqual(a: Number, b: Number, epsilon: Double = definedExternally): Boolean

external fun lineIntersection(aStart: IPointData, aEnd: IPointData, bStart: IPointData, bEnd: IPointData): Point

external fun <T : IPointData> lineIntersection(aStart: IPointData, aEnd: IPointData, bStart: IPointData, bEnd: IPointData, outPoint: T): T

external fun segmentIntersection(aStart: IPointData, aEnd: IPointData, bStart: IPointData, bEnd: IPointData): Point

external fun <T : IPointData> segmentIntersection(aStart: IPointData, aEnd: IPointData, bStart: IPointData, bEnd: IPointData, outPoint: T): T

external interface Vector2Math {
	fun add(other: IPointData): Point
	fun <T : IPointData> add(other: IPointData, outPoint: T): T
	
	fun subtract(other: IPointData): Point
	fun <T : IPointData> subtract(other: IPointData, outPoint: T): T
	
	fun multiply(other: IPointData): Point
	fun <T : IPointData> multiply(other: IPointData, outPoint: T): T
	
	fun divide(other: IPointData): Point
	fun <T : IPointData> divide(other: IPointData, outPoint: T): T
	
	fun multiplyScalar(scalar: Double): Point
	fun <T : IPointData> multiplyScalar(scalar: Double, outPoint: T): T
	
	fun dot(other: IPointData): Double
	
	fun cross(other: IPointData): Double
	
	fun normalize(): Point
	fun <T : IPointData> normalize(outPoint: T): T
	
	fun magnitude(): Double
	
	fun magnitudeSquared(): Double
	
	fun project(other: IPointData): Point
	fun <T : IPointData> project(other: IPointData, outPoint: T): T
	
	fun reflect(other: IPointData): Point
	fun <T : IPointData> reflect(other: IPointData, outPoint: T): T
}

package pixi

import pixi.typings.math.IPointData
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Point
import pixi.typings.math.Rectangle


inline fun ObservablePoint<*>.add(other: IPointData) = asDynamic().add(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.add(other: IPointData, outPoint: T) = asDynamic().add(other, outPoint) as T

inline fun ObservablePoint<*>.subtract(other: IPointData) = asDynamic().subtract(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.subtract(other: IPointData, outPoint: T) = asDynamic().subtract(other, outPoint) as T

inline fun ObservablePoint<*>.multiply(other: IPointData) = asDynamic().multiply(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.multiply(other: IPointData, outPoint: T) = asDynamic().multiply(other, outPoint) as T

inline fun ObservablePoint<*>.divide(other: IPointData) = asDynamic().divide(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.divide(other: IPointData, outPoint: T) = asDynamic().divide(other, outPoint) as T

inline fun ObservablePoint<*>.multiplyScalar(scalar: Double) = asDynamic().multiply(scalar) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.multiplyScalar(scalar: Double, outPoint: T) = asDynamic().multiply(scalar, outPoint) as T

inline fun ObservablePoint<*>.dot(other: IPointData): Double = asDynamic().dot(other) as Double

inline fun ObservablePoint<*>.cross(other: IPointData): Double = asDynamic().cross(other) as Double

inline fun ObservablePoint<*>.normalize() = asDynamic().normalize() as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.normalize(outPoint: T) = asDynamic().normalize(outPoint) as T

inline fun ObservablePoint<*>.magnitude(): Double = asDynamic().magnitude() as Double

inline fun ObservablePoint<*>.magnitudeSquared(): Double = asDynamic().magnitude() as Double

inline fun ObservablePoint<*>.project(other: IPointData) = asDynamic().project(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.project(other: IPointData, outPoint: T) = asDynamic().project(other, outPoint) as T

inline fun ObservablePoint<*>.reflect(other: IPointData) = asDynamic().reflect(other) as ObservablePoint<*>
inline fun <T : IPointData> ObservablePoint<*>.reflect(other: IPointData, outPoint: T) = asDynamic().reflect(other, outPoint) as T


inline fun Point.add(other: IPointData) = asDynamic().add(other) as Point
inline fun <T : IPointData> Point.add(other: IPointData, outPoint: T) = asDynamic().add(other, outPoint) as T

inline fun Point.subtract(other: IPointData) = asDynamic().subtract(other) as Point
inline fun <T : IPointData> Point.subtract(other: IPointData, outPoint: T) = asDynamic().subtract(other, outPoint) as T

inline fun Point.multiply(other: IPointData) = asDynamic().multiply(other) as Point
inline fun <T : IPointData> Point.multiply(other: IPointData, outPoint: T) = asDynamic().multiply(other, outPoint) as T

inline fun Point.divide(other: IPointData) = asDynamic().divide(other) as Point
inline fun <T : IPointData> Point.divide(other: IPointData, outPoint: T) = asDynamic().divide(other, outPoint) as T

inline fun Point.multiplyScalar(scalar: Double) = asDynamic().multiply(scalar) as Point
inline fun <T : IPointData> Point.multiplyScalar(scalar: Double, outPoint: T) = asDynamic().multiply(scalar, outPoint) as T

inline fun Point.dot(other: IPointData): Double = asDynamic().dot(other) as Double

inline fun Point.cross(other: IPointData): Double = asDynamic().cross(other) as Double

inline fun Point.normalize() = asDynamic().normalize() as Point
inline fun <T : IPointData> Point.normalize(outPoint: T) = asDynamic().normalize(outPoint) as T

inline fun Point.magnitude(): Double = asDynamic().magnitude() as Double

inline fun Point.magnitudeSquared(): Double = asDynamic().magnitude() as Double

inline fun Point.project(other: IPointData) = asDynamic().project(other) as Point
inline fun <T : IPointData> Point.project(other: IPointData, outPoint: T) = asDynamic().project(other, outPoint) as T

inline fun Point.reflect(other: IPointData) = asDynamic().reflect(other) as Point
inline fun <T : IPointData> Point.reflect(other: IPointData, outPoint: T) = asDynamic().reflect(other, outPoint) as T


inline fun Rectangle.intersects(other: Rectangle) = asDynamic().intersects(other) as Boolean

inline fun Rectangle.containsRect(other: Rectangle) = asDynamic().containsRect(other) as Boolean

inline fun Rectangle.equals(other: Rectangle) = asDynamic().equals(other)

inline fun Rectangle.intersection(other: Rectangle) = asDynamic().intersection(other) as Rectangle
inline fun <T : Rectangle> Rectangle.intersection(other: Rectangle, outRectangle: T) = asDynamic().intersection(other, outRectangle) as T

inline fun Rectangle.union(other: Rectangle) = asDynamic().union(other) as Rectangle
inline fun <T : Rectangle> Rectangle.union(other: Rectangle, outRectangle: T) = asDynamic().union(other, outRectangle) as T

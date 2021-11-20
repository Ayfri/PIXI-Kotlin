package typings.mixin_get_global_position

import typings.display.DisplayObject
import typings.math.Point

inline fun DisplayObject.getGlobalPosition(point: Point, skipUpdate: Boolean) = asDynamic().getGlobalPosition(point, skipUpdate) as Point

inline fun DisplayObject.getGlobalPosition(point: Point) = asDynamic().getGlobalPosition(point) as Point
inline fun DisplayObject.getGlobalPosition() = asDynamic().getGlobalPosition() as Point

package typings.mixin_get_global_position

import typings.display.DisplayObject
import typings.math.Point

inline fun DisplayObject.getGlobalPosition(point: Point = Point(), skipUpdate: Boolean = false) = asDynamic().getGlobalPosition(point, skipUpdate) as Point

package pixi.typings.mixin_get_global_position

import pixi.typings.display.DisplayObject
import pixi.typings.math.Point

inline fun DisplayObject.getGlobalPosition(point: Point = Point(), skipUpdate: Boolean = false) = asDynamic().getGlobalPosition(point, skipUpdate) as Point

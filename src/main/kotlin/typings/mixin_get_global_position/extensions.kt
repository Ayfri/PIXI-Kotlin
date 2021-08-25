package typings.mixin_get_global_position

import typings.display.DisplayObject
import typings.math.Point

@Suppress("NOTHING_TO_INLINE")
inline fun DisplayObject.getGlobalPosition(point: Point, skipUpdate: Boolean): Point =
	asDynamic().getGlobalPosition(point, skipUpdate) as Point

@Suppress("NOTHING_TO_INLINE")
inline fun DisplayObject.getGlobalPosition(point: Point): Point = asDynamic().getGlobalPosition(point) as Point
@Suppress("NOTHING_TO_INLINE")
inline fun DisplayObject.getGlobalPosition(): Point = asDynamic().getGlobalPosition() as Point
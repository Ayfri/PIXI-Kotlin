@file:Suppress("NOTHING_TO_INLINE")

package pixi.typings.mixin_get_child_by_name

import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

inline var DisplayObject.name
	get() = asDynamic().name as String
	set(value) {
		asDynamic().name = value
	}

inline fun Container.getChildByName(name: String, isRecursive: Boolean = false) = asDynamic().getChildByName(name, isRecursive) as DisplayObject

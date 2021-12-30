@file:Suppress("NOTHING_TO_INLINE")

package typings.mixin_get_child_by_name

import typings.display.Container
import typings.display.DisplayObject

inline var DisplayObject.name
	get() = asDynamic().name as String
	set(value) {
		asDynamic().name = value
	}

inline fun Container.getChildByName(name: String, isRecursive: Boolean = false) = asDynamic().getChildByName(name, isRecursive) as DisplayObject

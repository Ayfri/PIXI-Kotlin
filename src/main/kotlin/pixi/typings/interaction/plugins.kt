package pixi.typings.interaction

import pixi.typings.Object
import pixi.typings.display.DisplayObject

var DisplayObject.interactive
	get() = asDynamic().interactive as Boolean
	set(value) {
		asDynamic().interactive = value
	}

var DisplayObject.interactiveChildren
	get() = asDynamic().interactiveChildren as Boolean
	set(value) {
		asDynamic().interactiveChildren = value
	}

var DisplayObject.hitArea
	get() = asDynamic().hitArea as IHitArea
	set(value) {
		asDynamic().hitArea = value
	}

var DisplayObject.cursor /* Cursor | String */
	get() = asDynamic().cursor
	set(value) {
		asDynamic().cursor = value
	}

var DisplayObject.buttonMode
	get() = asDynamic().buttonMode as Boolean
	set(value) {
		asDynamic().buttonMode = value
	}

var DisplayObject.trackedPointers
	get() = asDynamic().trackedPointers as Object<Number, InteractionTrackingData>
	set(value) {
		asDynamic().trackedPointers = value
	}

var DisplayObject._trackedPointers
	get() = asDynamic()._trackedPointers as Object<Number, InteractionTrackingData>
	set(value) {
		asDynamic()._trackedPointers = value
	}

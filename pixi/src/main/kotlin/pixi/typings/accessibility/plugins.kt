package pixi.typings.accessibility

import pixi.typings.display.DisplayObject

inline var DisplayObject.accessible
	get() = asDynamic().accessible.unsafeCast<Boolean>()
	set(value) {
		asDynamic().accessible = value
	}

inline var DisplayObject.accessibleTitle
	get() = asDynamic().accessibleTitle.unsafeCast<String>()
	set(value) {
		asDynamic().accessibleTitle = value
	}

inline var DisplayObject.accessibleHint
	get() = asDynamic().accessibleHint.unsafeCast<String>()
	set(value) {
		asDynamic().accessibleHint = value
	}

inline var DisplayObject.tabIndex
	get() = asDynamic().tabIndex.unsafeCast<Int>()
	set(value) {
		asDynamic().tabIndex = value
	}

inline var DisplayObject._accessibleActive
	get() = asDynamic()._accessibleActive.unsafeCast<Boolean>()
	set(value) {
		asDynamic()._accessibleActive = value
	}

inline var DisplayObject._accessibleDiv
	get() = asDynamic()._accessibleDiv.unsafeCast<IAccessibleHTMLElement>()
	set(value) {
		asDynamic()._accessibleDiv = value
	}

inline var DisplayObject.accessibleType
	get() = asDynamic().accessibleType.unsafeCast<String>()
	set(value) {
		asDynamic().accessibleType = value
	}

inline var DisplayObject.accessiblePointerEvents
	get() = asDynamic().accessiblePointerEvents.unsafeCast<PointerEvents>()
	set(value) {
		asDynamic().accessiblePointerEvents = value
	}

inline var DisplayObject.accessibleChildren
	get() = asDynamic().accessibleChildren.unsafeCast<Boolean>()
	set(value) {
		asDynamic().accessibleChildren = value
	}

inline var DisplayObject.renderId
	get() = asDynamic().renderId.unsafeCast<Int>()
	set(value) {
		asDynamic().renderId = value
	}

package pixi.typings.app

inline var Application.resizeTo: dynamic /* Window | HTMLElement */
	get() = asDynamic().resizeTo
	set(value) {
		asDynamic().resizeTo = value
	}

inline var Application.resize: () -> Unit
	get() = asDynamic().resize.unsafeCast<() -> Unit>()
	set(noinline value) {
		asDynamic().resize = value
	}

inline var Application.queueResize: () -> Unit
	get() = asDynamic().queueResize.unsafeCast<() -> Unit>()
	set(noinline value) {
		asDynamic().queueResize = value
	}

inline var Application.cancelResize: () -> Unit
	get() = asDynamic().cancelResize.unsafeCast<() -> Unit>()
	set(noinline value) {
		asDynamic().cancelResize = value
	}

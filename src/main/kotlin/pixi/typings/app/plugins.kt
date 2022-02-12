package pixi.typings.app

inline var Application.resizeTo: dynamic /* Window | HTMLElement */
	get() = asDynamic().resizeTo
	set(value) {
		asDynamic().resizeTo = value
	}

inline var Application.resize: () -> Unit
	get() = asDynamic().resize as () -> Unit
	set(noinline value) {
		asDynamic().resize = value
	}

inline var Application.queueResize: () -> Unit
	get() = asDynamic().queueResize as () -> Unit
	set(noinline value) {
		asDynamic().queueResize = value
	}

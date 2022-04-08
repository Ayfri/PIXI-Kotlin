package pixi.typings.ticker

import pixi.typings.app.IApplicationOptions

inline var IApplicationOptions.autoStart
	get() = asDynamic().autoStart as? Boolean
	set(value) {
		asDynamic().autoStart = value
	}

inline var IApplicationOptions.sharedTicker
	get() = asDynamic().sharedTicker as? Boolean
	set(value) {
		asDynamic().sharedTicker = value
	}

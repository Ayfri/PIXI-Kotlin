package typings.ticker

import typings.app.IApplicationOptions

inline var IApplicationOptions.autoStart: Boolean?
	get() = asDynamic().autoStart as? Boolean
	set(value) {
		asDynamic().autoStart = value
	}

inline var IApplicationOptions.sharedTicker: Boolean?
	get() = asDynamic().sharedTicker as? Boolean
	set(value) {
		asDynamic().sharedTicker = value
	}

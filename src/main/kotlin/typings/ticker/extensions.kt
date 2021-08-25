package typings.ticker

import typings.app.Application
import typings.app.IApplicationOptions

inline var Application.ticker: Ticker
	get() = asDynamic().ticker as Ticker
	set(value) {
		asDynamic().ticker = value
	}

inline fun Application.stop(): Unit = asDynamic().stop() as Unit
inline fun Application.start() = asDynamic().start() as Unit

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
package pixi.typings.ticker

import pixi.typings.app.Application

inline var Application.ticker
	get() = asDynamic().ticker.unsafeCast<Ticker>()
	set(value) {
		asDynamic().ticker = value
	}

inline fun Application.stop() = asDynamic().stop() as Unit
inline fun Application.start() = asDynamic().start() as Unit

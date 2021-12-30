package pixi.typings.ticker

import pixi.typings.Number

typealias TickerCallback<T> = (self: T, dt: Number) -> Any?

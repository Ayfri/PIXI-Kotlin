package typings.ticker

import typings.Number

typealias TickerCallback<T> = (self: T, dt: Number) -> Any

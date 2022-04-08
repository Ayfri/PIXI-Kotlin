package pixi.externals.extensions

import pixi.typings.loaders.Loader
import pixi.typings.loaders.OnCompleteSignal

fun Loader.load(fn: (Loader) -> Unit) = load(fn.unsafeCast<OnCompleteSignal>())
fun Loader.load(fn: () -> Unit) = load(fn.unsafeCast<OnCompleteSignal>())

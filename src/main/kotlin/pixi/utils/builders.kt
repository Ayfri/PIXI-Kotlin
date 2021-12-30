package pixi.utils

import kotlinext.js.jsObject
import pixi.typings.app.Application
import pixi.typings.app.IApplicationOptions

fun Application(block: IApplicationOptions.() -> Unit) = Application(jsObject(block))

package pixi.utils

import kotlinext.js.jso
import pixi.typings.app.Application
import pixi.typings.app.IApplicationOptions

fun Application(block: IApplicationOptions.() -> Unit) = Application(jso(block))

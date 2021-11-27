package utils

import kotlinext.js.jsObject
import typings.app.Application
import typings.app.IApplicationOptions

fun Application(block: IApplicationOptions.() -> Unit) = Application(jsObject(block))

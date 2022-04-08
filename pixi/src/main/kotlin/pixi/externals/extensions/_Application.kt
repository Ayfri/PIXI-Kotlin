package pixi.externals.extensions

import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import pixi.typings.app.Application

fun Application.addTo(element: HTMLElement) = element.appendChild(view)
fun Application.addToBody() = document.body!!.appendChild(view)

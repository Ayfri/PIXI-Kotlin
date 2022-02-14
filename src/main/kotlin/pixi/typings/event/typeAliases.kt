package pixi.typings.event

import org.w3c.dom.Window
import org.w3c.dom.events.UIEvent

typealias EventFn = (e: FederatedEvent<UIEvent>) -> Unit
typealias WindowProxy = Window

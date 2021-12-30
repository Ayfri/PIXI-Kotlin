package pixi.typings.interaction

import org.w3c.dom.events.UIEvent
import pixi.typings.display.DisplayObject

typealias InteractionCallback = (interactionEvent: InteractionEvent, displayObject: DisplayObject, hit: Boolean /* = definedExternally */) -> Unit
internal typealias InteractionCallbackDefaultValue = (interactionEvent: InteractionEvent, displayObject: DisplayObject) -> Unit
typealias InteractivePointerEvent = UIEvent /* PointerEvent | TouchEvent | MouseEvent */

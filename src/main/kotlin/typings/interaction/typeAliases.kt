package typings.interaction

import typings.display.DisplayObject

typealias InteractionCallback = (interactionEvent: InteractionEvent, displayObject: DisplayObject, hit: Boolean?) -> Unit
typealias InteractivePointerEvent = Any /* PointerEvent | TouchEvent | MouseEvent */
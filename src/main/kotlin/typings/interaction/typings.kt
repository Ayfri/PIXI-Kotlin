@file:JsModule("@pixi/interaction")

package typings.interaction

import org.w3c.dom.HTMLElement
import org.w3c.dom.Touch
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.pointerevents.PointerEvent
import typings.Indexed
import typings.core.AbstractRenderer
import typings.display.DisplayObject
import typings.math.IPointData
import typings.math.Point
import typings.utils.Dict
import typings.utils.EventEmitter

external interface DelayedEvent {
	var displayObject: DisplayObject
	var eventString: String
	var eventData: InteractionEvent
}

external interface IHitArea {
	fun contains(x: Number, y: Number): Boolean
}

open external class InteractionData {
	open var global: Point
	open var target: DisplayObject
	open var originalEvent: InteractivePointerEvent
	open var identifier: Number
	open var isPrimary: Boolean
	open var button: Number
	open var buttons: Number
	open var width: Number
	open var height: Number
	open var tiltX: Number
	open var tiltY: Number
	open var pointerType: String
	open var pressure: Number
	open var twist: Number
	open var tangentialPressure: Number
	open val pointerId: Number
	open fun <P : IPointData /* = Point */> getLocalPosition(
		displayObject: DisplayObject,
		point: P = definedExternally,
		globalPos: IPointData = definedExternally
	): P

	open fun getLocalPosition(displayObject: DisplayObject, point: Point = definedExternally, globalPos: IPointData = definedExternally): Point
	open fun copyEvent(event: Touch)
	open fun copyEvent(event: PointerEvent)
	open fun copyEvent(event: TouchEvent)
	open fun copyEvent(event: MouseEvent)
	open fun reset()
}

open external class InteractionEvent {
	open var stopped: Boolean
	open var stopsPropagatingAt: DisplayObject
	open var stopPropagationHint: Boolean
	open var target: DisplayObject
	open var currentTarget: DisplayObject
	open var type: String
	open var data: InteractionData
	open fun stopPropagation()
	open fun reset()
}

open external class InteractionManager(renderer: AbstractRenderer, options: InteractionManagerOptions = definedExternally) : EventEmitter {
	open val activeInteractionData: Indexed<Number, InteractionData>
	open val supportsTouchEvents: Boolean
	open val supportsPointerEvents: Boolean
	open var interactionDataPool: Array<InteractionData>
	open var cursor: String
	open var delayedEvents: Array<DelayedEvent>
	open var search: TreeSearch
	open var renderer: AbstractRenderer
	open var autoPreventDefault: Boolean
	open var interactionFrequency: Number
	open var mouse: InteractionData
	open var eventData: InteractionEvent
	open var moveWhenInside: Boolean
	open var cursorStyle: Dict<dynamic /* string | (mode: String) -> Unit | CSSStyleDeclaration */>
	open var currentCursorMode: String
	open var resolution: Number
	protected open var interactionDOMElement: HTMLElement
	protected open var eventsAdded: Boolean
	protected open var mouseOverRenderer: Boolean
	open var useSystemTicker: Boolean
	open val lastObjectRendered: DisplayObject

	open fun hitTest(globalPoint: Point, root: DisplayObject = definedExternally): DisplayObject
	open fun setTargetElement(element: HTMLElement, resolution: Number = definedExternally)
	open fun tickerUpdate(deltaTime: Number)
	open fun update()
	open fun setCursorMode(mode: String)
	open fun mapPositionToPoint(point: IPointData, x: Number, y: Number)
	open fun processInteractive(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally
	)

	open fun destroy()
}

external interface InteractionManagerOptions {
	var autoPreventDefault: Boolean?
	var interactionFrequency: Number?
	var useSystemTicker: Boolean?
}

open external class InteractionTrackingData(pointerId: Number) {
	open val pointerId: Number
	open var flags: Number
	open var over: Boolean
	open var rightDown: Boolean
	open var leftDown: Boolean

	companion object {
		val FLAGS: InteractionTrackingFlagsReadOnly
	}
}

external interface InteractionTrackingFlags {
	var OVER: Number
	var LEFT_DOWN: Number
	var RIGHT_DOWN: Number
	var NONE: Number
}

external interface InteractionTrackingFlagsReadOnly {
	val OVER: Number
	val LEFT_DOWN: Number
	val RIGHT_DOWN: Number
	val NONE: Number
}

external object interactiveTarget {
	var interactive: Boolean
	var interactiveChildren: Boolean
	var hitArea: IHitArea
	var cursor: Cursor
	var buttonMode: Boolean
	var trackedPointers: Indexed<Number, InteractionTrackingData>
	var _trackedPointers: Indexed<Number, InteractionTrackingData>
}

open external class TreeSearch {
	open fun recursiveFindHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally,
		interactive: Boolean = definedExternally
	): Boolean

	open fun findHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally
	)
}
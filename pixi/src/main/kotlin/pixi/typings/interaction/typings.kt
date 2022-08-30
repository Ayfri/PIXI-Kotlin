@file:JsModule("@pixi/interaction")

package pixi.typings.interaction

import org.w3c.dom.HTMLElement
import org.w3c.dom.Touch
import org.w3c.dom.TouchEvent
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.pointerevents.PointerEvent
import pixi.typings.Object
import pixi.typings.core.AbstractRenderer
import pixi.typings.display.DisplayObject
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.math.IPointData
import pixi.typings.math.Point
import pixi.typings.utils.Dict
import pixi.typings.utils.EventEmitter

external interface DelayedEvent {
	var displayObject: DisplayObject
	var eventString: String
	var eventData: InteractionEvent
}

external interface IHitArea {
	fun contains(x: Double, y: Double): Boolean
}

open external class InteractionData {
	open var global: Point
	open var target: DisplayObject
	open var originalEvent: InteractivePointerEvent
	open var identifier: Int
	open var isPrimary: Boolean
	open var button: Button
	open var buttons: Short
	open var width: Int
	open var height: Int
	open var tiltX: Short
	open var tiltY: Short
	open var pointerType: String?
	open var pressure: Double
	open var rotationAngle: Short
	open var twist: Int
	open var tangentialPressure: Double
	
	open val pointerId: Int
	
	open fun <P : IPointData /* = Point */> getLocalPosition(
		displayObject: DisplayObject,
		point: P = definedExternally,
		globalPos: IPointData = definedExternally,
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
	open val activeInteractionData: Object<Int, InteractionData>
	open val supportsTouchEvents: Boolean
	open val supportsPointerEvents: Boolean
	open var interactionDataPool: Array<InteractionData>
	open var cursor: String
	open var delayedEvents: Array<DelayedEvent>
	open var search: TreeSearch
	open var renderer: AbstractRenderer
	open var autoPreventDefault: Boolean
	open var interactionFrequency: Int
	open var mouse: InteractionData
	open var eventData: InteractionEvent
	open var moveWhenInside: Boolean
	open var cursorStyle: Dict<dynamic /* String | (mode: String) -> Unit | CSSStyleDeclaration */>
	open var currentCursorMode: String
	open var resolution: Double
	protected open var interactionDOMElement: HTMLElement
	protected open var eventsAdded: Boolean
	protected open var mouseOverRenderer: Boolean
	
	open var useSystemTicker: Boolean
	open val lastObjectRendered: DisplayObject
	
	open fun hitTest(globalPoint: Point, root: DisplayObject = definedExternally): DisplayObject
	open fun setTargetElement(element: HTMLElement, resolution: Double = definedExternally)
	open fun tickerUpdate(deltaTime: Int)
	open fun update()
	open fun setCursorMode(mode: String)
	open fun mapPositionToPoint(point: IPointData, x: Int, y: Int)
	open fun processInteractive(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally,
	)
	
	open fun processInteractive(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallbackDefaultValue = definedExternally,
		hitTest: Boolean = definedExternally,
	)
	
	open fun destroy()
	
	companion object {
		var extension: ExtensionMetadata
	}
}

external interface InteractionManagerOptions {
	var autoPreventDefault: Boolean?
	var interactionFrequency: Int?
	var useSystemTicker: Boolean?
}

open external class InteractionTrackingData(pointerId: Int) {
	open val pointerId: Int
	open var flags: Short
	open val none: Boolean
	open var over: Boolean
	open var rightDown: Boolean
	open var leftDown: Boolean
	
	companion object {
		val FLAGS: InteractionTrackingFlagsReadOnly
	}
}

external interface InteractionTrackingFlags {
	var OVER: Short
	var LEFT_DOWN: Short
	var RIGHT_DOWN: Short
	var NONE: Short
}

external interface InteractionTrackingFlagsReadOnly {
	val OVER: Short
	val LEFT_DOWN: Short
	val RIGHT_DOWN: Short
	val NONE: Short
}

external interface InteractiveTarget {
	var interactive: Boolean
	var interactiveChildren: Boolean
	var hitArea: IHitArea?
	var cursor: dynamic /* Cursor | String */
	var buttonMode: Boolean
	var trackedPointers: Object<Int, InteractionTrackingData>
	var _trackedPointers: Object<Int, InteractionTrackingData>
}

external object interactiveTarget : InteractiveTarget {
	override var interactive: Boolean
	override var interactiveChildren: Boolean
	override var hitArea: IHitArea?
	override var cursor: dynamic /* Cursor | String */
	override var buttonMode: Boolean
	override var trackedPointers: Object<Int, InteractionTrackingData>
	override var _trackedPointers: Object<Int, InteractionTrackingData>
}

open external class TreeSearch {
	open fun recursiveFindHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally,
		interactive: Boolean = definedExternally,
	): Boolean
	
	open fun recursiveFindHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallbackDefaultValue = definedExternally,
		hitTest: Boolean = definedExternally,
		interactive: Boolean = definedExternally,
	): Boolean
	
	open fun findHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallback = definedExternally,
		hitTest: Boolean = definedExternally,
	)
	
	open fun findHit(
		interactionEvent: InteractionEvent,
		displayObject: DisplayObject,
		func: InteractionCallbackDefaultValue = definedExternally,
		hitTest: Boolean = definedExternally,
	)
}

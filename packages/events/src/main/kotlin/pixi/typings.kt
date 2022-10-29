@file:JsModule("@pixi/events")

package pixi

import kotlinx.js.ReadonlyArray
import kotlinx.js.Record
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.EventTarget
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.UIEvent
import org.w3c.dom.events.WheelEvent
import org.w3c.dom.pointerevents.PointerEvent
import pixi.typings.Object
import pixi.typings.core.IRenderableObject
import pixi.typings.core.Renderer
import pixi.typings.display.DisplayObject
import pixi.typings.interaction.Cursor
import pixi.typings.interaction.IHitArea
import pixi.typings.math.IPointData
import pixi.typings.math.Point
import pixi.typings.utils.EventEmitter

external interface EventTable {
	var fn: EventFn
	var priority: Int
}

external interface AllocateEvent<T> {
	operator fun invoke(boundary: EventBoundary): T
}

open external class EventBoundary(rootTarget: DisplayObject = definedExternally) {
	open var rootTarget: DisplayObject
	open var dispatch: EventEmitter
	open var cursor: Cursor
	protected var mappingTable: Object<String, Array<EventTable>>
	protected var mappingState: Object<String, Any>
	protected var eventPool: Map<FederatedEvent<UIEvent>, Array<FederatedEvent<UIEvent>>>
	
	fun addEventMapping(type: String, fn: EventFn)
	fun dispatchEvent(e: FederatedEvent<UIEvent>, type: String = definedExternally)
	fun mapEvent(e: FederatedEvent<UIEvent>)
	fun hitTest(x: Int, y: Int): DisplayObject
	fun propagate(e: FederatedEvent<UIEvent>, type: String = definedExternally)
	fun all(e: FederatedEvent<UIEvent>, type: String = definedExternally, target: FederatedEvent<UIEvent> = definedExternally)
	fun propagationPath(target: FederatedEvent<UIEvent>): Array<FederatedEvent<UIEvent>>
	protected fun hitTestRecursive(
		currentTarget: DisplayObject,
		interactive: Boolean,
		location: Point,
		testFn: (`object`: DisplayObject, pt: Point) -> Boolean,
		pruneFn: (`object`: DisplayObject, pt: Point) -> Boolean = definedExternally
	): Array<DisplayObject>
	
	protected fun hitPruneFn(displayObject: DisplayObject, location: Point): Boolean
	protected fun hitTestFn(displayObject: DisplayObject, location: Point): Boolean
	protected fun notifyTarget(e: FederatedEvent<UIEvent>, type: String = definedExternally)
	protected fun mapPointerDown(from: FederatedEvent<UIEvent>)
	protected fun mapPointerMove(from: FederatedEvent<UIEvent>)
	protected fun mapPointerOver(from: FederatedEvent<UIEvent>)
	protected fun mapPointerOut(from: FederatedEvent<UIEvent>)
	protected fun mapPointerUp(from: FederatedEvent<UIEvent>)
	protected fun mapPointerUpOutside(from: FederatedEvent<UIEvent>)
	protected fun mapWheel(from: FederatedEvent<UIEvent>)
	protected fun findMountedTarget(propagationPath: Array<FederatedEventTarget>): FederatedEventTarget
	protected fun createPointerEvent(from: FederatedPointerEvent, type: String = definedExternally, target: FederatedEventTarget = definedExternally): FederatedPointerEvent
	protected fun createWheelEvent(from: FederatedWheelEvent): FederatedWheelEvent
	protected fun clonePointerEvent(from: FederatedPointerEvent, type: String = definedExternally): FederatedPointerEvent
	protected fun copyWheelData(from: FederatedWheelEvent, to: FederatedWheelEvent)
	protected fun copyPointerData(from: FederatedEvent<UIEvent>, to: FederatedEvent<UIEvent>)
	protected fun copyMouseData(from: FederatedEvent<UIEvent>, to: FederatedEvent<UIEvent>)
	protected fun copyData(from: FederatedEvent<UIEvent>, to: FederatedEvent<UIEvent>)
	protected fun trackingData(id: Int): TrackingData
	protected fun <T : FederatedEvent<UIEvent>> allocateEvent(constructor: AllocateEvent<T>): T
	protected fun <T : FederatedEvent<UIEvent>> freeEvent(event: T)
}

open external class EventSystem(renderer: Renderer) {
	open val rootBoundary: EventBoundary
	open val supportsTouchEvents: Boolean
	open val supportsPointerEvents: Boolean
	open var autoPreventDefault: Boolean
	open var cursorStyle: Object<String, dynamic /* String | (mode: String) -> Unit | CSSStyleDeclaration */>
	open val domElement: HTMLElement
	open val resolution: Double
	open val renderer: Renderer
	
	fun destroy()
	fun setCursor(mode: String)
	protected fun onWheel(nativeEvent: WheelEvent)
	fun setTargetElement(element: HTMLElement)
	fun mapPositionToPoint(point: IPointData, x: Double, y: Double)
	protected fun normalizeWheelEvent(nativeEvent: WheelEvent): FederatedWheelEvent
}

external object FederatedDisplayObject {
	var interactive: Boolean
	var interactiveChildren: Boolean
	var hitArea: IHitArea
}

open external class FederatedEvent<N : UIEvent /* = UIEvent */>(manager: EventBoundary) : UIEvent {
	override var bubbles: Boolean
	open var cancelBubble: Boolean
	override val cancelable: Boolean
	override val composed: Boolean
	
	@Suppress("VAR_TYPE_MISMATCH_ON_OVERRIDE")
	override var currentTarget: FederatedEventTarget
	override var defaultPrevented: Boolean
	override var eventPhase: Short
	override var isTrusted: Boolean
	open var returnValue: Boolean
	open var srcElement: EventTarget
	override var target: FederatedEventTarget
	override var timeStamp: Number
	override var type: String
	open var nativeEvent: N
	open var originalEvent: FederatedEvent<N>
	open var propagationStopped: Boolean
	open var propagationImmediateStopped: Boolean
	open var path: Array<FederatedEventTarget>
	open val manager: EventBoundary
	override var detail: Int
	override var view: WindowProxy
	open var which: Int
	open var layer: Point
	open var page: Point
	
	open val layerX: Double
	open val layerY: Double
	open val pageX: Double
	open val pageY: Double
	open val data: FederatedEvent<N> /* this */
	
	@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE", "OVERRIDING_FINAL_MEMBER")
	override fun composedPath(): Array<FederatedEventTarget>
	
	@Deprecated("Unimplemented method included for implementing the DOM interface UIEvent. Since Pixi 6.5.4", level = DeprecationLevel.ERROR)
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override fun initEvent(_type: String, _bubbles: Boolean, _cancelable: Boolean)
	
	@Deprecated("Unimplemented method included for implementing the DOM interface UIEvent. Since Pixi 6.5.4", level = DeprecationLevel.ERROR)
	fun initUiEvent(
		_typeArg: String,
		_bubblesArg: Boolean = definedExternally,
		_cancelableArg: Boolean = definedExternally,
		_viewArg: WindowProxy? = definedExternally,
		_detailArg: Int = definedExternally,
	)
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override fun preventDefault()
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override fun stopImmediatePropagation()
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override fun stopPropagation()
	
	open val AT_TARGET: Short
	open val BUBBLING_PHASE: Short
	open val CAPTURING_PHASE: Short
	open val NONE: Short
}

@Suppress("INTERFACE_WITH_SUPERCLASS", "MANY_CLASSES_IN_SUPERTYPE_LIST")
external interface FederatedEventTarget : EventTarget, EventEmitter {
	var cursor: Cursor
	val parent: FederatedEventTarget?
	val children: ReadonlyArray<FederatedEventTarget>?
	var interactive: Boolean
	var interactiveChildren: Boolean
	var hitArea: IHitArea?
}

@Suppress("MANY_CLASSES_IN_SUPERTYPE_LIST")
open external class FederatedMouseEvent : FederatedEvent<UIEvent /* MouseEvent | PointerEVent | TouchEvent */>, MouseEvent {
	override var altKey: Boolean
	override var button: Short
	override var buttons: Short
	override var ctrlKey: Boolean
	override var metaKey: Boolean
	override var relatedTarget: EventTarget
	override var shiftKey: Boolean
	open var client: Point
	override var detail: Int
	open var movement: Point
	open var offset: Point
	open var global: Point
	
	override val clientX: Int
	override val clientY: Int
	override val x: Double
	override val y: Double
	open val movementX: Double
	open val movementY: Double
	override var offsetX: Double
	override var offsetY: Double
	open var globalX: Double
	open var globalY: Double
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override var pageX: Double
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override var pageY: Double
	
	@Suppress("OVERRIDING_FINAL_MEMBER")
	override fun getModifierState(key: String): Boolean
	fun initMouseEvent(
		_typeArg: String,
		_canBubbleArg: Boolean,
		_cancelableArg: Boolean,
		_viewArg: WindowProxy,
		_detailArg: Int,
		_screenXArg: Int,
		_screenYArg: Int,
		_clientXArg: Int,
		_clientYArg: Int,
		_ctrlKeyArg: Boolean,
		_altKeyArg: Boolean,
		_shiftKeyArg: Boolean,
		_metaKeyArg: Boolean,
		_buttonArg: Short,
		_relatedTargetArg: EventTarget
	)
}

@Suppress("MANY_CLASSES_IN_SUPERTYPE_LIST")
open external class FederatedPointerEvent : FederatedMouseEvent, PointerEvent {
	override var pointerId: Int
	override var width: Double
	override var height: Double
	override var isPrimary: Boolean
	override var pointerType: String
	
	@Suppress("PROPERTY_TYPE_MISMATCH_ON_OVERRIDE")
	override var pressure: Double
	
	@Suppress("PROPERTY_TYPE_MISMATCH_ON_OVERRIDE")
	override var tangentialPressure: Double
	
	@Suppress("PROPERTY_TYPE_MISMATCH_ON_OVERRIDE")
	override var tiltX: Double
	
	@Suppress("PROPERTY_TYPE_MISMATCH_ON_OVERRIDE")
	override var tiltY: Double
	
	override var detail: Int
	
	fun getCoalescedEvents(): Array<PointerEvent>
	fun getPredictedEvents(): Array<PointerEvent>
}

@Suppress("MANY_CLASSES_IN_SUPERTYPE_LIST")
open external class FederatedWheelEvent : FederatedMouseEvent, WheelEvent {
	override var deltaMode: Int
	override var deltaX: Double
	override var deltaY: Double
	override var deltaZ: Double
	open var DOM_DELTA_LINE: Short
	open var DOM_DELTA_PAGE: Short
	open var DOM_DELTA_PIXEL: Short
}

external interface Renderer {
	var _lastObjectRendered: IRenderableObject
	var view: HTMLCanvasElement
	var resolution: Double
	var plugins: Record<String, Any>
}

external interface Clicks {
	var clickCount: Int
	var target: FederatedEventTarget
	var timeStamp: Double
}

external interface TrackingData {
	var pressTargetsByButton: Object<Int, Array<FederatedEventTarget>>
	var clicksByButton: Object<Int, Clicks>
	var overTargets: Array<FederatedEventTarget>
}

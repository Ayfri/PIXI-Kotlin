@file:JsModule("@pixi/accessibility")

package pixi.typings.accessibility

import org.w3c.dom.HTMLElement
import pixi.typings.core.AbstractRenderer
import pixi.typings.core.Renderer
import pixi.typings.display.DisplayObject
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.math.Rectangle

open external class AccessibilityManager(renderer: AbstractRenderer) {
	constructor(renderer: Renderer)
	
	open var debug: Boolean
	open var renderer: AbstractRenderer
	open val isActive: Boolean
	open val isMobileAccessibility: Boolean
	open fun updateDebugHTML(div: IAccessibleHTMLElement)
	open fun capHitArea(hitArea: Rectangle)
	open fun destroy()
	
	companion object {
		var extension: ExtensionMetadata
	}
}

external val accessibleTarget: IAccessibleTarget

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface IAccessibleHTMLElement : HTMLElement {
	var type: String?
	var displayObject: DisplayObject?
}

external interface IAccessibleTarget {
	var accessible: Boolean
	var accessibleTitle: String
	var accessibleHint: String
	var tabIndex: Int
	var _accessibleActive: Boolean
	var _accessibleDiv: IAccessibleHTMLElement
	var accessibleType: String
	var accessiblePointerEvents: PointerEvents
	var accessibleChildren: Boolean /* true */
	var renderId: Int
}

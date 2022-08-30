@file:JsModule("@pixi/text")

package pixi.typings.text

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import pixi.typings.Object
import pixi.typings.core.Renderer
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.Rectangle
import pixi.typings.sprite.Sprite

external interface IFontMetrics {
	var ascent: Double
	var descent: Double
	var fontSize: Double
}

external interface ITextStyle {
	var align: TextStyleAlign
	var breakWords: Boolean
	var dropShadow: Boolean
	var dropShadowAlpha: Double
	var dropShadowAngle: Double
	var dropShadowBlur: Double
	var dropShadowColor: dynamic /* String | Int */
	var dropShadowDistance: Double
	var fill: TextStyleFill
	var fillGradientType: TEXT_GRADIENT
	var fillGradientStops: Array<Double>
	var fontFamily: dynamic /* String | Array<String> */
	var fontSize: dynamic /* Double | String */
	var fontStyle: TextStyleFontStyle
	var fontVariant: TextStyleFontVariant
	var fontWeight: TextStyleFontWeight
	var letterSpacing: Double
	var lineHeight: Double
	var lineJoin: TextStyleLineJoin
	var miterLimit: Double
	var padding: Double
	var stroke: dynamic /* String | Double */
	var strokeThickness: Double
	var textBaseline: TextStyleTextBaseline
	var trim: Boolean
	var whiteSpace: TextStyleWhiteSpace
	var wordWrap: Boolean
	var wordWrapWidth: Double
	var leading: Double
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ModernContext2D : CanvasRenderingContext2D {
	var textLetterSpacing: Double?
	var letterSpacing: Double?
}

open external class Text(
	text: String = definedExternally,
	style: PartialTextStyle = definedExternally,
	canvas: HTMLCanvasElement = definedExternally,
) : Sprite {
	constructor(text: Number = definedExternally, style: PartialTextStyle = definedExternally, canvas: HTMLCanvasElement = definedExternally)
	constructor(text: String = definedExternally, style: TextStyle = definedExternally, canvas: HTMLCanvasElement = definedExternally)
	constructor(text: Number = definedExternally, style: TextStyle = definedExternally, canvas: HTMLCanvasElement = definedExternally)
	
	open var canvas: HTMLCanvasElement
	open var context: ModernContext2D
	open var localStyleID: Int
	open var dirty: Boolean
	open var _resolution: Double
	open var _autoResolution: Boolean
	protected open var _text: String
	protected open var _font: String
	protected open var _style: TextStyle
	protected open var _styleListener: () -> Unit
	
	override var width: Double
	override var height: Double
	open var style: PartialTextStyle
	open var text: String /* String | Number for setter */
	open var resolution: Double
	
	open fun updateText(respectDirty: Boolean)
	override fun _render(renderer: Renderer)
	override fun getLocalBounds(rect: Rectangle): Rectangle
	override fun updateTransform()
	override fun getBounds(skipUpdate: Boolean, rect: Rectangle): Rectangle
	override fun _calculateBounds()
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		var nextLineHeightBehavior: Boolean
		var experimentalLetterSpacing: Boolean
	}
}

open external class TextMetrics(
	text: String,
	style: TextStyle,
	width: Double,
	height: Double,
	lines: Array<String>,
	lineWidths: Array<Double>,
	lineHeight: Double,
	maxLineWidth: Double,
	fontProperties: IFontMetrics,
) {
	open var text: String
	open var style: TextStyle
	open var width: Double
	open var height: Double
	open var lines: Array<String>
	open var lineWidths: Array<Double>
	open var lineHeight: Double
	open var maxLineWidth: Double
	open var fontProperties: IFontMetrics
	
	companion object {
		var METRICS_STRING: String
		var BASELINE_SYMBOL: String
		var BASELINE_MULTIPLIER: Double
		var HEIGHT_MULTIPLIER: Double
		var _canvas: dynamic /* HTMLCanvasElement | OffscreenCanvas */
		var _context: dynamic /* CanvasRenderingContext2D | OffscreenCanvasRenderingContext2D */
		var _fonts: Object<String, IFontMetrics>
		var _newlines: Array<Int>
		var _breakingSpaces: Array<Int>
		
		fun measureText(
			text: String,
			style: TextStyle,
			wordWrap: Boolean = definedExternally,
			canvas: HTMLCanvasElement = definedExternally,
		): TextMetrics
		
		fun measureText(
			text: String,
			style: TextStyle,
			wordWrap: Boolean = definedExternally,
			canvas: OffscreenCanvas = definedExternally,
		): TextMetrics
		
		fun isBreakingSpace(char: String, _nextChar: String = definedExternally): Boolean
		fun canBreakWords(_token: String, breakWords: Boolean): Boolean
		fun canBreakChars(
			_char: String,
			_nextChar: String,
			_token: String,
			_index: Int,
			_breakWords: Boolean,
		): Boolean
		
		fun wordWrapSplit(token: String): Array<String>
		fun measureFont(font: String): IFontMetrics
		fun clearMetrics(font: String = definedExternally)
	}
}

open external class TextStyle(style: PartialTextStyle = definedExternally) {
	open var styleID: Int
	protected open var _align: TextStyleAlign
	protected open var _breakWords: Boolean
	protected open var _dropShadow: Boolean
	protected open var _dropShadowAlpha: Double
	protected open var _dropShadowAngle: Double
	protected open var _dropShadowBlur: Double
	protected open var _dropShadowColor: dynamic /* String | Double */
	protected open var _dropShadowDistance: Double
	protected open var _fill: TextStyleFill
	protected open var _fillGradientType: TEXT_GRADIENT
	protected open var _fillGradientStops: Array<Double>
	protected open var _fontFamily: dynamic /* String | Array<String> */
	protected open var _fontSize: dynamic /* Double | String */
	protected open var _fontStyle: TextStyleFontStyle
	protected open var _fontVariant: TextStyleFontVariant
	protected open var _fontWeight: TextStyleFontWeight
	protected open var _letterSpacing: Double
	protected open var _lineHeight: Double
	protected open var _lineJoin: TextStyleLineJoin
	protected open var _miterLimit: Double
	protected open var _padding: Double
	protected open var _stroke: dynamic /* String | Double */
	protected open var _strokeThickness: Double
	protected open var _textBaseline: TextStyleTextBaseline
	protected open var _trim: Boolean
	protected open var _whiteSpace: TextStyleWhiteSpace
	protected open var _wordWrap: Boolean
	protected open var _wordWrapWidth: Double
	protected open var _leading: Double
	
	open var align: TextStyleAlign
	open var breakWords: Boolean
	open var dropShadow: Boolean
	open var dropShadowAlpha: Double
	open var dropShadowAngle: Double
	open var dropShadowBlur: Double
	open var dropShadowColor: dynamic /* String | Double */
	open var dropShadowDistance: Double
	open var fill: TextStyleFill
	open var fillGradientType: TEXT_GRADIENT
	open var fillGradientStops: Array<Double>
	open var fontFamily: dynamic /* String | Array<String> */
	open var fontSize: dynamic /* Double | String */
	open var fontStyle: TextStyleFontStyle
	open var fontVariant: TextStyleFontVariant
	open var fontWeight: TextStyleFontWeight
	open var letterSpacing: Double
	open var lineHeight: Double
	open var leading: Double
	open var lineJoin: TextStyleLineJoin
	open var miterLimit: Double
	open var padding: Double
	open var stroke: dynamic /* String | Double */
	open var strokeThickness: Double
	open var textBaseline: TextStyleTextBaseline
	open var trim: Boolean
	open var whiteSpace: TextStyleWhiteSpace
	open var wordWrap: Boolean
	open var wordWrapWidth: Double
	
	open fun clone(): TextStyle
	open fun reset()
	open fun toFontString(): String
}

external interface PartialTextStyle {
	var styleID: Double?
	
	var align: TextStyleAlign?
	var breakWords: Boolean?
	var dropShadow: Boolean?
	var dropShadowAlpha: Double?
	var dropShadowAngle: Double?
	var dropShadowBlur: Double?
	var dropShadowColor: dynamic? /* String | Double */
	var dropShadowDistance: Double?
	var fill: TextStyleFill?
	var fillGradientType: TEXT_GRADIENT?
	var fillGradientStops: Array<Double>?
	var fontFamily: dynamic? /* String | Array<String> */
	var fontSize: dynamic? /* Double | String */
	var fontStyle: TextStyleFontStyle?
	var fontVariant: TextStyleFontVariant?
	var fontWeight: TextStyleFontWeight?
	var letterSpacing: Double?
	var lineHeight: Double?
	var leading: Double?
	var lineJoin: TextStyleLineJoin?
	var miterLimit: Double?
	var padding: Double?
	var stroke: dynamic? /* String | Double */
	var strokeThickness: Double?
	var textBaseline: TextStyleTextBaseline?
	var trim: Boolean?
	var whiteSpace: TextStyleWhiteSpace?
	var wordWrap: Boolean?
	var wordWrapWidth: Double?
}

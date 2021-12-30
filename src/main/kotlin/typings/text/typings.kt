@file:JsModule("@pixi/text")

package typings.text

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import typings.Indexed
import typings.Number
import typings.core.Renderer
import typings.display.IDestroyOptions
import typings.math.Rectangle
import typings.sprite.Sprite

external interface IFontMetrics {
	var ascent: Number
	var descent: Number
	var fontSize: Number
}

external interface ITextStyle {
	var align: TextStyleAlign
	var breakWords: Boolean
	var dropShadow: Boolean
	var dropShadowAlpha: Number
	var dropShadowAngle: Number
	var dropShadowBlur: Number
	var dropShadowColor: dynamic /* String | Number */
	var dropShadowDistance: Number
	var fill: TextStyleFill
	var fillGradientType: TEXT_GRADIENT
	var fillGradientStops: Array<Number>
	var fontFamily: dynamic /* String | Array<String> */
	var fontSize: dynamic /* Number | String */
	var fontStyle: TextStyleFontStyle
	var fontVariant: TextStyleFontVariant
	var fontWeight: TextStyleFontWeight
	var letterSpacing: Number
	var lineHeight: Number
	var lineJoin: TextStyleLineJoin
	var miterLimit: Number
	var padding: Number
	var stroke: dynamic /* String | Number */
	var strokeThickness: Number
	var textBaseline: TextStyleTextBaseline
	var trim: Boolean
	var whiteSpace: TextStyleWhiteSpace
	var wordWrap: Boolean
	var wordWrapWidth: Number
	var leading: Number
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ModernContext2D : CanvasRenderingContext2D {
	var textLetterSpacing: Number?
	var letterSpacing: Number?
}

open external class Text(
	text: String,
	style: PartialTextStyle = definedExternally,
	canvas: HTMLCanvasElement = definedExternally
) : Sprite {
	constructor(text: String, style: TextStyle = definedExternally, canvas: HTMLCanvasElement = definedExternally)
	
	open var canvas: HTMLCanvasElement
	open var context: ModernContext2D
	open var localStyleID: Number
	open var dirty: Boolean
	open var _resolution: Number
	open var _autoResolution: Boolean
	protected open var _text: String
	protected open var _font: String
	protected open var _style: TextStyle
	protected open var _styleListener: () -> Unit
	
	override var width: Number
	override var height: Number
	open var style: PartialTextStyle
	open var text: String
	open var resolution: Number
	
	open fun updateText(respectDirty: Boolean)
	override fun _render(renderer: Renderer)
	override fun getLocalBounds(rect: Rectangle): Rectangle
	override fun _calculateBounds()
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		var nextLineHeightBehavior: Boolean
	}
}

open external class TextMetrics(
	text: String,
	style: TextStyle,
	width: Number,
	height: Number,
	lines: Array<String>,
	lineWidths: Array<Number>,
	lineHeight: Number,
	maxLineWidth: Number,
	fontProperties: IFontMetrics
) {
	open var text: String
	open var style: TextStyle
	open var width: Number
	open var height: Number
	open var lines: Array<String>
	open var lineWidths: Array<Number>
	open var lineHeight: Number
	open var maxLineWidth: Number
	open var fontProperties: IFontMetrics
	
	companion object {
		var METRICS_STRING: String
		var BASELINE_SYMBOL: String
		var BASELINE_MULTIPLIER: Number
		var HEIGHT_MULTIPLIER: Number
		var _canvas: dynamic /* HTMLCanvasElement | OffscreenCanvas */
		var _context: dynamic /* CanvasRenderingContext2D | OffscreenCanvasRenderingContext2D */
		var _fonts: Indexed<String, IFontMetrics>
		var _newlines: Array<Number>
		var _breakingSpaces: Array<Number>
		
		fun measureText(
			text: String,
			style: TextStyle,
			wordWrap: Boolean = definedExternally,
			canvas: HTMLCanvasElement = definedExternally
		): TextMetrics
		
		fun measureText(
			text: String,
			style: TextStyle,
			wordWrap: Boolean = definedExternally,
			canvas: OffscreenCanvas = definedExternally
		): TextMetrics
		
		fun isBreakingSpace(char: String, _nextChar: String = definedExternally): Boolean
		fun canBreakWords(_token: String, breakWords: Boolean): Boolean
		fun canBreakChars(
			_char: String,
			_nextChar: String,
			_token: String,
			_index: Number,
			_breakWords: Boolean
		): Boolean
		
		fun wordWrapSplit(token: String): Array<String>
		fun measureFont(font: String): IFontMetrics
		fun clearMetrics(font: String = definedExternally)
	}
}

open external class TextStyle(style: PartialTextStyle = definedExternally) {
	open var styleID: Number
	protected open var _align: TextStyleAlign
	protected open var _breakWords: Boolean
	protected open var _dropShadow: Boolean
	protected open var _dropShadowAlpha: Number
	protected open var _dropShadowAngle: Number
	protected open var _dropShadowBlur: Number
	protected open var _dropShadowColor: dynamic /* String | Number */
	protected open var _dropShadowDistance: Number
	protected open var _fill: TextStyleFill
	protected open var _fillGradientType: TEXT_GRADIENT
	protected open var _fillGradientStops: Array<Number>
	protected open var _fontFamily: dynamic /* String | Array<String> */
	protected open var _fontSize: dynamic /* Number | String */
	protected open var _fontStyle: TextStyleFontStyle
	protected open var _fontVariant: TextStyleFontVariant
	protected open var _fontWeight: TextStyleFontWeight
	protected open var _letterSpacing: Number
	protected open var _lineHeight: Number
	protected open var _lineJoin: TextStyleLineJoin
	protected open var _miterLimit: Number
	protected open var _padding: Number
	protected open var _stroke: dynamic /* String | Number */
	protected open var _strokeThickness: Number
	protected open var _textBaseline: TextStyleTextBaseline
	protected open var _trim: Boolean
	protected open var _whiteSpace: TextStyleWhiteSpace
	protected open var _wordWrap: Boolean
	protected open var _wordWrapWidth: Number
	protected open var _leading: Number
	
	open var align: TextStyleAlign
	open var breakWords: Boolean
	open var dropShadow: Boolean
	open var dropShadowAlpha: Number
	open var dropShadowAngle: Number
	open var dropShadowBlur: Number
	open var dropShadowColor: dynamic /* String | Number */
	open var dropShadowDistance: Number
	open var fill: TextStyleFill
	open var fillGradientType: TEXT_GRADIENT
	open var fillGradientStops: Array<Number>
	open var fontFamily: dynamic /* String | Array<String> */
	open var fontSize: dynamic /* Number | String */
	open var fontStyle: TextStyleFontStyle
	open var fontVariant: TextStyleFontVariant
	open var fontWeight: TextStyleFontWeight
	open var letterSpacing: Number
	open var lineHeight: Number
	open var leading: Number
	open var lineJoin: TextStyleLineJoin
	open var miterLimit: Number
	open var padding: Number
	open var stroke: dynamic /* String | Number */
	open var strokeThickness: Number
	open var textBaseline: TextStyleTextBaseline
	open var trim: Boolean
	open var whiteSpace: TextStyleWhiteSpace
	open var wordWrap: Boolean
	open var wordWrapWidth: Number
	
	open fun clone(): TextStyle
	open fun reset()
	open fun toFontString(): String
}

external interface PartialTextStyle {
	var styleID: Number?
	
	var align: TextStyleAlign?
	var breakWords: Boolean?
	var dropShadow: Boolean?
	var dropShadowAlpha: Number?
	var dropShadowAngle: Number?
	var dropShadowBlur: Number?
	var dropShadowColor: dynamic? /* String | Number */
	var dropShadowDistance: Number?
	var fill: TextStyleFill?
	var fillGradientType: TEXT_GRADIENT?
	var fillGradientStops: Array<Number>?
	var fontFamily: dynamic? /* String | Array<String> */
	var fontSize: dynamic? /* Number | String */
	var fontStyle: TextStyleFontStyle?
	var fontVariant: TextStyleFontVariant?
	var fontWeight: TextStyleFontWeight?
	var letterSpacing: Number?
	var lineHeight: Number?
	var leading: Number?
	var lineJoin: TextStyleLineJoin?
	var miterLimit: Number?
	var padding: Number?
	var stroke: dynamic? /* String | Number */
	var strokeThickness: Number?
	var textBaseline: TextStyleTextBaseline?
	var trim: Boolean?
	var whiteSpace: TextStyleWhiteSpace?
	var wordWrap: Boolean?
	var wordWrapWidth: Number?
}

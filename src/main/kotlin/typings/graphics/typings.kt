@file:JsModule("@pixi/graphics")

package typings.graphics

import kotlinext.js.Record
import org.khronos.webgl.Float32Array
import typings.constants.BLEND_MODES
import typings.core.*
import typings.display.Bounds
import typings.display.Container
import typings.display.IDestroyOptions
import typings.math.*

external object ArcUtils {
	fun curveTo(x1: Number, y1: Number, x2: Number, y2: Number, radius: Number, points: Array<Number>): IArcLikeShape
	fun arc(_startX: Number, _startY: Number, cx: Number, cy: Number, radius: Number, startAngle: Number, endAngle: Number, _anticlockwise: Boolean, points: Array<Number>)
}

open external class BatchPart {
	open var style: dynamic /* LineStyle | FillStyle */
	open var start: Number
	open var size: Number
	open var attribStart: Number
	open var attribSize: Number

	open fun begin(style: LineStyle, startIndex: Number, attribStart: Number)
	open fun begin(style: FillStyle, startIndex: Number, attribStart: Number)
	open fun end(endIndex: Number, endAttrib: Number)
	open fun reset()
}

external object BezierUtils {
	fun curveLength(fromX: Number, fromY: Number, cpX: Number, cpY: Number, cpX2: Number, cpY2: Number, toX: Number, toY: Number): Number
	fun curveTo(cpX: Number, cpY: Number, cpX2: Number, cpY2: Number, toX: Number, toY: Number, points: Array<Number>)
}

external fun buildLine(graphicsData: GraphicsData, graphicsGeometry: GraphicsGeometry)

open external class FillStyle {
	open var color: Number
	open var alpha: Number
	open var texture: Texture<Resource>
	open var matrix: Matrix
	open var visible: Boolean
	open fun clone(): FillStyle
	open fun reset()
	open fun destroy()
}

open external class Graphics(geometry: GraphicsGeometry) : Container {
	constructor()

	open var shader: Shader
	open var currentPath: Polygon
	protected open var batches: Array<IGraphicsBatchElement>
	protected open var batchTint: Number
	protected open var batchDirty: Number
	protected open var vertexData: Float32Array
	protected open var _fillStyle: FillStyle
	protected open var _lineStyle: LineStyle
	protected open var _matrix: Matrix
	protected open var _holeMode: Boolean
	protected open var _transformID: Number
	protected open var _tint: Number
	open val geometry: GraphicsGeometry
	open var blendMode: BLEND_MODES
	open var tint: Number
	open val fill: FillStyle
	open val line: LineStyle

	open fun clone(): Graphics
	open fun lineStyle(width: Number, color: Number, alpha: Number, alignment: Number, native: Number): Graphics /* this */
	open fun lineStyle(width: Number, color: Number, alpha: Number, alignment: Number): Graphics /* this */
	open fun lineStyle(width: Number, color: Number, alpha: Number): Graphics /* this */
	open fun lineStyle(width: Number, color: Number): Graphics /* this */
	open fun lineStyle(width: Number): Graphics /* this */
	open fun lineStyle(options: ILineStyleOptions): Graphics /* this */
	open fun lineStyle(): Graphics /* this */
	open fun lineTextureStyle(options: ILineStyleOptions): Graphics /* this */
	protected open fun startPoly()
	open fun finishPoly()
	open fun moveTo(x: Number, y: Number): Graphics /* this */
	open fun lineTo(x: Number, y: Number): Graphics /* this */
	protected fun _initCurve(x: Number, y: Number)
	protected fun _initCurve(x: Number)
	protected fun _initCurve()
	open fun quadraticCurveTo(cpX: Number, cpY: Number, toX: Number, toY: Number): Graphics /* this */
	open fun bezierCurveTo(cpX: Number, cpY: Number, cpX2: Number, cpY2: Number, toX: Number, toY: Number): Graphics /* this */
	open fun arcTo(x1: Number, y1: Number, x2: Number, y2: Number, radius: Number)
	open fun arc(cx: Number, cy: Number, radius: Number, startAngle: Number, endAngle: Number, anticlockwise: Boolean): Graphics /* this */
	open fun arc(cx: Number, cy: Number, radius: Number, startAngle: Number, endAngle: Number): Graphics /* this */
	open fun beginFill(color: Number, alpha: Number): Graphics /* this */
	open fun beginFill(color: Number): Graphics /* this */
	open fun beginFill(): Graphics /* this */
	open fun beginTextureFill(options: IFillStyleOptions): Graphics /* this */
	open fun endFill(): Graphics /* this */
	open fun drawRect(x: Number, y: Number, width: Number, height: Number): Graphics /* this */
	open fun drawRoundedRect(x: Number, y: Number, width: Number, height: Number, radius: Number): Graphics /* this */
	open fun drawCircle(x: Number, y: Number, radius: Number): Graphics /* this */
	open fun drawEllipse(x: Number, y: Number, width: Number, height: Number): Graphics /* this */
	open fun drawPolygon(vararg path: Array<Number>): Graphics /* this */
	open fun drawPolygon(vararg path: Array<Point>): Graphics /* this */
	open fun drawPolygon(path: Array<Number>): Graphics /* this */
	open fun drawPolygon(path: Array<Point>): Graphics /* this */
	open fun drawPolygon(path: Polygon): Graphics /* this */
	open fun drawShape(shape: IShape): Graphics /* this */
	open fun clear()
	open fun isFastRect(): Boolean
	override fun _render(renderer: Renderer)
	protected open fun _populateBatches()
	protected open fun _renderBatched(renderer: Renderer)
	protected open fun _renderDirect(renderer: Renderer)
	protected open fun _renderDrawCallDirect(renderer: Renderer, drawCall: BatchDrawCall)
	protected open fun _resolveDirectShader(renderer: Renderer): Shader
	override fun _calculateBounds()
	open fun containsPoint(point: IPointData): Boolean
	protected open fun calculateTints()
	protected open fun calculateVertices()
	open fun closePath(): Graphics /* this */
	open fun setMatrix(matrix: Matrix): Graphics /* this */
	open fun beginHole(): Graphics /* this */
	open fun endHole(): Graphics /* this */
	override fun destroy(options: IDestroyOptions)
	override fun destroy(options: Boolean)
	override fun destroy()

	companion object {
		var _TEMP_POINT: Point
	}
}

external val GRAPHICS_CURVES: IGraphicsCurvesSettings

open external class GraphicsData(shape: IShape, fillStyle: FillStyle, lineStyle: LineStyle, matrix: Matrix) {
	constructor(shape: IShape, fillStyle: FillStyle, lineStyle: LineStyle)
	constructor(shape: IShape, fillStyle: FillStyle)
	constructor(shape: IShape)

	open var shape: IShape
	open var lineStyle: LineStyle
	open var fillStyle: FillStyle
	open var matrix: Matrix
	open var type: SHAPES
	open var points: Array<Number>
	open var holes: Array<GraphicsData>

	open fun clone(): GraphicsData
	open fun destroy()
}

open external class GraphicsGeometry : BatchGeometry {
	open var closePointEps: Number
	open var boundsPadding: Number
	open var uvsFloat32: Float32Array
	open var indicesUint16Array: dynamic /* Uint16Array | Uint32Array */
	open var batchable: Boolean
	open var points: Array<Number>
	open var colors: Array<Number>
	open var uvs: Array<Number>
	open var indices: Array<Number>
	open var textureIds: Array<Number>
	open var graphicsData: Array<GraphicsData>
	open var drawCalls: Array<BatchDrawCall>
	open var batchDirty: Number
	open var batches: Array<BatchPart>
	protected open var dirty: Number
	protected open var cacheDirty: Number
	protected open var clearDiry: Number
	protected open var shapeIndex: Number
	protected open var _bounds: Bounds
	protected open var boundsDirty: Number
	open var bounds: Bounds

	protected fun invalidate()
	protected fun clear(): GraphicsGeometry
	protected fun drawShape(shape: IShape_2, fillStyle: FillStyle, lineStyle: LineStyle, matrix: Matrix): GraphicsGeometry
	protected fun drawShape(shape: IShape_2, fillStyle: FillStyle, lineStyle: LineStyle): GraphicsGeometry
	protected fun drawShape(shape: IShape_2, fillStyle: FillStyle): GraphicsGeometry
	protected fun drawShape(shape: IShape_2): GraphicsGeometry
	open fun drawHole(shape: IShape_2, matrix: Matrix): GraphicsGeometry
	open fun drawHole(shape: IShape_2): GraphicsGeometry
	override fun destroy()
	open fun containsPoint(point: IPointData): Boolean
	open fun updateBatches(allow32Indices: Boolean)
	open fun updateBatches()
	protected open fun _compareStyles(styleA: FillStyle, styleB: FillStyle): Boolean
	protected open fun _compareStyles(styleA: FillStyle, styleB: LineStyle): Boolean
	protected open fun _compareStyles(styleA: LineStyle, styleB: FillStyle): Boolean
	protected open fun _compareStyles(styleA: LineStyle, styleB: LineStyle): Boolean
	protected open fun validateBatching(): Boolean
	protected open fun packBatches()
	protected open fun isBatchable(): Boolean
	protected open fun packAttributes()
	protected open fun processFill(data: GraphicsData)
	protected open fun processLine(data: GraphicsData)
	protected open fun processHoles(data: Array<GraphicsData>)
	protected open fun calculateBounds()
	protected open fun transformPoints(points: Array<Number>, matrix: Matrix)
	protected open fun addColors(colors: Array<Number>, color: Number, alpha: Number, size: Number, offset: Number)
	protected open fun addColors(colors: Array<Number>, color: Number, alpha: Number, size: Number)
	protected open fun addUvs(vers: Array<Number>, uvs: Array<Number>, texture: Texture<Resource>, start: Number, size: Number, matrix: Matrix)
	protected open fun addUvs(vers: Array<Number>, uvs: Array<Number>, texture: Texture<Resource>, start: Number, size: Number)
	protected open fun adjustUvs(uvs: Array<Number>, texture: Texture<Resource>, start: Number, size: Number)

	companion object {
		var BATCHABLE_SIZE: Number
	}
}

external object graphicsUtils {
	var buildPoly: IShapeBuildCommand
	var buildCircle: IShapeBuildCommand
	var buildRectangle: IShapeBuildCommand
	var buildRoundedRectangle: IShapeBuildCommand
	var buildLine: (graphicsData: GraphicsData, GraphicsGeometry: GraphicsGeometry) -> Unit
	var ArcUtils: ArcUtils
	var BezierUtils: BezierUtils
	var QuadraticUtils: QuadraticUtils
	var BatchPart: BatchPart
	var FILL_COMMANDS: Record<SHAPES, IShapeBuildCommand>
	var BATCH_POOL: Array<BatchPart>
	var DRAW_CALL_POOL: BatchDrawCall
}

external interface IArcLikeShape {
	var cx: Number
	var cy: Number
	var radius: Number
	var startAngle: Number
	var endAngle: Number
	var anticlockwise: Boolean
}

external interface IFillStyleOptions {
	var color: Number?
	var alpha: Number?
	var texture: Texture<Resource>?
	var matrix: Matrix?
}

external interface IGraphicsBatchElement {
	var vertexData: Float32Array
	var blendMode: BLEND_MODES
	var indices: dynamic /* Uint16Array | Uint32Array */
	var uvs: Float32Array
	var alpha: Number
	var worldAlpha: Number
	var _batchRGB: Array<Number>
	var _tintRGB: Number
	var _texture: Texture<Resource>
}

external interface IGraphicsCurvesSettings {
	var adaptive: Boolean
	var maxLength: Number
	var minSegments: Number
	var maxSegments: Number
	var epsilon: Number
	fun _segmentsCount(length: Number, defaultSegments: Number): Number
	fun _segmentsCount(length: Number): Number
}

external interface ILineStyleOptions : IFillStyleOptions {
	var width: Number?
	var alignment: Number?
	var native: Boolean?
	var cap: LINE_CAP?
	var join: LINE_JOIN?
	var miterLimit: Number?
}

external interface IShapeBuildCommand {
	fun build(graphicsData: GraphicsData)
	fun triangulate(graphicsData: GraphicsData, target: GraphicsGeometry)
}

open external class LineStyle : FillStyle {
	open var width: Number
	open var alignment: Number
	open var native: Boolean
	open var cap: LINE_CAP
	open var join: LINE_JOIN
	open var miterLimit: Number

	override fun clone(): LineStyle
	override fun reset()
}

external object QuadraticUtils {
	fun curveLength(fromX: Number, fromY: Number, cpX: Number, cpY: Number, toX: Number, toY: Number): Number
	fun curveTo(cpX: Number, cpY: Number, toX: Number, toY: Number, points: Array<Number>)
}
@file:JsModule("@pixi/graphics")

package pixi.typings.graphics

import kotlinx.js.Record
import org.khronos.webgl.ArrayBufferView
import org.khronos.webgl.Float32Array
import pixi.externals.Color
import pixi.externals.ColorArr
import pixi.typings.constants.BLEND_MODES
import pixi.typings.core.BatchDrawCall
import pixi.typings.core.BatchGeometry
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Shader
import pixi.typings.core.Texture
import pixi.typings.display.Bounds
import pixi.typings.display.Container
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.IPointData
import pixi.typings.math.IShape
import pixi.typings.math.Matrix
import pixi.typings.math.Point
import pixi.typings.math.Polygon
import pixi.typings.math.SHAPES

external class ArcUtils {
	companion object {
		fun curveTo(x1: Double, y1: Double, x2: Double, y2: Double, radius: Double, points: Array<Double>): IArcLikeShape?
		fun arc(
			_startX: Double,
			_startY: Double,
			cx: Double,
			cy: Double,
			radius: Double,
			startAngle: Double,
			endAngle: Double,
			_anticlockwise: Boolean,
			points: Array<Double>
		)
	}
}

open external class BatchPart {
	open var style: dynamic /* LineStyle | FillStyle */
	open var start: Int
	open var size: Int
	open var attribStart: Int
	open var attribSize: Int
	
	open fun begin(style: LineStyle, startIndex: Int, attribStart: Int)
	open fun begin(style: FillStyle, startIndex: Int, attribStart: Int)
	open fun end(endIndex: Int, endAttrib: Int)
	open fun reset()
}

external class BezierUtils {
	companion object {
		fun curveLength(fromX: Double, fromY: Double, cpX: Double, cpY: Double, cpX2: Double, cpY2: Double, toX: Double, toY: Double): Double
		fun curveTo(cpX: Double, cpY: Double, cpX2: Double, cpY2: Double, toX: Double, toY: Double, points: Array<Double>)
	}
}

external fun buildLine(graphicsData: GraphicsData, graphicsGeometry: GraphicsGeometry)

open external class FillStyle {
	open var color: Color
	open var alpha: Double
	open var texture: Texture<Resource>
	open var matrix: Matrix
	open var visible: Boolean
	open fun clone(): FillStyle
	open fun reset()
	open fun destroy()
}

open external class Graphics(geometry: GraphicsGeometry = definedExternally) : Container {
	open var shader: Shader
	open var pluginName: String
	open var currentPath: Polygon
	protected open var batches: Array<IGraphicsBatchElement>
	protected open var batchTint: Int
	protected open var batchDirty: Int
	protected open var vertexData: Float32Array
	protected open var _fillStyle: FillStyle
	protected open var _lineStyle: LineStyle
	protected open var _matrix: Matrix
	protected open var _holeMode: Boolean
	protected open var _transformID: Int
	protected open var _tint: Color
	
	open val geometry: GraphicsGeometry
	open var blendMode: BLEND_MODES
	open var tint: Color
	open val fill: FillStyle
	open val line: LineStyle
	
	open fun clone(): Graphics
	open fun lineStyle(
		width: Double,
		color: Color = definedExternally,
		alpha: Double = definedExternally,
		alignment: Double = definedExternally,
		native: Boolean = definedExternally
	): Graphics /* this */
	
	open fun lineStyle(options: ILineStyleOptions = definedExternally): Graphics /* this */
	open fun lineTextureStyle(options: ILineStyleOptions): Graphics /* this */
	protected open fun startPoly()
	open fun finishPoly()
	open fun moveTo(x: Double, y: Double): Graphics /* this */
	open fun lineTo(x: Double, y: Double): Graphics /* this */
	protected open fun _initCurve(x: Double = definedExternally, y: Double = definedExternally)
	open fun quadraticCurveTo(cpX: Double, cpY: Double, toX: Double, toY: Double): Graphics /* this */
	open fun bezierCurveTo(cpX: Double, cpY: Double, cpX2: Double, cpY2: Double, toX: Double, toY: Double): Graphics /* this */
	open fun arcTo(x1: Double, y1: Double, x2: Double, y2: Double, radius: Double)
	open fun arc(cx: Double, cy: Double, radius: Double, startAngle: Double, endAngle: Double, anticlockwise: Boolean = definedExternally): Graphics /* this */
	open fun beginFill(color: Color = definedExternally, alpha: Double = definedExternally): Graphics /* this */
	open fun beginTextureFill(options: IFillStyleOptions = definedExternally): Graphics /* this */
	open fun endFill(): Graphics /* this */
	open fun drawRect(x: Double, y: Double, width: Double, height: Double): Graphics /* this */
	open fun drawRoundedRect(x: Double, y: Double, width: Double, height: Double, radius: Double): Graphics /* this */
	open fun drawCircle(x: Double, y: Double, radius: Double): Graphics /* this */
	open fun drawEllipse(x: Double, y: Double, width: Double, height: Double): Graphics /* this */
	open fun drawPolygon(vararg path: Array<Double>): Graphics /* this */
	open fun drawPolygon(vararg path: Array<Point>): Graphics /* this */
	open fun drawPolygon(path: Array<Double>): Graphics /* this */
	open fun drawPolygon(path: Array<Point>): Graphics /* this */
	open fun drawPolygon(path: Polygon): Graphics /* this */
	open fun drawShape(shape: IShape): Graphics /* this */
	open fun clear()
	open fun isFastRect(): Boolean
	override fun _render(renderer: Renderer)
	protected open fun _populateBatches()
	protected open fun _renderBatched(renderer: Renderer)
	protected open fun _renderDirect(renderer: Renderer)
	protected open fun _renderDrawCallDirect(renderer: Renderer, drawCall: BatchDrawCall = definedExternally)
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
	
	companion object {
		var _TEMP_POINT: Point
	}
}

external val GRAPHICS_CURVES: IGraphicsCurvesSettings

open external class GraphicsData(shape: IShape, fillStyle: FillStyle = definedExternally, lineStyle: LineStyle = definedExternally, matrix: Matrix = definedExternally) {
	open var shape: IShape
	open var lineStyle: LineStyle
	open var fillStyle: FillStyle
	open var matrix: Matrix
	open var type: SHAPES
	open var points: Array<Double>
	open var holes: Array<GraphicsData>
	
	open fun clone(): GraphicsData
	open fun destroy()
}

open external class GraphicsGeometry : BatchGeometry {
	open var closePointEps: Double
	open var boundsPadding: Double
	open var uvsFloat32: Float32Array
	open var indicesUint16Array: ArrayBufferView /* Uint16Array | Uint32Array */
	open var batchable: Boolean
	open var points: Array<Double>
	open var colors: ColorArr
	open var uvs: Array<Double>
	open var indices: Array<Double>
	open var textureIds: Array<Int>
	open var graphicsData: Array<GraphicsData>
	open var drawCalls: Array<BatchDrawCall>
	open var batchDirty: Int
	open var batches: Array<BatchPart>
	protected open var dirty: Int
	protected open var cacheDirty: Int
	protected open var clearDirty: Int
	protected open var shapeIndex: Int
	protected open var _bounds: Bounds
	protected open var boundsDirty: Int
	
	open var bounds: Bounds
	
	protected open fun invalidate()
	open fun clear(): GraphicsGeometry
	open fun drawShape(
		shape: IShape_2,
		fillStyle: FillStyle = definedExternally,
		lineStyle: LineStyle = definedExternally,
		matrix: Matrix = definedExternally
	): GraphicsGeometry
	
	open fun drawHole(shape: IShape_2, matrix: Matrix = definedExternally): GraphicsGeometry
	override fun destroy()
	open fun containsPoint(point: IPointData): Boolean
	open fun updateBatches(allow32Indices: Boolean = definedExternally)
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
	protected open fun transformPoints(points: Array<Double>, matrix: Matrix)
	protected open fun addColors(colors: Array<Int>, color: Color, alpha: Double, size: Int, offset: Int = definedExternally)
	protected open fun addTextureIds(textureIds: Array<Int>, id: Int, size: Int, offset: Int = definedExternally)
	protected open fun addUvs(vers: Array<Double>, uvs: Array<Double>, texture: Texture<Resource>, start: Int, size: Int, matrix: Matrix = definedExternally)
	protected open fun adjustUvs(uvs: Array<Double>, texture: Texture<Resource>, start: Int, size: Int)
	
	companion object {
		var BATCHABLE_SIZE: Short
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
	var cx: Double
	var cy: Double
	var radius: Double
	var startAngle: Double
	var endAngle: Double
	var anticlockwise: Boolean
}

external interface IFillStyleOptions {
	var color: Color?
	var alpha: Double?
	var texture: Texture<Resource>?
	var matrix: Matrix?
}

external interface IGraphicsBatchElement {
	var vertexData: Float32Array
	var blendMode: BLEND_MODES
	var indices: ArrayBufferView /* Uint16Array | Uint32Array */
	var uvs: Float32Array
	var alpha: Double
	var worldAlpha: Double
	var _batchRGB: Array<Int>
	var _tintRGB: Color
	var _texture: Texture<Resource>
}

external interface IGraphicsCurvesSettings {
	var adaptive: Boolean
	var maxLength: Int
	var minSegments: Int
	var maxSegments: Int
	var epsilon: Double
	fun _segmentsCount(length: Int, defaultSegments: Int = definedExternally): Int
}

external interface ILineStyleOptions : IFillStyleOptions {
	var width: Double?
	var alignment: Double?
	var native: Boolean?
	var cap: LINE_CAP?
	var join: LINE_JOIN?
	var miterLimit: Int?
}

external interface IShapeBuildCommand {
	fun build(graphicsData: GraphicsData)
	fun triangulate(graphicsData: GraphicsData, target: GraphicsGeometry)
}

open external class LineStyle : FillStyle {
	open var width: Int
	open var alignment: Double
	open var native: Boolean
	open var cap: LINE_CAP
	open var join: LINE_JOIN
	open var miterLimit: Int
	
	override fun clone(): LineStyle
	override fun reset()
}

external class QuadraticUtils {
	companion object {
		fun curveLength(fromX: Double, fromY: Double, cpX: Double, cpY: Double, toX: Double, toY: Double): Int
		fun curveTo(cpX: Double, cpY: Double, toX: Double, toY: Double, points: Array<Double>)
	}
}

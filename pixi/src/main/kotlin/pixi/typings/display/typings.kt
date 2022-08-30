@file:JsModule("@pixi/display")

package pixi.typings.display

import org.khronos.webgl.Float32Array
import pixi.typings.core.Filter
import pixi.typings.core.Renderer
import pixi.typings.math.IPointData
import pixi.typings.math.Matrix
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Rectangle
import pixi.typings.math.Transform
import pixi.typings.utils.Dict
import pixi.typings.utils.EventEmitter

open external class Bounds {
	open var minX: Double
	open var maxX: Double
	open var minY: Double
	open var maxY: Double
	open var rect: Rectangle
	open var updateID: Int
	
	open fun isEmpty(): Boolean
	open fun clear()
	open fun getRectangle(rect: Rectangle = definedExternally): Rectangle
	open fun addPoint(point: IPointData)
	open fun addPointMatrix(matrix: Matrix, point: IPointData)
	open fun addQuad(vertices: Float32Array)
	open fun addFrame(transform: Transform, x0: Double, y0: Double, x1: Double, y1: Double)
	open fun addFrameMatrix(matrix: Matrix, x0: Double, y0: Double, x1: Double, y1: Double)
	open fun addVertexData(vertexData: Float32Array, beginOffset: Int, endOffset: Int)
	open fun addVertices(transform: Transform, vertices: Float32Array, beginOffset: Int, endOffset: Int)
	open fun addVerticesMatrix(
		matrix: Matrix,
		vertices: Float32Array,
		beginOffset: Int,
		endOffset: Int,
		padX: Double = definedExternally,
		padY: Double = definedExternally
	)
	
	open fun addBounds(bounds: Bounds)
	open fun addBoundsMask(bounds: Bounds, mask: Bounds)
	open fun addBoundsMatrix(bounds: Bounds, matrix: Matrix)
	open fun addBoundsArea(bounds: Bounds, area: Rectangle)
	open fun pad(paddingX: Double = definedExternally, paddingY: Double = definedExternally)
	open fun addFramePad(x0: Double, y0: Double, x1: Double, y1: Double, padX: Double, padY: Double)
}

open external class Container<T : DisplayObject /* = DisplayObject */> : DisplayObject {
	open val children: Array<T>
	open var sortableChildren: Boolean
	override var sortDirty: Boolean
	override var parent: Container<DisplayObject>
	open var containerUpdateTransform: () -> Unit
	protected open var _width: Double
	protected open var _height: Double
	open var width: Double
	open var height: Double
	
	protected open fun onChildrenChange(_length: Int = definedExternally)
	open fun <U : T> addChild(vararg child: U): U
	open fun <U : T> addChildAt(child: U, index: Int): U
	open fun swapChildren(child: T, child2: T)
	open fun getChildIndex(child: T): Int
	open fun setChildIndex(child: T, index: Int)
	open fun getChildAt(index: Int): T
	
	@Suppress("CANNOT_WEAKEN_ACCESS_PRIVILEGE")
	internal override fun removeChild(child: DisplayObject)
	
	@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
	open fun <U : T> removeChild(vararg child: U): U
	open fun removeChildAt(index: Int): T
	open fun removeChildren(beginIndex: Int = definedExternally, endIndex: Int = definedExternally): Array<T>
	open fun sortChildren()
	override fun updateTransform()
	override fun calculateBounds()
	open fun getLocalBounds(rect: Rectangle = definedExternally, skipChildrenUpdate: Boolean = definedExternally): Rectangle
	protected open fun _calculateBounds()
	protected open fun _renderWithCulling(renderer: Renderer)
	override fun render(renderer: Renderer)
	protected open fun renderAdvanced(renderer: Renderer)
	protected open fun _render(_renderer: Renderer)
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
}

abstract external class DisplayObject : EventEmitter {
	abstract var sortDirty: Boolean
	open var parent: Container<DisplayObject>
	open var worldAlpha: Double
	open var transform: Transform
	open var alpha: Double
	open var visible: Boolean
	open var renderable: Boolean
	open var cullable: Boolean
	open var cullArea: Rectangle?
	open var filterArea: Rectangle
	open var filters: Array<Filter>?
	open var isSprite: Boolean
	open var isMask: Boolean
	open var _lastSortedIndex: Int
	open var _mask: dynamic /* Container | MaskData */
	open var _bounds: Bounds
	open var _localBounds: Bounds
	protected open var _zIndex: Int
	protected open var _enabledFilters: Array<Filter>
	protected open var _boundsID: Int
	protected open var _boundsRect: Rectangle
	protected open var _lastBoundsRect: Rectangle
	protected open var _destroyed: Boolean
	open var displayObjectUpdateTransform: () -> Unit
	open val destroyed: Boolean
	open val _tempDisplayObjectParent: TemporaryDisplayObject
	
	open var x: Double
	open var y: Double
	open val worldTransform: Transform
	open val localTransform: Matrix
	open var position: ObservablePoint<Any?>
		@Suppress("WRONG_SETTER_PARAMETER_TYPE")
		set(value: IPointData) = definedExternally
	open var scale: ObservablePoint<Any?>
		@Suppress("WRONG_SETTER_PARAMETER_TYPE")
		set(value: IPointData) = definedExternally
	open var pivot: ObservablePoint<Any?>
		@Suppress("WRONG_SETTER_PARAMETER_TYPE")
		set(value: IPointData) = definedExternally
	open var skew: ObservablePoint<Any?>
		@Suppress("WRONG_SETTER_PARAMETER_TYPE")
		set(value: IPointData) = definedExternally
	open var rotation: Double
	open var angle: Double
	open var zIndex: Int
	open val worldVisible: Boolean
	open var mask: dynamic? /* Container | MaskData | null */
	
	abstract fun calculateBounds()
	abstract fun removeChild(child: DisplayObject)
	abstract fun render(renderer: Renderer)
	protected open fun _recursivePostUpdateTransform()
	open fun updateTransform()
	open fun getBounds(skipUpdate: Boolean = definedExternally, rect: Rectangle = definedExternally): Rectangle
	open fun getLocalBounds(rect: Rectangle = definedExternally): Rectangle
	open fun <P : IPointData> toGlobal(position: IPointData, point: P = definedExternally, skipUpdate: Boolean = definedExternally): P
	open fun <P : IPointData> toLocal(
		position: IPointData,
		from: DisplayObject = definedExternally,
		point: P = definedExternally,
		skipUpdate: Boolean = definedExternally
	): P
	
	open fun setParent(container: Container<DisplayObject>): Container<DisplayObject>
	open fun setTransform(
		x: Double = definedExternally,
		y: Double = definedExternally,
		scaleX: Double = definedExternally,
		scaleY: Double = definedExternally,
		rotation: Double = definedExternally,
		skewX: Double = definedExternally,
		skewY: Double = definedExternally,
		pivotX: Double = definedExternally,
		pivotY: Double = definedExternally
	): DisplayObject /* this */
	
	open fun destroy(_options: IDestroyOptions = definedExternally)
	open fun destroy(_options: Boolean = definedExternally)
	open fun enableTempParent(): Container<DisplayObject>
	open fun disableTempParent(cacheParent: Container<DisplayObject>)
	
	companion object {
		fun mixin(source: Dict<Any>)
	}
}

external interface IDestroyOptions {
	var children: Boolean?
	var texture: Boolean?
	var baseTexture: Boolean?
}

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class TemporaryDisplayObject : DisplayObject {
	override fun calculateBounds(): Nothing?
	override fun removeChild(child: DisplayObject): Nothing?
	override fun render(renderer: Renderer): Nothing?
	override var sortDirty: Boolean
}

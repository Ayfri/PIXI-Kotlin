@file:JsModule("@pixi/display")

package pixi.typings.display

import org.khronos.webgl.Float32Array
import pixi.typings.Number
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
	open var minX: Number
	open var maxX: Number
	open var minY: Number
	open var maxY: Number
	open var rect: Rectangle
	open var updateID: Number
	
	open fun isEmpty(): Boolean
	open fun clear()
	open fun getRectangle(rect: Rectangle = definedExternally): Rectangle
	open fun addPoint(point: IPointData)
	open fun addPointMatrix(matrix: Matrix, point: IPointData)
	open fun addQuad(vertices: Float32Array)
	open fun addFrame(transform: Transform, x0: Number, y0: Number, x1: Number, y1: Number)
	open fun addFrameMatrix(matrix: Matrix, x0: Number, y0: Number, x1: Number, y1: Number)
	open fun addVertexData(vertexData: Float32Array, beginOffset: Number, endOffset: Number)
	open fun addVertices(transform: Transform, vertices: Float32Array, beginOffset: Number, endOffset: Number)
	open fun addVerticesMatrix(
		matrix: Matrix,
		vertices: Float32Array,
		beginOffset: Number,
		endOffset: Number,
		padX: Number = definedExternally,
		padY: Number = definedExternally
	)
	
	open fun addBounds(bounds: Bounds)
	open fun addBoundsMask(bounds: Bounds, mask: Bounds)
	open fun addBoundsMatrix(bounds: Bounds, matrix: Matrix)
	open fun addBoundsArea(bounds: Bounds, area: Rectangle)
	open fun pad(paddingX: Number, paddingY: Number)
	open fun pad(paddingX: Number)
	open fun pad()
	open fun addFramePad(x0: Number, y0: Number, x1: Number, y1: Number, padX: Number, padY: Number)
}

open external class Container : DisplayObject {
	open val children: Array<DisplayObject>
	open var sortableChildren: Boolean
	override var sortDirty: Boolean
	override var parent: Container
	open var containerUpdateTransform: () -> Unit
	protected open var _width: Number
	protected open var _height: Number
	open var width: Number
	open var height: Number
	
	protected open fun onChildrenChange(_length: Number = definedExternally)
	open fun <T : DisplayObject> addChild(vararg child: T): T
	open fun <T : DisplayObject> addChildAt(child: T, index: Number): T
	open fun swapChildren(child: DisplayObject, child2: DisplayObject)
	open fun getChildIndex(child: DisplayObject): Number
	open fun setChildIndex(child: DisplayObject, index: Number)
	open fun getChildAt(index: Number): DisplayObject
	
	@Suppress("CANNOT_WEAKEN_ACCESS_PRIVILEGE")
	internal override fun removeChild(child: DisplayObject)
	
	@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
	open fun <T : DisplayObject> removeChild(vararg child: T): T
	open fun removeChildAt(index: Number): DisplayObject
	open fun removeChildren(beginIndex: Number = definedExternally, endIndex: Number = definedExternally): Array<DisplayObject>
	open fun sortChildren()
	override fun updateTransform()
	override fun calculateBounds()
	open fun getLocalBounds(rect: Rectangle = definedExternally, skipChildrenUpdate: Boolean = definedExternally): Rectangle
	protected open fun _calculateBounds()
	override fun render(renderer: Renderer)
	protected open fun renderAdvanced(renderer: Renderer)
	protected open fun _render(_renderer: Renderer)
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
}

abstract external class DisplayObject : EventEmitter {
	abstract var sortDirty: Boolean
	open var parent: Container
	open var worldAlpha: Number
	open var transform: Transform
	open var alpha: Number
	open var visible: Boolean
	open var renderable: Number
	open var filterArea: Rectangle
	open var filters: Array<Filter>?
	open var isSprite: Boolean
	open var isMask: Boolean
	open var _lastSortedIndex: Number
	open var _mask: dynamic /* Container | MaskData */
	open var _bounds: Bounds
	open var _localBounds: Bounds
	protected open var _zIndex: Number
	protected open var _enabledFilters: Array<Filter>
	protected open var _boundsID: Number
	protected open var _boundsRect: Rectangle
	protected open var _lastBoundsRect: Rectangle
	protected open var _destroyed: Boolean
	open var displayObjectUpdateTransform: () -> Unit
	open val destroyed: Boolean
	open val _tempDisplayObjectParent: TemporaryDisplayObject
	
	open var x: Number
	open var y: Number
	open val worldTransform: Transform
	open val localTransform: Matrix
	open var position: ObservablePoint<Any?>
	open var scale: ObservablePoint<Any?>
	open var pivot: ObservablePoint<Any?>
	open var skew: ObservablePoint<Any?>
	open var rotation: Number
	open var angle: Number
	open var zIndex: Number
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
	
	open fun setParent(container: Container): Container
	open fun setTransform(
		x: Number = definedExternally,
		y: Number = definedExternally,
		scaleX: Number = definedExternally,
		scaleY: Number = definedExternally,
		rotation: Number = definedExternally,
		skewX: Number = definedExternally,
		skewY: Number = definedExternally,
		pivotX: Number = definedExternally,
		pivotY: Number = definedExternally
	): DisplayObject /* this */
	
	open fun destroy(_options: IDestroyOptions = definedExternally)
	open fun destroy(_options: Boolean = definedExternally)
	open fun enableTempParent(): Container
	open fun disableTempParent(cacheParent: Container)
	
	companion object {
		fun mixin(source: Dict<Any>)
	}
}

external interface IDestroyOptions {
	val children: Boolean?
	val texture: Boolean?
	val baseTexture: Boolean?
}

@Suppress("RETURN_TYPE_MISMATCH_ON_OVERRIDE")
open external class TemporaryDisplayObject : DisplayObject {
	override fun calculateBounds(): Nothing?
	override fun removeChild(child: DisplayObject): Nothing?
	override fun render(renderer: Renderer): Nothing?
	override var sortDirty: Boolean
}

@file:JsModule("@pixi/mesh")

package pixi.typings.mesh

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import pixi.externals.Color
import pixi.typings.constants.BLEND_MODES
import pixi.typings.constants.DRAW_MODES
import pixi.typings.core.*
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.IPointData
import pixi.typings.math.Matrix
import pixi.typings.utils.Dict

external interface IMeshMaterialOptions {
	var alpha: Double?
	var tint: Color?
	var pluginName: String?
	var program: Program?
	var uniforms: Dict<Any>?
}

open external class Mesh<T : Shader /* = MeshMaterial */>(
	geometry: Geometry,
	shader: T,
	state: State = definedExternally,
	drawMode: DRAW_MODES = definedExternally
) : Container<DisplayObject> {
	open var shader: T
	open var state: State
	open var drawMode: DRAW_MODES
	open var start: Int
	open var size: Int
	open var uvs: Float32Array
	open var indices: Uint16Array
	open var _tintRGB: Color
	open var _texture: Texture<Resource>
	
	open var geometry: Geometry
	open val uvBuffer: Buffer
	open val verticesBuffer: Buffer
	open var material: T
	open var blendMode: BLEND_MODES
	open var roundPixels: Boolean
	open var texture: Texture<Resource>
	
	
	override fun _render(renderer: Renderer)
	protected open fun _renderDefault(renderer: Renderer)
	protected open fun _renderToBatch(renderer: Renderer)
	open fun calculateVertices()
	open fun calculateUvs()
	override fun _calculateBounds()
	open fun containsPoint(point: IPointData): Boolean
	override fun destroy(options: IDestroyOptions)
	override fun destroy(options: Boolean)
	
	companion object {
		var BATCHABLE_SIZE: Short
	}
}

open external class MeshBatchUvs(uvBuffer: Buffer, uvMatrix: Matrix) {
	open val data: Float32Array
	open var uvBuffer: Buffer
	open var uvMatrix: TextureMatrix
	open var _updateID: Int
	
	open fun update(forceUpdate: Boolean = definedExternally)
}

open external class MeshGeometry(vertices: IArrayBuffer = definedExternally, uvs: IArrayBuffer = definedExternally, index: IArrayBuffer = definedExternally) : Geometry {
	open var _updateID: Int
	
	open val vertexDirtyId: Int
}

open external class MeshMaterial(uSampler: Texture<Resource>, options: IMeshMaterialOptions = definedExternally) : Shader {
	open val uvMatrix: TextureMatrix
	open var batchable: Boolean
	open var pluginName: String
	
	open var texture: Texture<Resource>
	open var alpha: Double
	open var tint: Color
	
	open fun update()
}

@file:JsModule("@pixi/mesh")

package typings.mesh

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import typings.constants.BLEND_MODES
import typings.constants.DRAW_MODES
import typings.core.*
import typings.display.Container
import typings.display.IDestroyOptions
import typings.math.IPointData
import typings.math.Matrix
import typings.utils.Dict

external interface IMeshMaterialOptions {
	var alpha: Number?
	var tint: Number?
	var pluginName: String?
	var program: Program?
	var uniforms: Dict<Any>?
}

open external class Mesh<T : Shader /* = MeshMaterial */>(geometry: Geometry, shader: T, state: State, drawMode: DRAW_MODES) : Container {
	constructor(geometry: Geometry, shader: T, state: State)
	constructor(geometry: Geometry, shader: T)

	open val geometry: Geometry
	open var shader: T
	open var state: State
	open var drawMode: DRAW_MODES
	open var start: Number
	open var size: Number
	open var uvs: Float32Array
	open var indices: Uint16Array
	open var _tintRGB: Number
	open var _texture: Texture<Resource>
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
	override fun destroy()

	companion object {
		var BATCHABLE_SIZE: Number
	}
}

open external class MeshBatchUvs(uvBuffer: Buffer, uvMatrix: Matrix) {
	open val data: Float32Array
	open var uvBuffer: Buffer
	open var uvMatrix: TextureMatrix
	open var _updateID: Number

	open fun update(forceUpdate: Boolean)
	open fun update()
}

open external class MeshGeometry(vertices: IArrayBuffer, uvs: IArrayBuffer, index: IArrayBuffer) : Geometry {
	constructor(vertices: IArrayBuffer, uvs: IArrayBuffer)
	constructor(vertices: IArrayBuffer)
	constructor()

	open var _updateID: Number
	open val vertexDirtyId: Number
}

open external class MeshMaterial(uSampler: Texture<Resource>, options: IMeshMaterialOptions) : Shader {
	constructor(uSampler: Texture<Resource>)

	open val uvMatrix: TextureMatrix
	open var batchable: Boolean
	open var pluginNAme: String
	open var texture: Texture<Resource>
	open var alpha: Number
	open var tint: Number

	open fun update()
}
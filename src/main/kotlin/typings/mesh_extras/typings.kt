@file:JsModule("@pixi/mesh-extras")

package typings.mesh_extras

import typings.constants.DRAW_MODES
import typings.core.*
import typings.display.IDestroyOptions
import typings.math.IPoint
import typings.mesh.Mesh
import typings.mesh.MeshGeometry
import typings.mesh.MeshMaterial

open external class NineSlicePlane(texture: Texture<Resource>, leftWidth: Number, topHeight: Number, rightWidth: Number, bottomHeight: Number) : SimplePlane {
	constructor(texture: Texture<Resource>, leftWidth: Number, topHeight: Number, rightWidth: Number)
	constructor(texture: Texture<Resource>, leftWidth: Number, topHeight: Number)
	constructor(texture: Texture<Resource>, leftWidth: Number)
	constructor(texture: Texture<Resource>)

	open var _leftWidth: Number
	open var _rightWidth: Number
	open var _topHeight: Number
	open var _bottomHeight: Number
	open var vertices: ITypedArray
	override var width: Number
	override var height: Number
	open var leftWidth: Number
	open var rightWidth: Number
	open var topHeight: Number
	open var bottomHeight: Number

	override fun textureUpdated()
	open fun updateHorizontalVertices()
	open fun updateVerticalVertices()
}

open external class PlaneGeometry(width: Number, height: Number, segWidth: Number, segHeight: Number) : MeshGeometry {
	constructor(width: Number, height: Number, segWidth: Number)
	constructor(width: Number, height: Number)
	constructor(width: Number)

	open var segWidth: Number
	open var segHeight: Number
	open var width: Number
	open var height: Number

	open fun build()
}

open external class RopeGeometry(width: Number, points: Array<IPoint>, textureScale: Number) : MeshGeometry {
	constructor(width: Number, points: Array<IPoint>)

	open var points: Array<IPoint>
	open val textureScale: Number
	open var _wdith: Number
	open val width: Number

	open fun updateVertices()
	open fun update()
}

open external class SimpleMesh(texture: Texture<Resource>, vertices: IArrayBuffer, uvs: IArrayBuffer, indices: IArrayBuffer, drawModes: DRAW_MODES) : Mesh<MeshMaterial> {
	constructor(texture: Texture<Resource>, vertices: IArrayBuffer, uvs: IArrayBuffer, indices: IArrayBuffer)
	constructor(texture: Texture<Resource>, vertices: IArrayBuffer, uvs: IArrayBuffer)
	constructor(texture: Texture<Resource>, vertices: IArrayBuffer)
	constructor(texture: Texture<Resource>)
	constructor()

	open var autoUpdate: Boolean
	open var vertices: ITypedArray
	override fun _render(renderer: Renderer)
}

open external class SimplePlane(texture: Texture<Resource>, verticesX: Number, verticesY: Number) : Mesh<MeshMaterial> {
	open var autoUpdate: Boolean
	protected open var _textureID: Number
	override var texture: Texture<Resource>

	open fun textureUpdated()
	override fun _render(renderer: Renderer)
	override fun destroy(options: IDestroyOptions)
	override fun destroy(options: Boolean)
	override fun destroy()
}

open external class SimpleRope(texture: Texture<Resource>, points: Array<IPoint>, textureScale: Number) : Mesh<MeshMaterial> {
	constructor(texture: Texture<Resource>, points: Array<IPoint>)

	open var autoUpdate: Boolean

	override fun _render(renderer: Renderer)
}
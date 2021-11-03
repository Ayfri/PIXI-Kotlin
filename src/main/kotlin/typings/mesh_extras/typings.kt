@file:JsModule("@pixi/mesh-extras")

package typings.mesh_extras

import typings.constants.DRAW_MODES
import typings.core.IArrayBuffer
import typings.core.ITypedArray
import typings.core.Renderer
import typings.core.Resource
import typings.core.Texture
import typings.math.IPoint
import typings.mesh.Mesh
import typings.mesh.MeshGeometry
import typings.mesh.MeshMaterial

open external class NineSlicePlane(
	texture: Texture<Resource>,
	leftWidth: Number = definedExternally,
	topHeight: Number = definedExternally,
	rightWidth: Number = definedExternally,
	bottomHeight: Number = definedExternally
) : SimplePlane {
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

open external class PlaneGeometry(
	width: Number,
	height: Number = definedExternally,
	segWidth: Number = definedExternally,
	segHeight: Number = definedExternally
) : MeshGeometry {
	open var segWidth: Number
	open var segHeight: Number
	open var width: Number
	open var height: Number
	
	open fun build()
}

open external class RopeGeometry(width: Number, points: Array<IPoint>, textureScale: Number = definedExternally) : MeshGeometry {
	open var points: Array<IPoint>
	open val textureScale: Number
	open var _wdith: Number
	open val width: Number
	
	open fun updateVertices()
	open fun update()
}

open external class SimpleMesh(
	texture: Texture<Resource> = definedExternally,
	vertices: IArrayBuffer = definedExternally,
	uvs: IArrayBuffer = definedExternally,
	indices: IArrayBuffer = definedExternally,
	drawModes: DRAW_MODES = definedExternally
) : Mesh<MeshMaterial> {
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
}

open external class SimpleRope(texture: Texture<Resource>, points: Array<IPoint>, textureScale: Number = definedExternally) : Mesh<MeshMaterial> {
	open var autoUpdate: Boolean
	
	override fun _render(renderer: Renderer)
}

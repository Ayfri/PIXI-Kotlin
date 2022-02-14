@file:JsModule("@pixi/mesh-extras")

package pixi.typings.mesh_extras

import pixi.typings.constants.DRAW_MODES
import pixi.typings.core.IArrayBuffer
import pixi.typings.core.ITypedArray
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.IPoint
import pixi.typings.mesh.Mesh
import pixi.typings.mesh.MeshGeometry
import pixi.typings.mesh.MeshMaterial

open external class NineSlicePlane(
	texture: Texture<Resource>,
	leftWidth: Double = definedExternally,
	topHeight: Double = definedExternally,
	rightWidth: Double = definedExternally,
	bottomHeight: Double = definedExternally
) : SimplePlane {
	open var _leftWidth: Double
	open var _rightWidth: Double
	open var _topHeight: Double
	open var _bottomHeight: Double
	
	open var vertices: ITypedArray
	override var width: Double
	override var height: Double
	open var leftWidth: Double
	open var rightWidth: Double
	open var topHeight: Double
	open var bottomHeight: Double
	
	override fun textureUpdated()
	open fun updateHorizontalVertices()
	open fun updateVerticalVertices()
}

open external class PlaneGeometry(
	width: Double,
	height: Double = definedExternally,
	segWidth: Double = definedExternally,
	segHeight: Double = definedExternally
) : MeshGeometry {
	open var segWidth: Double
	open var segHeight: Double
	open var width: Double
	open var height: Double
	
	open fun build()
}

open external class RopeGeometry(width: Double, points: Array<IPoint>, textureScale: Double = definedExternally) : MeshGeometry {
	open var points: Array<IPoint>
	open val textureScale: Double
	open var _width: Double
	
	open val width: Double
	
	open fun updateVertices()
	open fun update()
}

open external class SimpleMesh(
	texture: Texture<Resource> = definedExternally,
	vertices: IArrayBuffer = definedExternally,
	uvs: IArrayBuffer = definedExternally,
	indices: IArrayBuffer = definedExternally,
	drawMode: DRAW_MODES = definedExternally
) : Mesh<MeshMaterial> {
	open var autoUpdate: Boolean
	
	open var vertices: ITypedArray
	
	override fun _render(renderer: Renderer)
}

open external class SimplePlane(texture: Texture<Resource>, verticesX: Int, verticesY: Int) : Mesh<MeshMaterial> {
	open var autoUpdate: Boolean
	protected open var _textureID: Int
	
	override var texture: Texture<Resource>
	
	open fun textureUpdated()
	override fun _render(renderer: Renderer)
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
}

open external class SimpleRope(texture: Texture<Resource>, points: Array<IPoint>, textureScale: Double = definedExternally) : Mesh<MeshMaterial> {
	open var autoUpdate: Boolean
	
	override fun _render(renderer: Renderer)
}

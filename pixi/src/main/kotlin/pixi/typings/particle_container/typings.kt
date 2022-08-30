@file:JsModule("@pixi/particle-container")

package pixi.typings.particle_container

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import pixi.externals.Color
import pixi.typings.VarArgFun
import pixi.typings.constants.BLEND_MODES
import pixi.typings.constants.TYPES
import pixi.typings.core.BaseTexture
import pixi.typings.core.Buffer
import pixi.typings.core.Geometry
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.ObjectRenderer
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Shader
import pixi.typings.core.State
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject
import pixi.typings.display.IDestroyOptions
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.math.Matrix
import pixi.typings.sprite.Sprite

external interface IParticleProperties {
	var vertices: Boolean?
	var position: Boolean?
	var rotation: Boolean?
	var uvs: Boolean?
	var tint: Boolean?
	var alpha: Boolean?
	var scale: Boolean?
}

external interface IParticleRendererProperty {
	var attributeName: String
	var size: Int
	var type: TYPES?
	var uploadFunction: VarArgFun<Any?, Any?>
	var offset: Int
}

open external class ParticleBuffer(properties: Array<IParticleRendererProperty>, dynamicPropertyFlags: Array<Boolean>, size: Int) {
	open var geometry: Geometry
	open var staticStride: Int
	open var staticBuffer: Buffer
	open var staticData: Float32Array
	open var staticDataUint32: Uint32Array
	open var dynamicStride: Int
	open var dynamicData: Float32Array
	open var dynamicDataUint32: Uint32Array
	open var _updateID: Int
	open var indexBuffer: Buffer
	
	open fun uploadDynamic(children: Array<Sprite>, startIndex: Int, amount: Int)
	open fun uploadStatic(children: Array<Sprite>, startIndex: Int, amount: Int)
	open fun destroy()
}

open external class ParticleContainer(
	maxSize: Int = definedExternally,
	properties: IParticleProperties = definedExternally,
	batchSize: Int = definedExternally,
	autoResize: Boolean = definedExternally,
) : Container<Sprite> {
	open var blendMode: BLEND_MODES
	open var autoResize: Boolean
	open var roundPixels: Boolean
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var tintRgb: Float32Array
	open var _maxSize: Int
	open var _buffers: Array<ParticleBuffer>
	open var _batchSize: Int
	open var _properties: Array<Boolean>
	open var _bufferUpdateIDs: Array<Int>
	open var _updateID: Int
	
	open var tint: Color
	
	open fun setProperties(properties: IParticleProperties)
	override fun updateTransform()
	override fun render(renderer: Renderer)
	override fun onChildrenChange(smallestChildIndex: Int)
	open fun dispose()
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		var extension: ExtensionMetadata
	}
}

open external class ParticleRenderer(renderer: Renderer) : ObjectRenderer {
	open val state: State
	open var shader: Shader
	open var tempMatrix: Matrix
	open var properties: Array<IParticleRendererProperty>
	
	open fun render(container: ParticleContainer)
	open fun uploadVertices(children: Array<Sprite>, startIndex: Int, amount: Int, array: Array<Double>, stride: Int, offset: Int)
	open fun uploadPosition(children: Array<Sprite>, startIndex: Int, amount: Int, array: Array<Double>, stride: Int, offset: Int)
	open fun uploadRotation(children: Array<Sprite>, startIndex: Int, amount: Int, array: Array<Double>, stride: Int, offset: Int)
	open fun uploadUvs(children: Array<DisplayObject>, startIndex: Int, amount: Int, array: Array<Double>, stride: Int, offset: Int)
	open fun uploadTint(children: Array<DisplayObject>, startIndex: Int, amount: Int, array: Array<Int>, stride: Int, offset: Int)
	override fun destroy()
}

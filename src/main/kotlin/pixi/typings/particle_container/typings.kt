@file:JsModule("@pixi/particle-container")

package pixi.typings.particle_container

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import pixi.typings.Number
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
import pixi.typings.math.Matrix

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
	var size: Number
	var type: TYPES?
	var uploadFunction: VarArgFun<Any?, Any?>
	var offset: Number
}

open external class ParticleBuffer(properties: Array<IParticleRendererProperty>, dynamicPropertyFlags: Array<Boolean>, size: Number) {
	open var geometry: Geometry
	open var staticStride: Number
	open var staticBuffer: Buffer
	open var staticData: Float32Array
	open var staticDataUint32: Uint32Array
	open var dynamicStride: Number
	open var dynamicData: Float32Array
	open var dynamicDataUnit32: Uint32Array
	open var _updateID: Number
	open var indexBuffer: Buffer
	
	open fun uploadDynamic(children: Array<DisplayObject>, startIndex: Number, amount: Number)
	open fun uploadStatic(children: Array<DisplayObject>, startIndex: Number, amount: Number)
	open fun destroy()
}

open external class ParticleContainer(
	maxSize: Number = definedExternally,
	properties: IParticleProperties = definedExternally,
	batchSize: Number = definedExternally,
	autoResize: Number = definedExternally
) : Container {
	open var blendMode: BLEND_MODES
	open var autoResize: Boolean
	open var roundPixelms: Boolean
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var tintRgb: Float32Array
	open var _maxSize: Number
	open var _buffers: Array<ParticleBuffer>
	open var _batchSize: Number
	open var _properties: Array<Boolean>
	open var _bufferUpdateIDs: Array<Number>
	open var _updateID: Number
	
	open var tint: Number
	
	open fun setProperties(properties: IParticleProperties)
	override fun updateTransform()
	override fun render(renderer: Renderer)
	override fun onChildrenChange(smallestChildIndex: Number)
	open fun dispose()
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
}

open external class ParticleRenderer(renderer: Renderer) : ObjectRenderer {
	open val state: State
	open var shader: Shader
	open var tempMatrix: Matrix
	open var properties: Array<IParticleRendererProperty>
	
	open fun render(container: ParticleContainer)
	open fun uploadVertices(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadPosition(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadRotation(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadUvs(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadTint(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	override fun destroy()
}

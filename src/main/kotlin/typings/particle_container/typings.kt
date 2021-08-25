@file:JsModule("@pixi/particle-container")

package typings.particle_container

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint32Array
import typings.VarArgFun
import typings.constants.BLEND_MODES
import typings.constants.TYPES
import typings.core.*
import typings.display.Container
import typings.display.DisplayObject
import typings.display.IDestroyOptions
import typings.math.Matrix

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
	var uploadFunction: VarArgFun<Any, Any>
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
	open var _updateID: Number
	open var indexBuffer: Buffer

	open fun uploadDynamic(children: Array<DisplayObject>, startIndex: Number, amount: Number)
	open fun uploadStatic(children: Array<DisplayObject>, startIndex: Number, amount: Number)
	open fun destroy()
}

open external class ParticleContainer(maxSize: Number, properties: IParticleProperties, batchSize: Number, autoResize: Number) : Container {
	constructor(maxSize: Number, properties: IParticleProperties, batchSize: Number)
	constructor(maxSize: Number, properties: IParticleProperties)

	open val blendMode: BLEND_MODES
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
	override fun destroy()
}

open external class ParticleRenderer(renderer: Renderer) : ObjectRenderer {
	open val state: State
	open var shader: Shader
	open var tempMatrix: Matrix
	open var properties: Array<IParticleRendererProperty>

	open fun render(renderer: Renderer)
	open fun uploadVertices(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadPosition(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadRotation(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadUvs(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	open fun uploadTint(children: Array<DisplayObject>, startIndex: Number, amount: Number, array: Array<Number>, stride: Number, offset: Number)
	override fun destroy()
}
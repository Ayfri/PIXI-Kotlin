@file:Suppress("PropertyName")
@file:JsModule("@pixi/core")

package typings.core

import org.khronos.webgl.*
import org.w3c.dom.ErrorEvent
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.ImageBitmap
import typings.Indexed
import typings.Number
import typings.compressed_textures.WEBGL_compressed_texture_astc
import typings.compressed_textures.WEBGL_compressed_texture_s3tc
import typings.compressed_textures.WEBGL_compressed_texture_s3tc_srgb
import typings.constants.*
import typings.math.IPointData
import typings.math.ISize
import typings.math.Matrix
import typings.math.Point
import typings.math.Rectangle
import typings.runner.Runner
import typings.utils.Dict
import typings.utils.EventEmitter
import kotlin.js.Promise
import kotlin.js.RegExp

open external class AbstractBatchRenderer(renderer: Renderer) : ObjectRenderer {
	open val state: State
	open var size: Number
	open var MAX_TEXTURES: Number
	protected open var shaderGenerator: BatchShaderGenerator
	protected open var geometryClass: BatchGeometry
	protected open var vertexSize: Number
	protected open var _vertexCount: Number
	protected open var _indexCount: Number
	protected open var _bufferedElements: Array<IBatchableElement>
	protected open var _bufferedTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	protected open var _bufferSize: Number
	protected open var _shader: Shader
	protected open var _aBuffers: Array<ViewableBuffer>
	protected open var _iBuffers: Array<Uint16Array>
	protected open var _dcIndex: Number
	protected open var _aIndex: Number
	protected open var _iIndex: Number
	protected open var _attributeBuffer: ViewableBuffer
	protected open var _indexBuffer: Uint16Array
	protected open var _tempBoundTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	
	open fun contextChange()
	open fun initFlushBuffers()
	open fun onPrerender()
	open fun render(element: IBatchableElement)
	open fun buildTexturesAndDrawCalls()
	open fun buildDrawCalls(texArray: BatchTextureArray, start: Number, finish: Number)
	open fun bindAndClearTexArray(texArray: BatchTextureArray)
	open fun updateGeometry()
	open fun drawBatches()
	override fun flush()
	override fun start()
	override fun stop()
	override fun destroy()
	open fun getAttributeBuffer(size: Number): ViewableBuffer
	open fun getIndexBuffer(size: Number): Uint16Array
	open fun packInterleavedGeometry(element: IBatchableElement, attributeBuffer: ViewableBuffer, indexBuffer: Uint16Array, aIndex: Number, iIndex: Number)
	
	companion object {
		var _drawCallPool: Array<BatchDrawCall>
		var _textureArrayPool: Array<BatchTextureArray>
	}
}

open external class AbstractMaskSystem(renderer: Renderer) : ISystem {
	protected open var maskStack: Array<MaskData>
	protected open var glConst: Number
	protected open var renderer: Renderer
	open fun getStackLength(): Number
	open fun setMaskStack(stack: Array<MaskData>)
	protected open fun _useCurrent()
	override fun destroy()
}

abstract external class AbstractMultiResource(length: Number, options: ISize = definedExternally) : Resource {
	open val length: Number
	open var items: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var itemDirtyIds: Array<Number>
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	protected open fun initFromArray(resources: Array<Any?>, options: IAutoDetectOptions = definedExternally)
	override fun dispose()
	abstract fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Number): AbstractMultiResource /* this */
	open fun addResourceAt(resource: Resource, index: Number): AbstractMultiResource /* this */
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun unbind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	override fun load(): Promise<AbstractMultiResource /* this */>
}

abstract external class AbstractRenderer(type: RENDERER_TYPE = definedExternally, options: IRendererOptions = definedExternally) : EventEmitter {
	open var resolution: Number
	open var clearBeforeRender: Boolean?
	open val options: IRendererOptions
	open val type: RENDERER_TYPE
	open val screen: Rectangle
	open val view: HTMLCanvasElement
	open val plugins: IRendererPlugins
	open val useContextAlpha: dynamic /* boolean | 'notMultiplied */
	open val autoDensity: Boolean
	open val preserveDrawingBuffer: Boolean
	protected open var _backgroundColor: Number
	protected open var _backgroundColorString: String
	open var _backgroundColorRgba: Array<Number>
	
	open fun initPlugins(staticMap: IRendererPlugins)
	open val width: Number
	open val height: Number
	open fun resize(desiredScreenWidth: Number, desiredScreenHeight: Number)
	open fun generateTexture(displayObject: IRenderableObject, options: IGenerateTextureOptions = definedExternally): RenderTexture
	open fun generateTexture(
		displayObject: IRenderableObject,
		scaleMode: SCALE_MODES = definedExternally,
		resolution: Number = definedExternally,
		region: Rectangle = definedExternally
	): RenderTexture
	
	abstract fun render(displayObject: IRenderableObject, options: IRendererRenderOptions = definedExternally)
	open fun destroy(removeView: Boolean = definedExternally)
	open var backgroundColor: Number
	open var backgroundAlpha: Number
}

open external class ArrayResource(source: Number, options: ISize = definedExternally) : AbstractMultiResource {
	constructor(source: Array<Any?>, options: ISize = definedExternally)
	
	override fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Number): ArrayResource /* this */
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	override fun upload(renderer: Renderer, texture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
}

open external class Attribute(
	buffer: Number,
	size: Number = definedExternally,
	normalized: Boolean = definedExternally,
	type: TYPES = definedExternally,
	stride: Number = definedExternally,
	start: Number = definedExternally,
	instance: Boolean = definedExternally
) {
	open var buffer: Number
	open var size: Number
	open var normalized: Boolean
	open var type: TYPES
	open var stride: Number
	open var start: Number
	open var instance: Boolean
	open fun destroy()
	
	companion object {
		fun from(
			buffer: Number,
			size: Number = definedExternally,
			normalized: Boolean = definedExternally,
			type: TYPES = definedExternally,
			stride: Number = definedExternally
		): Attribute
	}
}

external fun autoDetectRender(options: IRendererOptionsAuto = definedExternally): AbstractRenderer

external fun <R : Resource, R0> autoDetectResource(source: Any?, options: R0 = definedExternally): R

open external class BaseImageResource(source: ImageSource) : Resource {
	open var source: ImageSource
	open var noSubImage: Boolean
	open fun upload(
		renderer: Renderer,
		baseTexture: BaseTexture<Resource, IAutoDetectOptions>,
		glTexture: GLTexture,
		source: ImageSource = definedExternally
	): Boolean
	
	override fun upload(renderer: Renderer, baseTexture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
	override fun update()
	override fun dispose()
	
	companion object {
		fun crossOrigin(element: HTMLImageElement, url: String, crossOrigin: Boolean = definedExternally)
		fun crossOrigin(element: HTMLImageElement, url: String, crossOrigin: String = definedExternally)
		fun crossOrigin(element: HTMLVideoElement, url: String, crossOrigin: Boolean = definedExternally)
		fun crossOrigin(element: HTMLVideoElement, url: String, crossOrigin: String = definedExternally)
	}
}

open external class BaseRenderTexture(options: IBaseTextureOptions<Any?> = definedExternally) : BaseTexture<Resource, IAutoDetectOptions> {
	open var clearColor: Array<Number>
	open var frameBuffer: Framebuffer
	open var maskStack: Array<MaskData>
	open var filterStack: Array<Any?>
	open fun resize(desiredWidth: Number, desiredHeight: Number)
	override fun dispose()
	override fun destroy()
}

open external class BaseTexture<R : Resource /*= Resource*/, R0 /*= IAutoDetectOptions*/>(
	resource: R = definedExternally,
	options: IBaseTextureOptions<R0> = definedExternally
) : EventEmitter {
	constructor(resource: ImageSource = definedExternally, options: IBaseTextureOptions<R0> = definedExternally)
	constructor(resource: String = definedExternally, options: IBaseTextureOptions<R0> = definedExternally)
	
	open var width: Number
	open var height: Number
	open var resolution: Number
	open var alphaMode: ALPHA_MODES?
	open var anisotropicLevel: Number?
	open var format: FORMATS?
	open var type: TYPES?
	open var target: TARGETS?
	open val uid: Number
	open var touched: Number
	open var isPowerOfTwo: Boolean
	open var _glTextures: Indexed<Number, GLTexture>
	open var dirtyId: Number
	open var dirtyStyleId: Number
	open var cacheId: String
	open var valid: Boolean
	open var textureCacheIds: Array<String>
	open var destroyed: Boolean
	open var resource: R
	open var _batchEnabled: Number
	open var _batchLocation: Number
	open var parentTextureArray: BaseTexture<Resource, IAutoDetectOptions>
	open val realWith: Number
	open val realHeight: Number
	open var mipmap: MIPMAP_MODES
	open var scaleMode: SCALE_MODES
	open var wrapMode: WRAP_MODES
	
	open fun setStyle(scaleMode: SCALE_MODES = definedExternally, mipmap: MIPMAP_MODES = definedExternally): BaseTexture<R, R0> /* this */
	open fun setSize(desiredWidth: Number, desiredHeight: Number, resolution: Number = definedExternally): BaseTexture<R, R0> /* this */
	open fun setRealSize(realWidth: Number, realHeight: Number, resolution: Number = definedExternally): BaseTexture<R, R0> /* this */
	protected open fun _refreshPOT()
	open fun setResolution(resolution: Number): BaseTexture<R, R0> /* this */
	open fun setResource(resource: R): BaseTexture<R, R0> /* this */
	open fun update()
	open fun onError(event: ErrorEvent)
	open fun destroy()
	open fun dispose()
	open fun castToBaseTexture(): BaseTexture<Resource, IAutoDetectOptions>
	
	companion object {
		var _globalBatch: Number
		
		fun <R : Resource, R0 : IAutoDetectOptions> from(
			source: ImageSource,
			options: IBaseTextureOptions<R0> = definedExternally,
			strict: Boolean = definedExternally
		): BaseTexture<R, IAutoDetectOptions>
		
		fun <R : Resource, R0 : IAutoDetectOptions> from(
			source: String,
			options: IBaseTextureOptions<R0> = definedExternally,
			strict: Boolean = definedExternally
		): BaseTexture<R, IAutoDetectOptions>
		
		fun from(
			source: ImageSource,
			options: IBaseTextureOptions<IAutoDetectOptions> = definedExternally,
			strict: Boolean = definedExternally
		): BaseTexture<Resource, IAutoDetectOptions>
		
		fun from(
			source: String,
			options: IBaseTextureOptions<IAutoDetectOptions> = definedExternally,
			strict: Boolean = definedExternally
		): BaseTexture<Resource, IAutoDetectOptions>
		
		fun fromBuffer(
			buffer: Float32Array,
			width: Number,
			height: Number,
			options: IBaseTextureOptions<IAutoDetectOptions> = definedExternally
		): BaseTexture<BufferResource, IAutoDetectOptions>
		
		fun fromBuffer(
			buffer: Uint8Array,
			width: Number,
			height: Number,
			options: IBaseTextureOptions<IAutoDetectOptions> = definedExternally
		): BaseTexture<BufferResource, IAutoDetectOptions>
		
		fun addToCache(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, id: String)
		fun removeFromCache(baseTexture: String): BaseTexture<Resource, IAutoDetectOptions>?
		fun removeFromCache(baseTexture: BaseTexture<Resource, IAutoDetectOptions>): BaseTexture<Resource, IAutoDetectOptions>?
	}
}

open external class BatchDrawCall {
	open var texArray: BatchTextureArray
	open var type: DRAW_MODES
	open var blend: BLEND_MODES
	open var start: Number
	open var size: Number
	open var data: Any?
}

open external class BatchGeometry(_static: Boolean = definedExternally) : Geometry {
	open var _buffer: Buffer
	open var _indexBuffer: Buffer
}

external class BatchPluginFactory {
	companion object {
		val defaultVertexSrc: String
		val defaultFragmentTemplate: String
		fun create(options: IBatchFactoryOptions): AbstractBatchRenderer
	}
}

external val BatchRenderer: AbstractBatchRenderer

open external class BatchShaderGenerator(vertexSrc: String, fragTemplate: String) {
	open val vertexSrc: String
	open val fragTemplate: String
	open val programCache: Indexed<Number, Program>
	open val defaultGroupCache: Indexed<Number, UniformGroup<Dict<Any>>>
	open fun generateShader(maxTextures: Number): Shader
	open fun generateSampleSrc(maxTextures: Number): String
}

open external class BatchSystem(renderer: Renderer) : ISystem {
	open val emptyRenderer: ObjectRenderer
	open var currentRenderer: ObjectRenderer
	open fun setObjectRenderer(objectRenderer: ObjectRenderer)
	open fun flush()
	open fun reset()
	open fun copyBoundTextures(arr: Array<BaseTexture<Resource, IAutoDetectOptions>>, maxTextures: Number)
	open fun boundArray(texArray: BatchTextureArray, boundTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>, batchId: Number, maxTextures: Number)
	
	override fun destroy()
}

open external class BatchTextureArray {
	open var elements: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var ids: Array<Number>
	open var count: Number
	open fun clear()
}

open external class Buffer(data: IArrayBuffer = definedExternally, _static: Boolean = definedExternally, index: Number = definedExternally) {
	open var data: ITypedArray
	open var type: BUFFER_TYPE
	open var static: Boolean
	open var id: Number
	open var disposeRunner: Runner
	open var _glBuffers: Indexed<Number, GLBuffer>
	open var _updateID: Number
	open var index: Boolean
	open fun update(data: IArrayBuffer = definedExternally)
	open fun update(data: Array<Number> = definedExternally)
	open fun update()
	open fun dispose()
	open fun destroy()
	
	companion object {
		fun from(data: IArrayBuffer): Buffer
		fun from(data: Array<Number>): Buffer
	}
}

open external class BufferResource(source: Float32Array, options: ISize) : Resource {
	constructor(source: Uint8Array, options: ISize)
	constructor(source: Uint16Array, options: ISize)
	constructor(source: Uint32Array, options: ISize)
	
	open var data: ArrayBufferView /* Float32Array | Uint8Array | Uint16Array | Uint32Array */
	override fun upload(renderer: Renderer, baseTexture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
	
	override fun dispose()
	
	companion object {
		fun <S : ArrayBufferView /* Float32Array | Uint8Array | Uint16Array | Uint32Array */> test(source: S): Boolean
	}
}

open external class BufferSystem(renderer: Renderer) : ISystem {
	open var CONTEXT_UID: Number
	open var gl: IRenderingContext
	open val managedBuffers: Indexed<Number, Buffer>
	open val boundBufferBases: Indexed<Number, Buffer>
	override fun destroy()
	protected open fun contextChange()
	open fun bind(buffer: Buffer)
	open fun bindBufferBase(buffer: Buffer, index: Number)
	open fun bindBufferRange(buffer: Buffer, index: Number = definedExternally, offset: Number = definedExternally)
	open fun update(buffer: Buffer)
	open fun dispose(buffer: Buffer, contextLost: Boolean = definedExternally)
	open fun disposeAll(contextLost: Boolean = definedExternally)
	protected open fun createGLBuffer(buffer: Buffer): GLBuffer
}

open external class CanvasResource(source: HTMLCanvasElement) : BaseImageResource {
	companion object {
		fun <S : Any /* OffscreenCanvas | HTMLCanvasElement */> test(source: S): Boolean
	}
}

external fun checkMaxIfStatementsInShader(maxIfs: Number, gl: IRenderingContext): Number

open external class ContextSystem(renderer: Renderer) : ISystem {
	open var webGLVersion: Number
	open val supports: ISupportDict
	protected open var CONTEXT_UID: Number
	protected open var gl: IRenderingContext
	open var extensions: WebGLExtensions
	open val isLost: Boolean
	protected open fun contextChange(gl: IRenderingContext)
	open fun initFromContext(gl: IRenderingContext)
	open fun initFromOptions(options: WebGLContextAttributes)
	open fun createContext(canvas: HTMLCanvasElement, options: WebGLContextAttributes): IRenderingContext
	protected open fun getExtensions()
	protected open fun handleContextLost(event: WebGLContextEvent)
	protected open fun handleContextRestored()
	override fun destroy()
	protected open fun postrender()
	protected open fun validateContext(gl: IRenderingContext)
}

external interface UBOElements {
	val uboElements: Array<UBOElement>
	val size: Number
}

external fun createUBOElements(uniformData: Array<IUniformData>): UBOElements

open external class CubeResource(source: Array<String> = definedExternally, options: ICubeResourceOptions = definedExternally) : AbstractMultiResource {
	constructor(source: Array<Resource> = definedExternally, options: ICubeResourceOptions = definedExternally)
	
	override var items: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var linkBaseTexture: Boolean
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Number, linkBaseTexture: Boolean = definedExternally): CubeResource
	override fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Number): CubeResource
	override fun upload(renderer: Renderer, _baseTexture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
	
	companion object {
		var SIDES: Number
		fun <S : Any? /* unknown */> test(source: Array<S>): Boolean /* S is Array<String | Resource> */
	}
}

external val defaultFilterVertex: String
external val defaultVertex: String

open external class Filter(vertexSrc: String = definedExternally, fragmentSrc: String = definedExternally, uniforms: Dict<Any> = definedExternally) : Shader {
	open var padding: Number
	open var multisample: MSAA_QUALITY
	open var enabled: Boolean
	open var autoFit: Boolean
	open var legacy: Boolean
	open var state: State
	protected open var _resolution: Number
	open fun apply(
		filterManager: FilterSystem,
		input: RenderTexture,
		output: RenderTexture,
		clearMode: CLEAR_MODES = definedExternally,
		_currentState: FilterState = definedExternally
	)
	
	open var blendMode: BLEND_MODES
	open val resolution: Number
	
	companion object {
		val defaultVertexSrc: String
		val defaultFragmentSrc: String
		var SOURCE_KEY_MAP: Dict<String>
	}
}

open external class FilterState {
	open var renderTexture: RenderTexture
	open var target: IFilterTarget
	open var legacy: Boolean
	open var resolution: Number
	open var multisample: MSAA_QUALITY
	open var sourceFrame: Rectangle
	open var destinationFrame: Rectangle
	open var bindingSourceFrame: Rectangle
	open var bindingDestinationFrame: Rectangle
	open var filters: Array<Filter>
	open var transform: Matrix
	open fun clear()
}

open external class FilterSystem(renderer: Renderer) : ISystem {
	open val defaultFilterStack: Array<FilterState>
	open var statePool: Array<FilterState>
	open var texturePool: RenderTexturePool
	open var forceClear: Boolean
	open var useMaxPadding: Boolean
	protected open var quad: Quad
	protected open var quadUv: QuadUv
	protected open var activeState: FilterState
	protected open var globalUniforms: UniformGroup<Dict<Any>>
	open var renderer: Renderer
	open fun push(target: IFilterTarget, filters: Array<Filter>)
	open fun pop()
	open fun bindAndClear(filterTexture: RenderTexture, clearMode: CLEAR_MODES = definedExternally)
	open fun applyFilter(filter: Filter, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES = definedExternally)
	open fun calculateSpriteMatrix(outputMatrix: Matrix, sprite: ISpriteMaskTarget): Matrix
	override fun destroy()
	protected open fun getOptimalFilterTexture(
		minWidth: Number,
		minHeight: Number,
		resolution: Number = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun getFilterTexture(
		input: RenderTexture = definedExternally,
		resolution: Number = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun returnFilterTexture(renderTexture: RenderTexture)
	open fun emptyPool()
	open fun resize()
}

open external class Framebuffer(width: Number, height: Number) {
	open var width: Number
	open var height: Number
	open var multisample: MSAA_QUALITY
	open var stencil: Boolean
	open var depth: Boolean
	open var dirtyId: Boolean
	open var dirtyFormat: Number
	open var dirtySize: Number
	open var depthTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var colorTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var glFramebuffers: Indexed<String, GLFramebuffer>
	open var disposeRunner: Runner
	open val colorTexture: BaseTexture<Resource, IAutoDetectOptions>
	
	open fun addColorTexture(index: Number = definedExternally, texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally): Framebuffer /* this */
	open fun addDepthTexture(texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally): Framebuffer /* this */
	open fun enableDepth(): Framebuffer /* this */
	open fun enableStencil(): Framebuffer /* this */
	open fun resize(width: Number, height: Number)
	open fun dispose()
	open fun destroyDepthTexture()
}

open external class FramebufferSystem(renderer: Renderer) : ISystem {
	open val managedFramebuffers: Array<Framebuffer>
	open var current: Framebuffer
	open var viewport: Rectangle
	open var hasMRT: Boolean
	open var writeDepthTexture: Boolean
	protected open var CONTEXT_UID: Number
	protected open var gl: IRenderingContext
	protected open var unknownFramebuffer: Framebuffer
	protected open var msaaSamples: Array<Number>
	open var renderer: Renderer
	
	protected open fun contextChange()
	open fun bind(framebuffer: Framebuffer = definedExternally, frame: Rectangle = definedExternally, mipLevel: Number = definedExternally)
	open fun setViewport(x: Number, y: Number, width: Number, height: Number)
	open val size: Size
	open fun clear(r: Number, g: Number, b: Number, a: Number, mask: BUFFER_BITS = definedExternally)
	open fun initFramebuffer(framebuffer: Framebuffer): GLFramebuffer
	open fun resizeFramebuffer(framebuffer: Framebuffer)
	open fun updateFramebuffer(framebuffer: Framebuffer, mipLevel: Number)
	protected open fun canMultisampleFramebuffer(framebuffer: Framebuffer): Boolean
	protected open fun detectSamples(samples: MSAA_QUALITY): MSAA_QUALITY
	open fun blit(framebuffer: Framebuffer = definedExternally, sourcePixels: Rectangle = definedExternally, destPixels: Rectangle = definedExternally)
	open fun disposeFramebuffer(framebuffer: Framebuffer, contextLost: Boolean = definedExternally)
	open fun disposeAll(contextLost: Boolean = definedExternally)
	open fun forceStencil()
	open fun reset()
	override fun destroy()
	
	interface Size {
		val x: Number
		val y: Number
		val width: Number
		val height: Number
	}
}

external fun generateProgram(gl: IRenderingContext, program: Program): GLProgram

external interface UniformBuffer {
	var size: Number
	var syncFunc: UniformsSyncCallback
}

external fun generateUniformBufferSync(group: UniformGroup<Dict<Any>>, uniformData: Dict<Any>): UniformBuffer

open external class Geometry(buffers: Array<Buffer> = definedExternally, attributes: Indexed<String, Attribute> = definedExternally) {
	open var buffers: Array<Buffer>
	open var indexBuffer: Buffer
	open var attributes: Indexed<String, Attribute>
	open var id: Number
	open var instanced: Boolean
	open var instanceCount: Number
	open var glVertexArrayObjects: Indexed<Number, Indexed<String, WebGLVertexArrayObject>>
	open var disposeRunner: Runner
	open var refCount: Number
	
	open fun addAttribute(
		id: String,
		buffer: Buffer,
		size: Number = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Number = definedExternally,
		start: Number = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Float32Array,
		size: Number = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Number = definedExternally,
		start: Number = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Uint32Array,
		size: Number = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Number = definedExternally,
		start: Number = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Array<Number>,
		size: Number = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Number = definedExternally,
		start: Number = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun getAttribute(id: String): Attribute
	open fun getBuffer(id: String): Buffer
	open fun addIndex(buffer: Buffer = definedExternally): Geometry
	open fun addIndex(buffer: ArrayBuffer = definedExternally): Geometry
	open fun addIndex(buffer: Array<Number> = definedExternally): Geometry
	open fun getIndex(): Buffer
	open fun interleave(): Geometry
	open fun getSize(): Number
	open fun dispose()
	open fun destroy()
	open fun clone(): Geometry
	
	companion object {
		fun merge(geometries: Array<Geometry>): Geometry
	}
}

open external class GeometrySystem(renderer: Renderer) : ISystem {
	open var hasVao: Boolean
	open var hasInstance: Boolean
	open var canUseUInt32ElementIndex: Boolean
	protected open var CONTEXT_UID: Number
	protected open var gl: IRenderingContext
	protected open var _activeGeometry: Geometry
	protected open var _activeVao: WebGLVertexArrayObject
	protected open var _boundBuffer: GLBuffer
	open val managedGeometries: Indexed<Number, Geometry>
	
	protected open fun contextChange()
	open fun bind(geometry: Geometry = definedExternally, shader: Shader = definedExternally)
	open fun reset()
	open fun updateBuffers()
	protected open fun checkCompatibility(geometry: Geometry, program: Program)
	protected open fun getSignature(geometry: Geometry, program: Program): String
	protected open fun initGeometryVao(geometry: Geometry, shader: Shader, incRefCount: Boolean = definedExternally): WebGLVertexArrayObject
	open fun disposeGeometry(geometry: Geometry, contextLost: Boolean = definedExternally)
	open fun disposeAll(contextLost: Boolean = definedExternally)
	protected open fun activateVao(geometry: Geometry, program: Program)
	open fun draw(
		type: DRAW_MODES,
		size: Number = definedExternally,
		start: Number = definedExternally,
		instanceCount: Number = definedExternally
	): GeometrySystem /* this */
	
	protected open fun unbind()
	override fun destroy()
}

external fun getTestContext(): dynamic /* WebGLRenderingContext | WebGL2RenderingContext */

external fun getUBOData(uniforms: Dict<Any>, uniformData: Dict<Any>): Array<Any?>

open external class GLBuffer(buffer: WebGLBuffer = definedExternally) {
	open var buffer: WebGLBuffer
	open var updateID: Number
	open var byteLength: Number
	open var refCount: Number
}

open external class GLFramebuffer(framebuffer: WebGLTexture) {
	open var framebuffer: WebGLFramebuffer
	open var stencil: WebGLRenderbuffer
	open var multisample: MSAA_QUALITY
	open var msaaBuffer: WebGLRenderbuffer
	open var bitFramebuffer: Framebuffer
	open var dirtyId: Number
	open var dirtyFormat: Number
	open var dirtySize: Number
	open var mipLevel: Number
}

open external class GLProgram(program: WebGLProgram, uniformData: Indexed<String, IGLUniformData>) {
	open var program: WebGLProgram
	open var uniformData: Dict<Any>
	open var uniformGroups: Dict<Any>
	open var uniformBufferBindings: Dict<Any>
	open var uniformSync: Dict<Any>
	open var uniformDirtyGroups: Dict<Any>
	open fun destroy()
}

open external class GLTexture(texture: WebGLTexture) {
	open var texture: WebGLTexture
	open var width: Number
	open var height: Number
	open var mipmap: Boolean
	open var wrapMode: Number
	open var type: Number
	open var internalFormat: Number
	open var samplerType: Number
	open var dirtyId: Number
	open var dirtyStyleId: Number
}

external interface IAttributeData {
	var type: String
	var size: Number
	var location: Number
	var name: String
}

external interface IAutoDetectOptions : ISize {
	var filterArea: Rectangle?
	fun getBounds(skipUpdate: Boolean): Rectangle?
	fun getBounds(): Rectangle?
}

external interface IBaseTextureOptions<R /* = Any */> {
	var alphaMode: ALPHA_MODES?
	var mimap: MIPMAP_MODES?
	var anisotropicLevel: Number?
	var scaleMode: SCALE_MODES?
	var width: Number?
	var height: Number?
	var wrapMode: WRAP_MODES?
	var format: FORMATS?
	var type: TYPES?
	var target: TARGETS?
	var resolution: Number?
	var multisample: MSAA_QUALITY?
	var resourceOptions: R?
	var pixiIdPrefix: String?
}

external interface IBatchableElement {
	var _texture: Texture<Resource>
	var vertexData: Float32Array
	var indices: dynamic /* Uint16Array | Uint32Array | Array<Number> */
	var uvs: Float32Array
	var worldAlpha: Number
	var _tintRGB: Number
	var blendMode: BLEND_MODES
}

external interface IBatchFactoryOptions {
	var vertex: String?
	var fragment: String?
	var geometryClass: BatchGeometry?
	var vertexSize: Number?
}

external interface ICubeResourceOptions : ISize {
	var autoLoad: Boolean?
	var linkBaseTexture: Boolean?
}

external interface IFilterTarget {
	var filterArea: Rectangle
	fun getBounds(skipUpdate: Boolean = definedExternally): Rectangle
}

external interface IGenerateTextureOptions {
	var scaleMode: SCALE_MODES?
	var resolution: Number?
	var region: Rectangle?
	var multisample: MSAA_QUALITY?
}

open external class IGLUniformData {
	open var location: WebGLUniformLocation
	open var value: dynamic /* number | boolean | Float32Array | Int32Array | Uint32Array | Array<Boolean> */
}

external interface IImageResourceOptions {
	var autoLoad: Boolean?
	var createBitMap: Boolean?
	var crossorigin: dynamic? /* boolean | string */
	var alphaMode: ALPHA_MODES?
}

open external class ImageBitmapResource(source: ImageBitmap) : BaseImageResource {
	companion object {
		fun <S : Any? /* unknown */> test(source: S): Boolean /* source is ImageBitmap */
	}
}

open external class ImageResource(source: HTMLImageElement, options: IImageResourceOptions = definedExternally) : BaseImageResource {
	constructor(source: String, options: IImageResourceOptions = definedExternally)
	
	open var url: String
	open var preserveBitmap: Boolean
	open var createBitmap: Boolean
	open var alphaMode: ALPHA_MODES
	open var bitmap: ImageBitmap
	open fun load(createBitmap: Boolean = definedExternally): Promise<ImageResource>
	override fun load(): Promise<ImageResource>
	open fun process(): Promise<ImageResource>
	override fun upload(renderer: Renderer, baseTexture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
	override fun dispose()
	
	companion object {
		fun <S : Any? /* unknown */> test(source: S): Boolean /* S is string | HTMLImageElement */
	}
}

external interface IMaskTarget : IFilterTarget {
	var renderable: Boolean
	var isSprite: Boolean?
	var worldTransform: Matrix
	var isFastRect: (() -> Boolean)?
	override fun getBounds(skipUpdate: Boolean): Rectangle
	fun render(renderer: Renderer)
}

external val INSTALLED: Array<IResourcePlugin<Any?, Any?>>

external interface IRenderableContainer : IRenderableObject {
	fun getLocalBounds(rect: Rectangle = definedExternally, skipChildrenUpdate: Boolean = definedExternally): Rectangle
}

external interface IRenderableObject {
	val parent: IRenderableContainer
	fun enableTempParent(): IRenderableContainer
	fun updateTransform()
	fun disableTempParent(parent: IRenderableContainer)
	fun render(renderer: Renderer)
}

external interface IRendererOptions {
	var width: Number?
	var height: Number?
	var view: HTMLCanvasElement?
	var useContextAlpha: dynamic? /* boolean | 'notMultiplied' */
	var transparent: Boolean?
	var autoDensity: Boolean?
	var antiAlias: Boolean?
	var resolution: Number?
	var preserveDrawingBuffer: Boolean?
	var clearBeforeRender: Boolean?
	var backgroundColor: Number?
	var backgroundAlpha: Number?
	var powerPreference: WebGLPowerPreference?
	var context: IRenderingContext?
}

external interface IRendererOptionsAuto : IRendererOptions {
	var forceCanvas: Boolean?
}

external interface IRendererPlugin {
	fun destroy()
}

external interface IRendererPluginConstructor {
	operator fun invoke(renderer: Renderer, options: Any? = definedExternally): IRendererPlugin
}

external interface IRendererRenderOptions {
	var renderTexture: RenderTexture?
	var clear: Boolean?
	var transform: Matrix?
	var skipUpdateTransform: Boolean?
}

external interface IResourcePlugin<R, R0> {
	fun test(source: Any? /* unknown */, extension: String): Boolean
	operator fun invoke(source: Any, options: R0 = definedExternally): R
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ISpriteMaskFilter : Filter {
	var maskSprite: IMaskTarget
}

external interface ISpriteMaskTarget : IMaskTarget {
	var _texture: Texture<Resource>
	var worldAlpha: Number
	var anchor: Point
}


external interface ISupportDict {
	var uint32Indices: Boolean
}

external interface ISVGResourceOptions {
	var source: String?
	var scale: Number?
	var width: Number?
	var height: Number?
	var autoLoad: Boolean?
	var crossorigin: dynamic? /* boolean | string */
}

external interface ISystem {
	fun destroy()
}

external interface ISystemConstructor<R /* = Renderer */> {
	operator fun invoke(renderer: R): ISystem
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface ITypedArray : IArrayBuffer {
	val length: Number
	operator fun get(key: Number): Number
	operator fun set(key: Number, value: Number)
	val BYTES_PER_ELEMENT: Number
}

external interface IUniformData {
	var index: Number
	var type: String
	var size: Number
	var isArray: Boolean
	var value: Any
	var name: String
}

external interface IUniformParser {
	fun test(data: Any?, uniform: Any): Boolean
	fun code(name: String, uniform: Any?): String
	var codeUbo: ((name: String, uniform: Any?) -> String)?
}

external interface IUnloadableTexture {
	var _texture: dynamic /* Texture | RenderTexture */
	var children: Array<IUnloadableTexture>
}

external interface IVideoResourceOptions {
	var autoLoad: Boolean?
	var autoPlay: Boolean?
	var updateFPS: Number?
	var crossorigin: dynamic? /* boolean | string */
}

external interface IVideoResourceOptionsElement {
	var src: String
	var mime: String
}

open external class MaskData(maskObject: IMaskTarget = definedExternally) {
	open var type: MASK_TYPES
	open var autoDetect: Boolean
	open var maskObject: IMaskTarget
	open var pooled: Boolean
	open var isMaskData: Boolean
	open var resolution: Number
	open var multisample: MSAA_QUALITY
	open var enabled: Boolean
	open var _filters: Array<ISpriteMaskFilter>
	open var _stencilCounter: Number
	open var _scissorCounter: Number
	open var _scissorRect: Rectangle
	open var _scissorRectLocal: Rectangle
	open var _target: IMaskTarget
	
	open var filter: ISpriteMaskFilter
	
	open fun reset()
	open fun copyCounterOrReset(maskAbove: MaskData = definedExternally)
}

open external class MaskSystem(renderer: Renderer) : ISystem {
	open var enableScissor: Boolean
	protected open val alphaMaskPool: Array<Array<SpriteMaskFilter>>
	protected open var alphaMaskIndex: Number
	fun setMaskStack(maskStack: Array<MaskData>)
	fun push(target: IMaskTarget, maskDataOrTarget: MaskData)
	fun push(target: IMaskTarget, maskDataOrTarget: IMaskTarget)
	fun pop(target: IMaskTarget)
	fun detect(maskData: MaskData)
	fun pushSpriteMask(maskData: MaskData)
	fun popSpriteMask(maskData: MaskData)
	override fun destroy()
}

open external class ObjectRenderer(renderer: Renderer) : ISystem {
	protected open var renderer: Renderer
	open fun flush()
	override fun destroy()
	open fun start()
	open fun stop()
	fun render(_object: Any)
}

open external class Program(vertexSrc: String = definedExternally, fragmentSrc: String = definedExternally, name: String = definedExternally) {
	open var id: Number
	open var vertexSrc: String
	open var fragmentSrc: String
	open var nameCache: Any
	open var glPrograms: Indexed<Number, GLProgram>
	open var attributeData: Indexed<String, IAttributeData>
	open var uniformData: Indexed<String, IUniformData>
	
	companion object {
		val defaultVertexSrc: String
		val defaultFragmentSrc: String
		fun from(vertexSrc: String = definedExternally, fragmentsSrc: String = definedExternally, name: String = definedExternally): Program
	}
}

open external class ProjectionSystem(renderer: Renderer) : ISystem {
	open var destinationFrame: Rectangle
	open var sourceFrame: Rectangle
	open var defaultFrame: Rectangle
	open var projectionMatrix: Matrix
	open var transform: Matrix
	open fun update(destinationFrame: Rectangle, sourceFrame: Rectangle, resolution: Number, root: Boolean)
	open fun calculateProject(_destinationFrame: Rectangle, sourceFrame: Rectangle, _resolution: Number, root: Boolean)
	open fun setTransform(_matrix: Matrix)
	override fun destroy()
}

open external class Quad : Geometry

open external class QuadUv : Geometry {
	open var vertexBuffer: Buffer
	open var uvBuffer: Buffer
	open var vertices: Float32Array
	open var uvs: Float32Array
	open fun map(targetTextureFrame: Rectangle, destinationFrame: Rectangle): QuadUv /* this */
	open fun invalidate(): QuadUv /* this */
}

open external class Renderer(options: IRendererOptions = definedExternally) : AbstractRenderer {
	open var gl: IRenderingContext
	open var globalUniforms: UniformGroup<Dict<Any>>
	open var CONTEXT_UID: Number
	open var renderingToScreen: Boolean
	open var multisample: MSAA_QUALITY
	open var mask: MaskSystem
	open var context: ContextSystem
	open var state: StateSystem
	open var shader: ShaderSystem
	open var texture: TextureSystem
	open var buffer: BufferSystem
	open var geometry: GeometrySystem
	open var framebuffer: FramebufferSystem
	open var scissor: ScissorsSystem
	open var stencim: StencilSystem
	open var project: ProjectionSystem
	open var textureGc: TextureGCSystem
	open var filter: FilterSystem
	open var renderTexture: RenderTextureSystem
	open var batch: BatchSystem
	open var runners: Indexed<String, Runner>
	open val extract: Any
	
	protected open fun contextChange()
	open fun addSystem(ClassRef: ISystemConstructor<Renderer>, name: String): Renderer /* this */
	override fun render(displayObject: IRenderableObject, options: IRendererRenderOptions)
	open fun render(
		displayObject: IRenderableObject,
		renderTexture: RenderTexture = definedExternally,
		clear: Boolean = definedExternally,
		transform: Matrix = definedExternally,
		skipUpdateTransform: Boolean = definedExternally
	)
	
	open fun generateTexture(
		displayObject: IRenderableObject,
		options: IGenerateTextureOptions = definedExternally,
		resolution: Number = definedExternally,
		region: Rectangle = definedExternally
	): RenderTexture
	
	override fun generateTexture(
		displayObject: IRenderableObject,
		options: SCALE_MODES,
		resolution: Number,
		region: Rectangle
	): RenderTexture
	
	open fun reset(): Renderer /* this */
	open fun clear()
	override fun destroy(removeView: Boolean)
	
	companion object {
		var __plugins: IRendererPlugins
		
		fun create(options: IRendererOptions = definedExternally): AbstractRenderer
		fun registerPlugin(pluginName: String, ctor: IRendererPluginConstructor)
	}
}

open external class RenderTexture(baseRenderTexture: BaseRenderTexture, frame: Rectangle = definedExternally) : Texture<Resource> {
	override var baseTexture: BaseTexture<Resource, IAutoDetectOptions> /* BaseRenderTexture */
	open var filterFrame: Rectangle?
	open var filterPoolKey: dynamic? /* string | number | null */
	open val frameBuffer: Framebuffer
	open var multisample: MSAA_QUALITY
	open fun resize(desiredWidth: Number, desiredHeight: Number, resizeBaseTexture: Boolean = definedExternally)
	open fun setResolution(resolution: Number)
	
	companion object {
		fun create(width: Number, height: Number, scaleMode: SCALE_MODES = definedExternally, resolution: Number = definedExternally): RenderTexture
		fun create(options: IBaseTextureOptions<Any> = definedExternally): RenderTexture
	}
}

open external class RenderTexturePool(textureOptions: IBaseTextureOptions<Any> = definedExternally) {
	open var textureOptions: IBaseTextureOptions<Any>
	open var enableFullScreen: Boolean
	open var texturePool: Indexed<dynamic /* string | number */, Array<RenderTexture>>
	open fun createTexture(realWidth: Number, realHeight: Number, multisample: MSAA_QUALITY = definedExternally): RenderTexture
	open fun getOptimalTexture(
		minWidth: Number,
		minHeight: Number,
		resolution: Number = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun getFilterTexture(input: RenderTexture, resolution: Number = definedExternally, multisample: MSAA_QUALITY = definedExternally): RenderTexture
	open fun returnTexture(renderTexture: RenderTexture)
	open fun returnFilterTexture(renderTexture: RenderTexture)
	open fun clear(destroyTextures: Boolean = definedExternally)
	open fun setScreenSize(size: ISize)
	
	companion object {
		var SCREEN_KEY: Number
	}
}

open external class RenderTextureSystem(renderer: Renderer) : ISystem {
	open var clearColor: Array<Number>
	open var defaultMaskStack: Array<MaskData>
	open var current: RenderTexture?
	open val sourceFrame: Rectangle
	open val destinationFrame: Rectangle
	open val viewportFrame: Rectangle
	open fun bind(renderTexture: RenderTexture = definedExternally, sourceFrame: Rectangle = definedExternally, destinationFrame: Rectangle = definedExternally)
	open fun clear(clearColor: Array<Number> = definedExternally, mask: BUFFER_BITS = definedExternally)
	open fun resize()
	open fun reset()
	override fun destroy()
}

abstract external class Resource(width: Number = definedExternally, height: Number = definedExternally) {
	open var destroyed: Boolean
	open var internal: Boolean
	protected open var _width: Number
	protected open var _height: Number
	protected open var onResize: Runner
	protected open var onUpdate: Runner
	protected open var onError: Runner
	open val valid: Boolean
	open val width: Number
	open val height: Number
	
	open fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun unBind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun resize(width: Number, height: Number)
	open fun update()
	open fun load(): Promise<Resource>
	abstract fun upload(
		renderer: Renderer,
		baseTexture: BaseTexture<Resource, IAutoDetectOptions>,
		glTexture: GLTexture
	): Boolean
	
	open fun style(
		_renderer: Renderer,
		_baseTexture: BaseTexture<Resource, IAutoDetectOptions>,
		_glTexture: GLTexture
	): Boolean
	
	open fun dispose()
	open fun destroy()
	
	companion object {
		fun test(_source: Any? /* unknown */, _extension: String = definedExternally): Boolean
	}
}

@Deprecated("since 6.0.0", ReplaceWith("Loader.resources"))
external val resources: Dict<Any>

open external class ScissorsSystem(renderer: Renderer) : AbstractMaskSystem {
	override fun getStackLength(): Number
	open fun calcScissorRect(maskData: MaskData)
	open fun testScissor(maskData: MaskData)
	open fun push(maskData: MaskData)
	open fun pop()
	override fun _useCurrent()
}

open external class Shader(program: Program, uniforms: Dict<Any> = definedExternally) {
	open var program: Program
	open var uniformGroup: UniformGroup<Dict<Any>>
	open var uniformBindCount: Number
	open val uniforms: Dict<Any>
	
	open fun checkUniformExists(name: String, group: UniformGroup<Dict<Any>>): Boolean
	open fun destroy()
	
	companion object {
		fun from(vertexSrc: String = definedExternally, fragmentsSrc: String = definedExternally, uniforms: Dict<Any> = definedExternally): Shader
	}
}

open external class ShaderSystem(renderer: Renderer) : ISystem {
	protected open var gl: IRenderingContext
	open var shader: Shader
	open var program: Program
	open var id: Number
	open var destroyed: Boolean
	
	open fun systemCheck()
	protected open fun contextChange(gl: IRenderingContext)
	open fun bind(shader: Shader, dontSync: Boolean = definedExternally): GLProgram
	open fun setUniforms(uniforms: Dict<Any>)
	open fun syncUniformGroup(group: UniformGroup<Dict<Any>>, syncData: Any = definedExternally)
	open fun syncUniforms(group: UniformGroup<Dict<Any>>, glProgram: GLProgram, syncData: Any)
	open fun createSyncGroups(group: UniformGroup<Dict<Any>>): UniformsSyncCallback_2
	open fun syncUniformBufferGroup(group: UniformGroup<Dict<Any>>, name: String = definedExternally)
	protected open fun createSyncBufferGroup(group: UniformGroup<Dict<Any>>, glProgram: GLProgram, name: String): UniformsSyncCallback_2
	open fun getGLProgram(): GLProgram
	open fun generateProgram(shader: Shader): GLProgram
	open fun reset()
	override fun destroy()
}

open external class SpriteMaskFilter(sprite: IMaskTarget) : Filter {
	constructor(vertexSrc: String = definedExternally, fragmentSrc: String = definedExternally, uniforms: Dict<Any> = definedExternally)
	
	open var _maskSprite: IMaskTarget
	open var maskSprite: IMaskTarget
	open var maskMatrix: Matrix
	open fun apply(filterManager: FilterSystem, input: RenderTexture, output: RenderTexture, clearMode: CLEAR_MODES)
}

open external class State {
	open var data: Number
	open var _blendMode: BLEND_MODES
	open var _polygonOffset: Number
	open var blend: Boolean
	open var offsets: Boolean
	open var culling: Boolean
	open var depthTest: Boolean
	open var clockwiseFrontFace: Boolean
	open var blendMode: BLEND_MODES
	open var polygonOffset: Number
	
	override fun toString(): String
	
	companion object {
		fun for2d(): State
	}
}

open external class StateSystem : ISystem {
	open var stateId: Number
	open var polygonOffset: Number
	open var blendMode: BLEND_MODES
	protected open var _blendEq: Boolean
	protected open var gl: IRenderingContext
	protected open var blendModes: Array<Array<Number>>
	protected open val map: Array<(value: Boolean) -> Unit>
	protected open val checks: Array<(system: StateSystem /* this */, state: State) -> Unit>
	protected open var defaultState: State
	
	open fun contextChange(gl: IRenderingContext)
	open fun set(state: State)
	open fun forceState(state: State)
	open fun setBlend(value: Boolean)
	open fun setOffset(value: Boolean)
	open fun setDepthTest(value: Boolean)
	open fun setDepthMask(value: Boolean)
	open fun setCullFace(value: Boolean)
	open fun setFrontFace(value: Boolean)
	open fun setBlendMode(value: Number)
	open fun setPolygonOffset(value: Number, scale: Number)
	open fun reset()
	open fun updateCheck(func: (system: StateSystem /* this */, state: State) -> Unit, value: Boolean)
	override fun destroy()
}

open external class StencilSystem(renderer: Renderer) : AbstractMaskSystem {
	override fun getStackLength(): Number
	open fun push(maskData: MaskData)
	open fun pop(maskObject: IMaskTarget)
	override fun _useCurrent()
}

open external class SVGResource(sourceBase64: String, options: ISVGResourceOptions = definedExternally) : BaseImageResource {
	open val svg: String
	open val scale: Number
	open val _overrideWidth: Number
	open val _overrideHeight: Number
	override fun load(): Promise<SVGResource>
	override fun dispose()
	
	companion object {
		val SVG_XML: RegExp
		val SVG_SIZE: RegExp
		
		fun getSize(svgString: String = definedExternally): ISize
		fun test(source: Any? /* unknown */, extension: String = definedExternally): Boolean
	}
}

open external class System(renderer: Renderer) : ISystem {
	open var renderer: Renderer
	override fun destroy()
}

@Deprecated("since 6.0.0")
external val systems: Dict<Any>

open external class Texture<R : Resource /* = Resource */>(
	baseTexture: BaseTexture<R, IAutoDetectOptions>,
	frame: Rectangle = definedExternally,
	orig: Rectangle = definedExternally,
	trim: Rectangle = definedExternally,
	rotate: Number = definedExternally,
	anchor: IPointData = definedExternally
) : EventEmitter {
	open var baseTexture: BaseTexture<R, IAutoDetectOptions>
	open var orig: Rectangle
	open var trim: Rectangle
	open var valid: Boolean
	open var noFrame: Boolean
	open var defaultAnchor: Point
	open var uvMatrix: TextureMatrix
	protected open var _rotate: Number
	open var _updateID: Number
	open var _frame: Rectangle
	open var _uvs: TextureUvs
	open var textureCacheIds: Array<String>
	
	open val resolution: Number
	open var frame: Rectangle
	open var rotate: Number
	open var width: Number
	open var height: Number
	
	open fun update()
	open fun onBaseTextureUpdated(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun destroy(destroyBase: Boolean = definedExternally)
	open fun clone(): Texture<Resource>
	open fun updateUvs()
	open fun castToBaseTexture(): BaseTexture<Resource, IAutoDetectOptions>
	
	companion object {
		val WHITE: Texture<CanvasResource>
		val EMPTY: Texture<CanvasResource>
		
		fun <R : Resource, R0> from(source: String, options: IBaseTextureOptions<R0> = definedExternally, strict: Boolean = definedExternally): Texture<R>
		fun <R : Resource, R0> from(source: ImageSource, options: IBaseTextureOptions<R0> = definedExternally, strict: Boolean = definedExternally): Texture<R>
		fun <R : Resource, R0> from(
			source: BaseTexture<R, IAutoDetectOptions> = definedExternally,
			options: IBaseTextureOptions<R0>,
			strict: Boolean = definedExternally
		): Texture<R>
		
		fun from(source: String, options: IBaseTextureOptions<Any> = definedExternally, strict: Boolean = definedExternally): Texture<Resource>
		fun from(source: ImageSource, options: IBaseTextureOptions<Any> = definedExternally, strict: Boolean = definedExternally): Texture<Resource>
		fun from(
			source: BaseTexture<Resource, IAutoDetectOptions>,
			options: IBaseTextureOptions<Any> = definedExternally,
			strict: Boolean = definedExternally
		): Texture<Resource>
		
		fun <R : Resource, R0> fromUrl(url: String, options: IBaseTextureOptions<R0> = definedExternally): Promise<Texture<R>>
		fun fromUrl(url: String, options: IBaseTextureOptions<Any> = definedExternally): Promise<Texture<Resource>>
		fun fromBuffer(buffer: Float32Array, width: Number, height: Number, options: IBaseTextureOptions<ISize> = definedExternally): Texture<BufferResource>
		fun fromBuffer(buffer: Uint8Array, width: Number, height: Number, options: IBaseTextureOptions<ISize> = definedExternally): Texture<BufferResource>
		fun <R : Resource> fromLoader(
			source: HTMLImageElement,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<R>>
		
		fun <R : Resource> fromLoader(
			source: HTMLCanvasElement,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<R>>
		
		fun <R : Resource> fromLoader(
			source: String,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<R>>
		
		fun fromLoader(
			source: HTMLImageElement,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<Resource>>
		
		fun fromLoader(
			source: HTMLCanvasElement,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<Resource>>
		
		fun fromLoader(
			source: String,
			imageUrl: String,
			name: String = definedExternally,
			options: IBaseTextureOptions<Any> = definedExternally
		): Promise<Texture<Resource>>
		
		fun addToCache(texture: Texture<Resource>, id: String)
		fun removeFromCache(texture: Texture<Resource>): Texture<Resource>?
		fun removeFromCache(texture: String): Texture<Resource>?
	}
}

open external class TextureGCSystem(renderer: Renderer) : ISystem {
	open var count: Number
	open var checkCount: Number
	open var maxIdle: Number
	open var checkCountMax: Number
	open var mode: GC_MODES
	
	protected open fun postrender()
	open fun run()
	open fun unload(displayObject: IUnloadableTexture)
	override fun destroy()
}

open external class TextureMatrix(texture: Texture<Resource>, clampMargin: Number = definedExternally) {
	open var mapCoord: Matrix
	open var clampOffset: Number
	open var clampMargin: Number
	open val uClampFrame: Float32Array
	open val uClampOffset: Float32Array
	open var _textureID: Number
	open var _updateID: Number
	open var _texture: Texture<Resource>
	open var isSimple: Boolean
	open var texture: Texture<Resource>
	
	open fun multiplyUvs(uvs: Float32Array, out: Float32Array = definedExternally): Float32Array
	open fun update(forceUpdate: Boolean = definedExternally): Boolean
}

open external class TextureSystem(renderer: Renderer) : ISystem {
	open var boundTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var managerTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	protected open var hasIntegerTextures: Boolean
	protected open var CONTEXT_UID: Number
	protected open var gl: IRenderingContext
	protected open var internalFormats: Indexed<Number, Indexed<Number, Number>>
	protected open var webGLVersion: Number
	protected open var unknownTexture: BaseTexture<Resource, IAutoDetectOptions>
	protected open var _unknownBoundTextures: Number
	open var currentLocation: Number
	open var emptyTextures: Indexed<Number, GLTexture>
	
	open fun contextChange()
	open fun bind(texture: Texture<Resource>, location: Number = definedExternally)
	open fun bind(texture: BaseTexture<Resource, IAutoDetectOptions>, location: Number = definedExternally)
	open fun reset()
	open fun unbind(texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally)
	open fun ensureSamplerType(maxTextures: Number)
	open fun initTexture(texture: BaseTexture<Resource, IAutoDetectOptions>): GLTexture
	open fun initTextureType(texture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture)
	open fun updateTexture(texture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun destroyTextures(texture: BaseTexture<Resource, IAutoDetectOptions>, skipRemove: Boolean = definedExternally)
	open fun destroyTextures(texture: Texture<Resource>, skipRemove: Boolean = definedExternally)
	open fun updateTextureStyle(texture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun setStyle(texture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture)
	override fun destroy()
}

open external class TextureUvs {
	open var x0: Number
	open var y0: Number
	open var x1: Number
	open var y1: Number
	open var x2: Number
	open var y2: Number
	open var x3: Number
	open var y3: Number
	open var uvsFloat32: Float32Array
	
	open fun set(frame: Rectangle, baseFrame: ISize, rotate: Number)
	override fun toString(): String
}

external interface UBOElement {
	var data: IUniformData
	var offset: Number
	var dataLen: Number
	var dirty: Number
}

open external class UniformGroup<LAYOUT /* = Dict<Any> */>(uniforms: LAYOUT, isStatic: Boolean = definedExternally, isUbo: Boolean = definedExternally) {
	constructor(uniforms: Buffer, isStatic: Boolean = definedExternally, isUbo: Boolean = definedExternally)
	
	open val uniforms: LAYOUT
	open val group: Boolean
	open var id: Number
	open var syncUniforms: Dict<UniformsSyncCallback_2>
	open var dirtyId: Number
	open var static: Boolean
	open var ubo: Boolean
	open var buffer: Buffer?
	open var autoManage: Boolean
	
	open fun update()
	open fun add(name: String, uniforms: Dict<Any>, _static: Boolean = definedExternally)
	
	companion object {
		fun from(uniforms: Dict<Any>, _static: Boolean = definedExternally, _ubo: Boolean = definedExternally): UniformGroup<Dict<Any>>
		fun from(uniforms: Buffer, _static: Boolean = definedExternally, _ubo: Boolean = definedExternally): UniformGroup<Dict<Any>>
		fun uboFrom(uniforms: Dict<Any>, _static: Boolean = definedExternally): UniformGroup<Dict<Any>>
		fun uboFrom(uniforms: Buffer, _static: Boolean = definedExternally): UniformGroup<Dict<Any>>
	}
}

external val uniformParers: Array<IUniformParser>

open external class VideoResource(source: HTMLVideoElement, options: IVideoResourceOptions = definedExternally) : BaseImageResource {
	constructor(source: Array<String>, options: IVideoResourceOptions = definedExternally)
	constructor(source: Array<IVideoResourceOptionsElement>, options: IVideoResourceOptions = definedExternally)
	constructor(source: String, options: IVideoResourceOptions = definedExternally)
	
	override var source: ImageSource /* HTMLVideoElement */
	protected open var _autoUpdate: Boolean
	protected open var _isConnectedToTicker: Boolean
	protected open var _updateFPS: Number
	protected open var _msToNextUpdate: Number
	protected open var autoPlay: Boolean
	open val autoUpdate: Boolean
	open val updateFPS: Number
	
	open fun update(_deltaTime: Number)
	override fun load(): Promise<VideoResource>
	
	companion object {
		val TYPES: Array<String>
		val MIME_TYPES: Dict<String>
		
		fun test(source: Any? /* unknown */, extension: String = definedExternally): Boolean /* source is HTMLVideoElement */
	}
}

open external class ViewableBuffer(length: Number) {
	open var size: Number
	open var rawBinaryData: ArrayBuffer
	open var uint32View: Uint32Array
	open var float32View: Float32Array
	open val int8view: Int8Array
	open val uint8Array: Uint8Array
	open val int16view: Int16Array
	open val uint16Array: Uint16Array
	open val int32Array: Int32Array
	
	open fun view(type: String): ITypedArray
	open fun destroy()
	
	companion object {
		fun sizeOf(type: String): Number
	}
}

external interface WEBGL_compressed_texture_atc {
	var COMPRESSED_RGB_ATC_WEBGL: Number
	var COMPRESSED_RGBA_ATC_EXPLICIT_ALPHA_WEBGL: Number
	var COMPRESSED_RGBA_ATC_INTERPOLATED_ALPHA_WEBGL: Number
}

external interface WEBGL_compressed_texture_etc {
	var COMPRESSED_R11_EAC: Number
	var COMPRESSED_SIGNED_R11_EAC: Number
	var COMPRESSED_RG11_EAC: Number
	var COMPRESSED_SIGNED_RG11_EAC: Number
	var COMPRESSED_RGB8_ETC2: Number
	var COMPRESSED_RGBA8_ETC2_EAC: Number
	var COMPRESSED_SRGB8_ETC2: Number
	var COMPRESSED_SRGB8_ALPHA8_ETC2_EAC: Number
	var COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2: Number
	var COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2: Number
}

external interface WEBGL_compressed_texture_etc1 {
	var COMPRESSED_RGB_ETC1_WEBGL: Number
}

external interface WEBGL_compressed_texture_pvrtc {
	var COMPRESSED_RGB_PVRTC_4BPPV1_IMG: Number
	var COMPRESSED_RGBA_PVRTC_4BPPV1_IMG: Number
	var COMPRESSED_RGB_PVRTC_2BPPV1_IMG: Number
	var COMPRESSED_RGBA_PVRTC_2BPPV1_IMG: Number
}

external interface WebGLExtensions {
	var drawBuffers: WEBGL_draw_buffers?
	var depthTexture: OES_texture_float?
	var loseContext: WEBGL_lose_context?
	var vertexArrayObject: OES_vertex_array_object?
	var anisotropicFiltering: EXT_texture_filter_anisotropic?
	var uint32ElementIndex: OES_element_index_uint?
	var floatTexture: OES_texture_float?
	var floatTextureLinear: OES_texture_float_linear?
	var textureHalfFloat: OES_texture_half_float?
	var textureHalfFloatLinear: OES_texture_half_float_linear?
	var colorBufferFloat: WEBGL_color_buffer_float?
	var s3tc: WEBGL_compressed_texture_s3tc?
	var s3tc_sRGB: WEBGL_compressed_texture_s3tc_srgb?
	var etc: WEBGL_compressed_texture_etc?
	var etc1: WEBGL_compressed_texture_etc1?
	var pvrtc: WEBGL_compressed_texture_pvrtc?
	var atc: WEBGL_compressed_texture_atc?
	var astc: WEBGL_compressed_texture_astc?
}

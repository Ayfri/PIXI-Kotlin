@file:Suppress("PropertyName")
@file:JsModule("@pixi/core")

package pixi.typings.core

import externals.Color
import externals.ColorArr
import externals.WEBGLVersion
import org.khronos.webgl.*
import org.w3c.dom.ErrorEvent
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.ImageBitmap
import pixi.typings.Object
import pixi.typings.compressed_textures.WEBGL_compressed_texture_astc
import pixi.typings.compressed_textures.WEBGL_compressed_texture_s3tc
import pixi.typings.compressed_textures.WEBGL_compressed_texture_s3tc_srgb
import pixi.typings.constants.*
import pixi.typings.math.IPointData
import pixi.typings.math.ISize
import pixi.typings.math.Matrix
import pixi.typings.math.Point
import pixi.typings.math.Rectangle
import pixi.typings.runner.Runner
import pixi.typings.utils.Dict
import pixi.typings.utils.EventEmitter
import kotlin.js.Promise
import kotlin.js.RegExp

open external class AbstractBatchRenderer(renderer: Renderer) : ObjectRenderer {
	open val state: State
	open var size: Int
	open var MAX_TEXTURES: Int
	protected open var shaderGenerator: BatchShaderGenerator
	protected open var geometryClass: BatchGeometry
	protected open var vertexSize: Int
	protected open var _vertexCount: Int
	protected open var _indexCount: Int
	protected open var _bufferedElements: Array<IBatchableElement>
	protected open var _bufferedTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	protected open var _bufferSize: Int
	protected open var _shader: Shader
	protected open var _aBuffers: Array<ViewableBuffer>
	protected open var _iBuffers: Array<Uint16Array>
	protected open var _dcIndex: Int
	protected open var _aIndex: Int
	protected open var _iIndex: Int
	protected open var _attributeBuffer: ViewableBuffer
	protected open var _indexBuffer: Uint16Array
	protected open var _tempBoundTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	
	open fun contextChange()
	open fun initFlushBuffers()
	open fun onPrerender()
	open fun render(element: IBatchableElement)
	open fun buildTexturesAndDrawCalls()
	open fun buildDrawCalls(texArray: BatchTextureArray, start: Int, finish: Int)
	open fun bindAndClearTexArray(texArray: BatchTextureArray)
	open fun updateGeometry()
	open fun drawBatches()
	override fun flush()
	override fun start()
	override fun stop()
	override fun destroy()
	open fun getAttributeBuffer(size: Int): ViewableBuffer
	open fun getIndexBuffer(size: Int): Uint16Array
	open fun packInterleavedGeometry(element: IBatchableElement, attributeBuffer: ViewableBuffer, indexBuffer: Uint16Array, aIndex: Int, iIndex: Int)
	
	companion object {
		var _drawCallPool: Array<BatchDrawCall>
		var _textureArrayPool: Array<BatchTextureArray>
	}
}

open external class AbstractMaskSystem(renderer: Renderer) : ISystem {
	protected open var maskStack: Array<MaskData>
	protected open var glConst: Int
	protected open var renderer: Renderer
	open fun getStackLength(): Int
	open fun setMaskStack(stack: Array<MaskData>)
	protected open fun _useCurrent()
	override fun destroy()
}

abstract external class AbstractMultiResource(length: Int, options: ISize = definedExternally) : Resource {
	open val length: Int
	open var items: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var itemDirtyIds: Array<Int>
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	protected open fun initFromArray(resources: Array<Any?>, options: IAutoDetectOptions = definedExternally)
	override fun dispose()
	abstract fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Int): AbstractMultiResource /* this */
	open fun addResourceAt(resource: Resource, index: Int): AbstractMultiResource /* this */
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun unbind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	override fun load(): Promise<AbstractMultiResource /* this */>
}

abstract external class AbstractRenderer(type: RENDERER_TYPE = definedExternally, options: IRendererOptions = definedExternally) : EventEmitter {
	open var resolution: Double
	open var clearBeforeRender: Boolean?
	open val options: IRendererOptions
	open val type: RENDERER_TYPE
	open val screen: Rectangle
	open val view: HTMLCanvasElement
	open val plugins: IRendererPlugins
	open val useContextAlpha: dynamic /* boolean | 'notMultiplied' */
	open val autoDensity: Boolean
	open val preserveDrawingBuffer: Boolean
	protected open var _backgroundColor: Color
	protected open var _backgroundColorString: String
	open var _backgroundColorRgba: ColorArr
	
	open fun initPlugins(staticMap: IRendererPlugins)
	open val width: Int
	open val height: Int
	open fun resize(desiredScreenWidth: Int, desiredScreenHeight: Int)
	open fun generateTexture(displayObject: IRenderableObject, options: IGenerateTextureOptions = definedExternally): RenderTexture
	open fun generateTexture(
		displayObject: IRenderableObject,
		scaleMode: SCALE_MODES = definedExternally,
		resolution: Double = definedExternally,
		region: Rectangle = definedExternally
	): RenderTexture
	
	abstract fun render(displayObject: IRenderableObject, options: IRendererRenderOptions = definedExternally)
	open fun destroy(removeView: Boolean = definedExternally)
	open var backgroundColor: Color
	open var backgroundAlpha: Double
}

open external class ArrayResource(source: Int, options: ISize = definedExternally) : AbstractMultiResource {
	constructor(source: Array<Any?>, options: ISize = definedExternally)
	
	override fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Int): ArrayResource /* this */
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	override fun upload(renderer: Renderer, texture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
}

open external class Attribute(
	buffer: Int,
	size: Int = definedExternally,
	normalized: Boolean = definedExternally,
	type: TYPES = definedExternally,
	stride: Int = definedExternally,
	start: Int = definedExternally,
	instance: Boolean = definedExternally
) {
	open var buffer: Int
	open var size: Int
	open var normalized: Boolean
	open var type: TYPES
	open var stride: Int
	open var start: Int
	open var instance: Boolean
	open fun destroy()
	
	companion object {
		fun from(
			buffer: Int,
			size: Int = definedExternally,
			normalized: Boolean = definedExternally,
			type: TYPES = definedExternally,
			stride: Int = definedExternally
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
	open var clearColor: ColorArr
	open var frameBuffer: Framebuffer
	open var maskStack: Array<MaskData>
	open var filterStack: Array<Any?>
	open fun resize(desiredWidth: Int, desiredHeight: Int)
	override fun dispose()
	override fun destroy()
}

open external class BaseTexture<R : Resource /*= Resource*/, R0 /*= IAutoDetectOptions*/>(
	resource: R = definedExternally,
	options: IBaseTextureOptions<R0> = definedExternally
) : EventEmitter {
	constructor(resource: ImageSource = definedExternally, options: IBaseTextureOptions<R0> = definedExternally)
	constructor(resource: String = definedExternally, options: IBaseTextureOptions<R0> = definedExternally)
	
	open var width: Double
	open var height: Double
	open var resolution: Double
	open var alphaMode: ALPHA_MODES?
	open var anisotropicLevel: Int?
	open var format: FORMATS?
	open var type: TYPES?
	open var target: TARGETS?
	open val uid: Int
	open var touched: Int
	open var isPowerOfTwo: Boolean
	open var _glTextures: Object<Int, GLTexture>
	open var dirtyId: Int
	open var dirtyStyleId: Int
	open var cacheId: String
	open var valid: Boolean
	open var textureCacheIds: Array<String>
	open var destroyed: Boolean
	open var resource: R
	open var _batchEnabled: Int
	open var _batchLocation: Int
	open var parentTextureArray: BaseTexture<Resource, IAutoDetectOptions>
	open val realWith: Int
	open val realHeight: Int
	open var mipmap: MIPMAP_MODES
	open var scaleMode: SCALE_MODES
	open var wrapMode: WRAP_MODES
	
	open fun setStyle(scaleMode: SCALE_MODES = definedExternally, mipmap: MIPMAP_MODES = definedExternally): BaseTexture<R, R0> /* this */
	open fun setSize(desiredWidth: Double, desiredHeight: Double, resolution: Double = definedExternally): BaseTexture<R, R0> /* this */
	open fun setRealSize(realWidth: Double, realHeight: Double, resolution: Double = definedExternally): BaseTexture<R, R0> /* this */
	protected open fun _refreshPOT()
	open fun setResolution(resolution: Double): BaseTexture<R, R0> /* this */
	open fun setResource(resource: R): BaseTexture<R, R0> /* this */
	open fun update()
	open fun onError(event: ErrorEvent)
	open fun destroy()
	open fun dispose()
	open fun castToBaseTexture(): BaseTexture<Resource, IAutoDetectOptions>
	
	companion object {
		var _globalBatch: Int
		
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
			width: Double,
			height: Double,
			options: IBaseTextureOptions<IAutoDetectOptions> = definedExternally
		): BaseTexture<BufferResource, IAutoDetectOptions>
		
		fun fromBuffer(
			buffer: Uint8Array,
			width: Double,
			height: Double,
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
	open var start: Int
	open var size: Int
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
	open val programCache: Object<Int, Program>
	open val defaultGroupCache: Object<Int, UniformGroup<Dict<Any>>>
	open fun generateShader(maxTextures: Int): Shader
	open fun generateSampleSrc(maxTextures: Int): String
}

open external class BatchSystem(renderer: Renderer) : ISystem {
	open val emptyRenderer: ObjectRenderer
	open var currentRenderer: ObjectRenderer
	open fun setObjectRenderer(objectRenderer: ObjectRenderer)
	open fun flush()
	open fun reset()
	open fun copyBoundTextures(arr: Array<BaseTexture<Resource, IAutoDetectOptions>>, maxTextures: Int)
	open fun boundArray(texArray: BatchTextureArray, boundTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>, batchId: Int, maxTextures: Int)
	
	override fun destroy()
}

open external class BatchTextureArray {
	open var elements: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var ids: Array<Int>
	open var count: Int
	open fun clear()
}

open external class Buffer(data: IArrayBuffer = definedExternally, _static: Boolean = definedExternally, index: Boolean = definedExternally) {
	open var data: ITypedArray
	open var type: BUFFER_TYPE
	open var static: Boolean
	open var id: Int
	open var disposeRunner: Runner
	open var _glBuffers: Object<Int, GLBuffer>
	open var _updateID: Int
	open var index: Boolean
	open fun update(data: IArrayBuffer = definedExternally)
	open fun update(data: Array<Double> = definedExternally)
	open fun update()
	open fun dispose()
	open fun destroy()
	
	companion object {
		fun from(data: IArrayBuffer): Buffer
		fun from(data: Array<Double>): Buffer
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
	open var CONTEXT_UID: Int
	open var gl: IRenderingContext
	open val managedBuffers: Object<Int, Buffer>
	open val boundBufferBases: Object<Int, Buffer>
	override fun destroy()
	protected open fun contextChange()
	open fun bind(buffer: Buffer)
	open fun bindBufferBase(buffer: Buffer, index: Int)
	open fun bindBufferRange(buffer: Buffer, index: Int = definedExternally, offset: Int = definedExternally)
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

external fun checkMaxIfStatementsInShader(maxIfs: Int, gl: IRenderingContext): Int

open external class ContextSystem(renderer: Renderer) : ISystem {
	open var webGLVersion: WEBGLVersion
	open val supports: ISupportDict
	protected open var CONTEXT_UID: Int
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
	val size: Int
}

external fun createUBOElements(uniformData: Array<IUniformData>): UBOElements

open external class CubeResource(source: Array<String> = definedExternally, options: ICubeResourceOptions = definedExternally) : AbstractMultiResource {
	constructor(source: Array<Resource> = definedExternally, options: ICubeResourceOptions = definedExternally)
	
	override var items: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var linkBaseTexture: Boolean
	override fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Int, linkBaseTexture: Boolean = definedExternally): CubeResource
	override fun addBaseTextureAt(baseTexture: BaseTexture<Resource, IAutoDetectOptions>, index: Int): CubeResource
	override fun upload(renderer: Renderer, _baseTexture: BaseTexture<Resource, IAutoDetectOptions>, glTexture: GLTexture): Boolean
	
	companion object {
		var SIDES: Short
		fun <S : Any? /* unknown */> test(source: Array<S>): Boolean /* S is Array<String | Resource> */
	}
}

external val defaultFilterVertex: String
external val defaultVertex: String

open external class Filter(vertexSrc: String = definedExternally, fragmentSrc: String = definedExternally, uniforms: Dict<Any> = definedExternally) : Shader {
	open var padding: Int
	open var multisample: MSAA_QUALITY
	open var enabled: Boolean
	open var autoFit: Boolean
	open var legacy: Boolean
	open var state: State
	protected open var _resolution: Double
	open fun apply(
		filterManager: FilterSystem,
		input: RenderTexture,
		output: RenderTexture,
		clearMode: CLEAR_MODES = definedExternally,
		_currentState: FilterState = definedExternally
	)
	
	open var blendMode: BLEND_MODES
	open val resolution: Double
	
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
	open var resolution: Double
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
		minWidth: Double,
		minHeight: Double,
		resolution: Double = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun getFilterTexture(
		input: RenderTexture = definedExternally,
		resolution: Double = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun returnFilterTexture(renderTexture: RenderTexture)
	open fun emptyPool()
	open fun resize()
}

open external class Framebuffer(width: Int, height: Int) {
	open var width: Int
	open var height: Int
	open var multisample: MSAA_QUALITY
	open var stencil: Boolean
	open var depth: Boolean
	open var dirtyId: Int
	open var dirtyFormat: Int
	open var dirtySize: Int
	open var depthTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var colorTextures: Array<BaseTexture<Resource, IAutoDetectOptions>>
	open var glFramebuffers: Object<String, GLFramebuffer>
	open var disposeRunner: Runner
	open val colorTexture: BaseTexture<Resource, IAutoDetectOptions>
	
	open fun addColorTexture(index: Int = definedExternally, texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally): Framebuffer /* this */
	open fun addDepthTexture(texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally): Framebuffer /* this */
	open fun enableDepth(): Framebuffer /* this */
	open fun enableStencil(): Framebuffer /* this */
	open fun resize(width: Int, height: Int)
	open fun dispose()
	open fun destroyDepthTexture()
}

open external class FramebufferSystem(renderer: Renderer) : ISystem {
	open val managedFramebuffers: Array<Framebuffer>
	open var current: Framebuffer
	open var viewport: Rectangle
	open var hasMRT: Boolean
	open var writeDepthTexture: Boolean
	protected open var CONTEXT_UID: Int
	protected open var gl: IRenderingContext
	protected open var unknownFramebuffer: Framebuffer
	protected open var msaaSamples: Array<Int>
	open var renderer: Renderer
	
	protected open fun contextChange()
	open fun bind(framebuffer: Framebuffer = definedExternally, frame: Rectangle = definedExternally, mipLevel: Int = definedExternally)
	open fun setViewport(x: Int, y: Int, width: Int, height: Int)
	open val size: Size
	open fun clear(r: Double, g: Double, b: Double, a: Double, mask: BUFFER_BITS = definedExternally)
	open fun initFramebuffer(framebuffer: Framebuffer): GLFramebuffer
	open fun resizeFramebuffer(framebuffer: Framebuffer)
	open fun updateFramebuffer(framebuffer: Framebuffer, mipLevel: Int)
	protected open fun canMultisampleFramebuffer(framebuffer: Framebuffer): Boolean
	protected open fun detectSamples(samples: MSAA_QUALITY): MSAA_QUALITY
	open fun blit(framebuffer: Framebuffer = definedExternally, sourcePixels: Rectangle = definedExternally, destPixels: Rectangle = definedExternally)
	open fun disposeFramebuffer(framebuffer: Framebuffer, contextLost: Boolean = definedExternally)
	open fun disposeAll(contextLost: Boolean = definedExternally)
	open fun forceStencil()
	open fun reset()
	override fun destroy()
	
	interface Size {
		val x: Int
		val y: Int
		val width: Int
		val height: Int
	}
}

external fun generateProgram(gl: IRenderingContext, program: Program): GLProgram

external interface UniformBuffer {
	var size: Int
	var syncFunc: UniformsSyncCallback
}

external fun generateUniformBufferSync(group: UniformGroup<Dict<Any>>, uniformData: Dict<Any>): UniformBuffer

open external class Geometry(buffers: Array<Buffer> = definedExternally, attributes: Object<String, Attribute> = definedExternally) {
	open var buffers: Array<Buffer>
	open var indexBuffer: Buffer
	open var attributes: Object<String, Attribute>
	open var id: Int
	open var instanced: Boolean
	open var instanceCount: Int
	open var glVertexArrayObjects: Object<Int, Object<String, WebGLVertexArrayObject>>
	open var disposeRunner: Runner
	open var refCount: Int
	
	open fun addAttribute(
		id: String,
		buffer: Buffer,
		size: Int = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Int = definedExternally,
		start: Int = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Float32Array,
		size: Int = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Int = definedExternally,
		start: Int = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Uint32Array,
		size: Int = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Int = definedExternally,
		start: Int = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun addAttribute(
		id: String,
		buffer: Array<Int>,
		size: Int = definedExternally,
		normalized: Boolean = definedExternally,
		type: TYPES = definedExternally,
		stride: Int = definedExternally,
		start: Int = definedExternally,
		instance: Boolean = definedExternally
	): Geometry
	
	open fun getAttribute(id: String): Attribute
	open fun getBuffer(id: String): Buffer
	open fun addIndex(buffer: Buffer = definedExternally): Geometry
	open fun addIndex(buffer: ArrayBuffer = definedExternally): Geometry
	open fun addIndex(buffer: Array<Int> = definedExternally): Geometry
	open fun getIndex(): Buffer
	open fun interleave(): Geometry
	open fun getSize(): Int
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
	protected open var CONTEXT_UID: Int
	protected open var gl: IRenderingContext
	protected open var _activeGeometry: Geometry
	protected open var _activeVao: WebGLVertexArrayObject
	protected open var _boundBuffer: GLBuffer
	open val managedGeometries: Object<Int, Geometry>
	
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
		size: Int = definedExternally,
		start: Int = definedExternally,
		instanceCount: Int = definedExternally
	): GeometrySystem /* this */
	
	protected open fun unbind()
	override fun destroy()
}

external fun getTestContext(): dynamic /* WebGLRenderingContext | WebGL2RenderingContext */

external fun getUBOData(uniforms: Dict<Any>, uniformData: Dict<Any>): Array<Any?>

open external class GLBuffer(buffer: WebGLBuffer = definedExternally) {
	open var buffer: WebGLBuffer
	open var updateID: Int
	open var byteLength: Int
	open var refCount: Int
}

open external class GLFramebuffer(framebuffer: WebGLTexture) {
	open var framebuffer: WebGLFramebuffer
	open var stencil: WebGLRenderbuffer
	open var multisample: MSAA_QUALITY
	open var msaaBuffer: WebGLRenderbuffer
	open var bitFramebuffer: Framebuffer
	open var dirtyId: Int
	open var dirtyFormat: Int
	open var dirtySize: Int
	open var mipLevel: Int
}

open external class GLProgram(program: WebGLProgram, uniformData: Object<String, IGLUniformData>) {
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
	open var width: Int
	open var height: Int
	open var mipmap: Boolean
	open var wrapMode: Int
	open var type: Int
	open var internalFormat: Int
	open var samplerType: Int
	open var dirtyId: Int
	open var dirtyStyleId: Int
}

external interface IAttributeData {
	var type: String
	var size: Int
	var location: Int
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
	var anisotropicLevel: Int?
	var scaleMode: SCALE_MODES?
	var width: Int?
	var height: Int?
	var wrapMode: WRAP_MODES?
	var format: FORMATS?
	var type: TYPES?
	var target: TARGETS?
	var resolution: Double?
	var multisample: MSAA_QUALITY?
	var resourceOptions: R?
	var pixiIdPrefix: String?
}

external interface IBatchableElement {
	var _texture: Texture<Resource>
	var vertexData: Float32Array
	var indices: dynamic /* Uint16Array | Uint32Array | Array<Int> */
	var uvs: Float32Array
	var worldAlpha: Double
	var _tintRGB: Color
	var blendMode: BLEND_MODES
}

external interface IBatchFactoryOptions {
	var vertex: String?
	var fragment: String?
	var geometryClass: BatchGeometry?
	var vertexSize: Int?
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
	var resolution: Double?
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
	var width: Int?
	var height: Int?
	var view: HTMLCanvasElement?
	var useContextAlpha: dynamic? /* boolean | 'notMultiplied' */
	var transparent: Boolean?
	var autoDensity: Boolean?
	var antiAlias: Boolean?
	var resolution: Double?
	var preserveDrawingBuffer: Boolean?
	var clearBeforeRender: Boolean?
	var backgroundColor: Color?
	var backgroundAlpha: Double?
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
	var worldAlpha: Double
	var anchor: Point
}

external interface ISupportDict {
	var uint32Indices: Boolean
}

external interface ISVGResourceOptions {
	var source: String?
	var scale: Double?
	var width: Double?
	var height: Double?
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
	val length: Int
	operator fun get(key: Int): Int
	operator fun set(key: Int, value: Int)
	val BYTES_PER_ELEMENT: Int
}

external interface IUniformData {
	var index: Int
	var type: String
	var size: Int
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
	var updateFPS: Double?
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
	open var resolution: Double?
	open var multisample: MSAA_QUALITY
	open var enabled: Boolean
	open var _filters: Array<ISpriteMaskFilter>
	open var _stencilCounter: Int
	open var _scissorCounter: Int
	open var _scissorRect: Rectangle?
	open var _scissorRectLocal: Rectangle
	open var _target: IMaskTarget
	
	open var filter: ISpriteMaskFilter
	
	open fun reset()
	open fun copyCounterOrReset(maskAbove: MaskData = definedExternally)
}

open external class MaskSystem(renderer: Renderer) : ISystem {
	open var enableScissor: Boolean
	protected open val alphaMaskPool: Array<Array<SpriteMaskFilter>>
	protected open var alphaMaskIndex: Double
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
	open var id: Int
	open var vertexSrc: String
	open var fragmentSrc: String
	open var nameCache: Any
	open var glPrograms: Object<Int, GLProgram>
	open var attributeData: Object<String, IAttributeData>
	open var uniformData: Object<String, IUniformData>
	
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
	open fun update(destinationFrame: Rectangle, sourceFrame: Rectangle, resolution: Double, root: Boolean)
	open fun calculateProject(_destinationFrame: Rectangle, sourceFrame: Rectangle, _resolution: Double, root: Boolean)
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
	open var CONTEXT_UID: Int
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
	open var runners: Object<String, Runner>
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
		resolution: Double = definedExternally,
		region: Rectangle = definedExternally
	): RenderTexture
	
	override fun generateTexture(
		displayObject: IRenderableObject,
		scaleMode: SCALE_MODES,
		resolution: Double,
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
	open fun resize(desiredWidth: Int, desiredHeight: Int, resizeBaseTexture: Boolean = definedExternally)
	open fun setResolution(resolution: Double)
	
	companion object {
		fun create(width: Int, height: Int, scaleMode: SCALE_MODES = definedExternally, resolution: Double = definedExternally): RenderTexture
		fun create(options: IBaseTextureOptions<Any> = definedExternally): RenderTexture
	}
}

open external class RenderTexturePool(textureOptions: IBaseTextureOptions<Any> = definedExternally) {
	open var textureOptions: IBaseTextureOptions<Any>
	open var enableFullScreen: Boolean
	open var texturePool: Object<dynamic /* string | number */, Array<RenderTexture>>
	open fun createTexture(realWidth: Int, realHeight: Int, multisample: MSAA_QUALITY = definedExternally): RenderTexture
	open fun getOptimalTexture(
		minWidth: Int,
		minHeight: Int,
		resolution: Double = definedExternally,
		multisample: MSAA_QUALITY = definedExternally
	): RenderTexture
	
	open fun getFilterTexture(input: RenderTexture, resolution: Double = definedExternally, multisample: MSAA_QUALITY = definedExternally): RenderTexture
	open fun returnTexture(renderTexture: RenderTexture)
	open fun returnFilterTexture(renderTexture: RenderTexture)
	open fun clear(destroyTextures: Boolean = definedExternally)
	open fun setScreenSize(size: ISize)
	
	companion object {
		var SCREEN_KEY: Short
	}
}

open external class RenderTextureSystem(renderer: Renderer) : ISystem {
	open var clearColor: ColorArr
	open var defaultMaskStack: Array<MaskData>
	open var current: RenderTexture?
	open val sourceFrame: Rectangle
	open val destinationFrame: Rectangle
	open val viewportFrame: Rectangle
	open fun bind(renderTexture: RenderTexture = definedExternally, sourceFrame: Rectangle = definedExternally, destinationFrame: Rectangle = definedExternally)
	open fun clear(clearColor: ColorArr = definedExternally, mask: BUFFER_BITS = definedExternally)
	open fun resize()
	open fun reset()
	override fun destroy()
}

abstract external class Resource(width: Int = definedExternally, height: Int = definedExternally) {
	open var destroyed: Boolean
	open var internal: Boolean
	protected open var _width: Int
	protected open var _height: Int
	protected open var onResize: Runner
	protected open var onUpdate: Runner
	protected open var onError: Runner
	open val valid: Boolean
	open val width: Int
	open val height: Int
	
	open fun bind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun unBind(baseTexture: BaseTexture<Resource, IAutoDetectOptions>)
	open fun resize(width: Int, height: Int)
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
	override fun getStackLength(): Int
	open fun calcScissorRect(maskData: MaskData)
	open fun testScissor(maskData: MaskData)
	open fun push(maskData: MaskData)
	open fun pop()
	override fun _useCurrent()
}

open external class Shader(program: Program, uniforms: Dict<Any> = definedExternally) {
	open var program: Program
	open var uniformGroup: UniformGroup<Dict<Any>>
	open var uniformBindCount: Int
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
	open var id: Int
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
	open var data: Double
	open var _blendMode: BLEND_MODES
	open var _polygonOffset: Double
	open var blend: Boolean
	open var offsets: Boolean
	open var culling: Boolean
	open var depthTest: Boolean
	open var clockwiseFrontFace: Boolean
	open var blendMode: BLEND_MODES
	open var polygonOffset: Double
	
	override fun toString(): String
	
	companion object {
		fun for2d(): State
	}
}

open external class StateSystem : ISystem {
	open var stateId: Double
	open var polygonOffset: Double
	open var blendMode: BLEND_MODES
	protected open var _blendEq: Boolean
	protected open var gl: IRenderingContext
	protected open var blendModes: Array<Array<Int>>
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
	open fun setBlendMode(value: Int)
	open fun setPolygonOffset(value: Double, scale: Double)
	open fun reset()
	open fun updateCheck(func: (system: StateSystem /* this */, state: State) -> Unit, value: Boolean)
	override fun destroy()
}

open external class StencilSystem(renderer: Renderer) : AbstractMaskSystem {
	override fun getStackLength(): Int
	open fun push(maskData: MaskData)
	open fun pop(maskObject: IMaskTarget)
	override fun _useCurrent()
}

open external class SVGResource(sourceBase64: String, options: ISVGResourceOptions = definedExternally) : BaseImageResource {
	open val svg: String
	open val scale: Double
	open val _overrideWidth: Double
	open val _overrideHeight: Double
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
	rotate: Int = definedExternally,
	anchor: IPointData = definedExternally
) : EventEmitter {
	open var baseTexture: BaseTexture<R, IAutoDetectOptions>
	open var orig: Rectangle
	open var trim: Rectangle
	open var valid: Boolean
	open var noFrame: Boolean
	open var defaultAnchor: Point
	open var uvMatrix: TextureMatrix
	protected open var _rotate: Int
	open var _updateID: Double
	open var _frame: Rectangle
	open var _uvs: TextureUvs
	open var textureCacheIds: Array<String>
	
	open val resolution: Double
	open var frame: Rectangle
	open var rotate: Int
	open var width: Double
	open var height: Double
	
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
		fun fromBuffer(buffer: Float32Array, width: Double, height: Double, options: IBaseTextureOptions<ISize> = definedExternally): Texture<BufferResource>
		fun fromBuffer(buffer: Uint8Array, width: Double, height: Double, options: IBaseTextureOptions<ISize> = definedExternally): Texture<BufferResource>
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
	open var count: Int
	open var checkCount: Int
	open var maxIdle: Int
	open var checkCountMax: Int
	open var mode: GC_MODES
	
	protected open fun postrender()
	open fun run()
	open fun unload(displayObject: IUnloadableTexture)
	override fun destroy()
}

open external class TextureMatrix(texture: Texture<Resource>, clampMargin: Double = definedExternally) {
	open var mapCoord: Matrix
	open var clampOffset: Double
	open var clampMargin: Double
	open val uClampFrame: Float32Array
	open val uClampOffset: Float32Array
	open var _textureID: Int
	open var _updateID: Int
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
	protected open var CONTEXT_UID: Int
	protected open var gl: IRenderingContext
	protected open var internalFormats: Object<TYPES, Object<FORMATS, Int>>
	protected open var webGLVersion: WEBGLVersion
	protected open var unknownTexture: BaseTexture<Resource, IAutoDetectOptions>
	protected open var _unknownBoundTextures: Boolean
	open var currentLocation: Int
	open var emptyTextures: Object<Int, GLTexture>
	
	open fun contextChange()
	open fun bind(texture: Texture<Resource>, location: Int = definedExternally)
	open fun bind(texture: BaseTexture<Resource, IAutoDetectOptions>, location: Int = definedExternally)
	open fun reset()
	open fun unbind(texture: BaseTexture<Resource, IAutoDetectOptions> = definedExternally)
	open fun ensureSamplerType(maxTextures: Int)
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
	open var x0: Double
	open var y0: Double
	open var x1: Double
	open var y1: Double
	open var x2: Double
	open var y2: Double
	open var x3: Double
	open var y3: Double
	open var uvsFloat32: Float32Array
	
	open fun set(frame: Rectangle, baseFrame: ISize, rotate: Int)
	override fun toString(): String
}

external interface UBOElement {
	var data: IUniformData
	var offset: Int
	var dataLen: Int
	var dirty: Int
}

open external class UniformGroup<LAYOUT /* = Dict<Any> */>(uniforms: LAYOUT, isStatic: Boolean = definedExternally, isUbo: Boolean = definedExternally) {
	constructor(uniforms: Buffer, isStatic: Boolean = definedExternally, isUbo: Boolean = definedExternally)
	
	open val uniforms: LAYOUT
	open val group: Boolean
	open var id: Int
	open var syncUniforms: Dict<UniformsSyncCallback_2>
	open var dirtyId: Int
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
	protected open var _updateFPS: Double
	protected open var _msToNextUpdate: Int
	protected open var autoPlay: Boolean
	open val autoUpdate: Boolean
	open val updateFPS: Double
	
	open fun update(_deltaTime: Int)
	override fun load(): Promise<VideoResource>
	
	companion object {
		val TYPES: Array<String>
		val MIME_TYPES: Dict<String>
		
		fun test(source: Any? /* unknown */, extension: String = definedExternally): Boolean /* source is HTMLVideoElement */
	}
}

open external class ViewableBuffer(length: Int) {
	open var size: Int
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
		fun sizeOf(type: String): Int
	}
}

external interface WEBGL_compressed_texture_atc {
	var COMPRESSED_RGB_ATC_WEBGL: Int
	var COMPRESSED_RGBA_ATC_EXPLICIT_ALPHA_WEBGL: Int
	var COMPRESSED_RGBA_ATC_INTERPOLATED_ALPHA_WEBGL: Int
}

external interface WEBGL_compressed_texture_etc {
	var COMPRESSED_R11_EAC: Int
	var COMPRESSED_SIGNED_R11_EAC: Int
	var COMPRESSED_RG11_EAC: Int
	var COMPRESSED_SIGNED_RG11_EAC: Int
	var COMPRESSED_RGB8_ETC2: Int
	var COMPRESSED_RGBA8_ETC2_EAC: Int
	var COMPRESSED_SRGB8_ETC2: Int
	var COMPRESSED_SRGB8_ALPHA8_ETC2_EAC: Int
	var COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2: Int
	var COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2: Int
}

external interface WEBGL_compressed_texture_etc1 {
	var COMPRESSED_RGB_ETC1_WEBGL: Int
}

external interface WEBGL_compressed_texture_pvrtc {
	var COMPRESSED_RGB_PVRTC_4BPPV1_IMG: Int
	var COMPRESSED_RGBA_PVRTC_4BPPV1_IMG: Int
	var COMPRESSED_RGB_PVRTC_2BPPV1_IMG: Int
	var COMPRESSED_RGBA_PVRTC_2BPPV1_IMG: Int
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

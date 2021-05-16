@file:Suppress("unused")

package typings

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.Window
import seskar.js.JsInt
import seskar.js.JsUnion
import typings.Loader as Loader_2

@JsModule("pixi.js")
@JsNonModule
external object PIXI {
	open class Application(options: IApplicationOptions = definedExternally) : IResizable {
		open val view: HTMLCanvasElement
		open val stage: Container
		
		open fun resize()
		override var resizeTo: Window?
	}
	
	open class Sprite : Container {
		open val anchor: ObservablePoint<dynamic>
		open var texture: Texture
		open var tint: Color
		
		companion object {
			fun from(source: Texture): Sprite
			fun from(source: String): Sprite
		}
	}
	
	open class Container : DisplayObject {
		val children: Array<DisplayObject>
		fun <T : DisplayObject> addChild(vararg child: T): T
		fun <T : DisplayObject> removeChild(vararg child: T): T
		fun removeChildren(beginIndex: Int = definedExternally, endIndex: Int = definedExternally): Array<DisplayObject>
		fun sortChildren()
	}
	
	abstract class DisplayObject : Point, ISizeable, IDestroyable {
		open val position: Point
		open val parent: DisplayObject
		open var alpha: Number
		open var angle: Number
		open val pivot: ObservablePoint<dynamic>
		open val skew: ObservablePoint<dynamic>
		open val rotation: ObservablePoint<dynamic>
		open val scale: ObservablePoint<dynamic>
		open var zIndex: Number
		open val worldVisible: Boolean
		
		open fun setParent(container: Container): Container
		override fun clone(): DisplayObject
		override var width: Number
		override var height: Number
		override fun destroy(options: IDestroyOptions?)
		override fun destroy()
	}
	
	open class Texture : ISizeable, IClonable<Texture>, IDestroyable {
		override var width: Number
		override var height: Number
		override fun clone(): Texture
		
		companion object {
			val WHITE: Texture
			val EMPTY: Texture
		}
		
		override fun destroy(options: IDestroyOptions?)
		override fun destroy()
	}
	
	open class ObservablePoint<T>(context: (self: T) -> Any, scope: T, x: Number, y: Number) : Point {
		var context: (self: T) -> Any
		var scope: T
		override fun clone(): ObservablePoint<T>
	}
	
	open class Point(x: Number, y: Number) : IClonable<Point>, IPoint {
		override var x: Number
		override var y: Number
		fun copyFrom(p: IPoint): Point
		fun <T : IPoint> copyTo(p: T): T
		fun set(x: Number, y: Number)
		override fun clone(): Point
	}
	
	open class Ticker {
		open var autoStart: Boolean
		open var deltaTime: Number
		open var deltaMS: Number
		open var elapsedMS: Number
		open var lastTime: Number
		open var speed: Number
		open val started: Boolean
		open val count: Number
		open var minFPS: Number
		open var maxFPS: Number
		open val FPS: Number
		
		open fun <T> add(fn: TickerCallback<T>, context: T = definedExternally, priority: UPDATE_PRIORITY = definedExternally): Ticker
		open fun <T> addOnce(fn: TickerCallback<T>, context: T = definedExternally, priority: UPDATE_PRIORITY = definedExternally): Ticker
		open fun <T> remove(fn: TickerCallback<T>, context: T = definedExternally): Ticker
		open fun start()
		open fun stop()
		open fun destroy()
		open fun update(currentTime: Number = definedExternally)
		
		companion object {
			val shared: Ticker
			val system: Ticker
		}
	}
	
	open class Loader(baseUrl: String = definedExternally, concurrency: Number = definedExternally) : Loader_2 {
		open fun pre(resource: Resource, next: (params: Any) -> Unit)
		open fun use(resource: Resource, next: (params: Any) -> Unit)
		
		companion object {
			val shared: Loader
		}
	}
}

@JsUnion
external enum class UPDATE_PRIORITY {
	@JsInt(50)
	INTERACTION,
	
	@JsInt(25)
	HIGH,
	
	@JsInt(0)
	NORMAL,
	
	@JsInt(-25)
	LOW,
	
	@JsInt(-50)
	UTILITY
}

inline val Resource.texture: PIXI.Texture?
	get() = asDynamic().texture as? PIXI.Texture

typealias TickerCallback<T> = (self: T, dt: Number) -> Any
typealias Color = Number

external interface IPoint {
	var x: Number
	var y: Number
}

external interface IClonable<T : IClonable<T>> {
	fun clone(): T
}

external interface ISizeable {
	var width: Number
	var height: Number
}

external interface IDestroyable {
	fun destroy(options: IDestroyOptions?)
	fun destroy()
}

external interface IDestroyOptions {
	val children: Boolean?
	val texture: Boolean?
	val baseTexture: Boolean?
}

external interface IResizable {
	var resizeTo: dynamic
}


external interface IApplicationOptions : IResizable {
	val width: Float?
	val height: Float?
	val view: HTMLCanvasElement?
	val forceCanvas: Boolean?
	val useContextAlpha: Any?
	
	/**
	 * Use `backgroundAlpha` instead.
	 * @deprecated
	 */
	val transparent: Boolean?
	val autoDensity: Boolean?
	val antialias: Boolean?
	val resolution: Float?
	val preserveDrawingBuffer: Boolean?
	val clearBeforeRender: Boolean?
	val backgroundColor: Float?
	val backgroundAlpha: Float?
	val powerPreference: String?
}

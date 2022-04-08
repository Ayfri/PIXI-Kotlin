@file:JsModule("@pixi/prepare")

package pixi.typings.prepare

import pixi.typings.core.AbstractRenderer
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.Container
import pixi.typings.display.DisplayObject

open external class BasePrepare(renderer: AbstractRenderer) {
	protected open var renderer: AbstractRenderer
	protected open var uploadHookHelper: Any?
	protected open var queue: Array<Any?>
	open var addHooks: Array<Any?>
	open var uploadHooks: Array<Any?>
	open var compeltes: Array<Any?>
	open var ticking: Boolean
	
	open fun upload(item: IDisplayObjectExtended, done: () -> Unit = definedExternally)
	open fun upload(item: Container, done: () -> Unit = definedExternally)
	open fun upload(item: BaseTexture<Resource, IAutoDetectOptions>, done: () -> Unit = definedExternally)
	open fun upload(item: Texture<Resource>, done: () -> Unit = definedExternally)
	open fun upload(item: () -> Unit, done: () -> Unit = definedExternally)
	open fun tick()
	open fun prepareItems()
	open fun registerFindHook(addHook: IFindHook): BasePrepare /* this */
	open fun registerUploadHook(uploadHook: IUploadHook): BasePrepare /* this */
	open fun registerUploadHook(uploadHook: IUploadHook2): BasePrepare /* this */
	open fun add(item: IDisplayObjectExtended): BasePrepare /* this */
	open fun add(item: Container): BasePrepare /* this */
	open fun add(item: BaseTexture<Resource, IAutoDetectOptions>): BasePrepare /* this */
	open fun add(item: Texture<Resource>): BasePrepare /* this */
	open fun destroy()
}

open external class CountLimiter(maxItemsPerFrame: Int) {
	open var maxItemsPerFrame: Int
	open var itemsLeft: Int
	
	open fun beginFrame()
	open fun allowedToUpload()
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface IDisplayObjectExtended : DisplayObject {
	var _textures: Array<Texture<Resource>>
	var _texture: Texture<Resource>
	var style: dynamic /* TextStyle | PartialTextStyle */
}

open external class Prepare(renderer: Renderer) : BasePrepare

open external class TimeLimiter(maxMilliseconds: Int) {
	open var maxMilliseconds: Int
	open var frameStart: Int
	
	open fun beginFrame()
	open fun allowedToUpload()
}

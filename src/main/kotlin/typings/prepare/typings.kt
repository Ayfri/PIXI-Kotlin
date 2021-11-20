@file:JsModule("@pixi/prepare")

package typings.prepare

import typings.core.AbstractRenderer
import typings.core.Renderer
import typings.core.Resource
import typings.core.Texture
import typings.display.DisplayObject
import typings.Number

open external class BasePrepare(renderer: AbstractRenderer) {
	protected open var renderer: AbstractRenderer
	protected open var uploadHookHelper: Any
	protected open var queue: Array<Any>
	open var addHooks: Array<Any>
	open var uploadHooks: Array<Any>
	open var compeltes: Array<Any>
	open var ticking: Boolean
	
	open fun upload(item: IDisplayObjectExtended, done: () -> Unit = definedExternally)
	open fun upload(item: IUploadHook, done: () -> Unit = definedExternally)
	open fun upload(item: IFindHook, done: () -> Unit = definedExternally)
	open fun upload(item: () -> Unit, done: () -> Unit = definedExternally)
	open fun tick()
	open fun prepareItems()
	open fun registerFindHook(addHook: IFindHook): BasePrepare /* this */
	open fun registerUploadHook(uploadHook: IUploadHook): BasePrepare /* this */
	open fun add(item: IDisplayObjectExtended): BasePrepare /* this */
	open fun add(item: IUploadHook): BasePrepare /* this */
	open fun add(item: IFindHook): BasePrepare /* this */
	open fun destroy()
}

open external class CountLimiter(maxItemsPerFrame: Number) {
	open var maxItemsPerFrame: Number
	open var itemsLeft: Number
	
	open fun beginFrame()
	open fun allowedToUpload()
}

@Suppress("INTERFACE_WITH_SUPERCLASS")
external interface IDisplayObjectExtended : DisplayObject {
	var _textures: Array<Texture<Resource>>
	var _texture: Texture<Resource>
	var style: dynamic /* TextStyle | PartialTextStyle */
}

external interface IFindHook {
	operator fun invoke(item: Any, queue: Array<Any>): Boolean
}

external interface IUploadHook {
	operator fun invoke(helper: AbstractRenderer, item: IDisplayObjectExtended): Boolean
	operator fun invoke(helper: BasePrepare, item: IDisplayObjectExtended): Boolean
}

open external class Prepare(renderer: Renderer) : BasePrepare

open external class TimeLimiter(maxMilliseconds: Number) {
	open var maxMilliseconds: Number
	open var frameStart: Number
	
	open fun beginFrame()
	open fun allowedToUpload()
}

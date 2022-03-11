@file:Suppress("NOTHING_TO_INLINE")

package pixi.typings.mixin_cache_as_bitmap

import pixi.typings.constants.MSAA_QUALITY
import pixi.typings.core.AbstractRenderer
import pixi.typings.core.Renderer
import pixi.typings.display.DisplayObject
import pixi.typings.display.IDestroyOptions
import pixi.typings.math.Rectangle

inline var DisplayObject.cacheAsBitmap
	get() = asDynamic().cacheAsBitmap.unsafeCast<Boolean>()
	set(value) {
		asDynamic().cacheAsBitmap = value
	}
inline var DisplayObject.cacheAsBitmapResolution
	get() = asDynamic().cacheAsBitmapResolution.unsafeCast<Double>()
	set(value) {
		asDynamic().cacheAsBitmapResolution = value
	}
inline var DisplayObject.cacheAsBitMapMultisample
	get() = asDynamic().cacheAsBitMapMultisample.unsafeCast<MSAA_QUALITY>()
	set(value) {
		asDynamic().cacheAsBitMapMultisample = value
	}
inline var DisplayObject._cacheAsBitmapResolution
	get() = asDynamic()._cacheAsBitmapResolution.unsafeCast<Double>()
	set(value) {
		asDynamic()._cacheAsBitmapResolution = value
	}
inline var DisplayObject._cacheAsBitmapMultisample
	get() = asDynamic()._cacheAsBitmapMultisample.unsafeCast<MSAA_QUALITY>()
	set(value) {
		asDynamic()._cacheAsBitmapMultisample = value
	}
inline var DisplayObject._cacheAsBitmap
	get() = asDynamic()._cacheAsBitmap.unsafeCast<Boolean>()
	set(value) {
		asDynamic()._cacheAsBitmap = value
	}
inline var DisplayObject._cacheData
	get() = asDynamic()._cacheData.unsafeCast<CacheData>()
	set(value) {
		asDynamic()._cacheData = value
	}

inline fun DisplayObject._renderCached(renderer: Renderer) = asDynamic()._renderCached(renderer) as Unit

inline fun DisplayObject._initCachedDisplayObject(renderer: Renderer) = asDynamic()._initCachedDisplayObject(renderer) as Unit

inline fun DisplayObject._calculateCachedBounds() = asDynamic()._calculateCachedBounds() as Unit

inline fun DisplayObject._getCachedLocalBounds() = asDynamic()._getCachedLocalBounds() as Rectangle

inline fun DisplayObject._renderCachedCanvas(renderer: AbstractRenderer) = asDynamic()._renderCachedCanvas(renderer) as Unit

inline fun DisplayObject._initCachedDisplayObjectCanvas(renderer: AbstractRenderer) = asDynamic()._initCachedDisplayObjectCanvas(renderer) as Unit

inline fun DisplayObject._destroyCachedDisplayObject(): Unit = asDynamic()._destroyCachedDisplayObject() as Unit

inline fun DisplayObject._cacheAsBitmapDestroy(options: IDestroyOptions) = asDynamic()._cacheAsBitmapDestroy(options) as Unit

inline fun DisplayObject._cacheAsBitmapDestroy(options: Boolean = false) = asDynamic()._cacheAsBitmapDestroy(options) as Unit

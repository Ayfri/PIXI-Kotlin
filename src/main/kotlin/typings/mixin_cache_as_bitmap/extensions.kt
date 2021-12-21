@file:Suppress("NOTHING_TO_INLINE")

package typings.mixin_cache_as_bitmap

import typings.constants.MSAA_QUALITY
import typings.core.AbstractRenderer
import typings.core.Renderer
import typings.display.DisplayObject
import typings.display.IDestroyOptions
import typings.math.Rectangle
import typings.Number

inline var DisplayObject.cacheAsBitmap
	get() = asDynamic().cacheAsBitmap as Boolean
	set(value) {
		asDynamic().cacheAsBitmap = value
	}
inline var DisplayObject.cacheAsBitmapResolution
	get() = asDynamic().cacheAsBitmapResolution as Number
	set(value) {
		asDynamic().cacheAsBitmapResolution = value
	}
inline var DisplayObject.cacheAsBitMapMultiSample
	get() = asDynamic().cacheAsBitMapMultiSample as MSAA_QUALITY
	set(value) {
		asDynamic().cacheAsBitMapMultiSample = value
	}
inline var DisplayObject._cacheAsBitmapResolution
	get() = asDynamic()._cacheAsBitmapResolution as Number
	set(value) {
		asDynamic()._cacheAsBitmapResolution = value
	}
inline var DisplayObject._cacheAsBitmapMultiSample
	get() = asDynamic()._cacheAsBitmapMultiSample as MSAA_QUALITY
	set(value) {
		asDynamic()._cacheAsBitmapMultiSample = value
	}
inline var DisplayObject._cacheasBitmap
	get() = asDynamic()._cacheasBitmap as Boolean
	set(value) {
		asDynamic()._cacheasBitmap = value
	}
inline var DisplayObject._cacheData
	get() = asDynamic()._cacheData as CacheData
	set(value) {
		asDynamic()._cacheData = value
	}

inline fun DisplayObject._renderCached(renderer: Renderer) = asDynamic()._renderCached(renderer) as Unit

inline fun DisplayObject._initCachedDisplayObject(renderer: Renderer) =
	asDynamic()._initCachedDisplayObject(renderer) as Unit

inline fun DisplayObject._calculateCachedBounds(): Unit = asDynamic()._calculateCachedBounds() as Unit

inline fun DisplayObject._getCachedCanvas(): Rectangle = asDynamic()._getCachedCanvas() as Rectangle

inline fun DisplayObject._renderCachedCanvas(renderer: AbstractRenderer) =
	asDynamic()._renderCachedCanvas(renderer) as Unit

inline fun DisplayObject._initCachedDisplayObjectCanvas(renderer: AbstractRenderer) =
	asDynamic()._initCachedDisplayObjectCanvas(renderer) as Unit

inline fun DisplayObject._destroyCachedDisplayObject(): Unit = asDynamic()._destroyCachedDisplayObject() as Unit

inline fun DisplayObject._cacheAsBitmapDestroy(options: IDestroyOptions) =
	asDynamic()._cacheAsBitmapDestroy(options) as Unit

inline fun DisplayObject._cacheAsBitmapDestroy(options: Boolean) = asDynamic()._cacheAsBitmapDestroy(options) as Unit

inline fun DisplayObject._cacheAsBitmapDestroy(): Unit = asDynamic()._cacheAsBitmapDestroy() as Unit

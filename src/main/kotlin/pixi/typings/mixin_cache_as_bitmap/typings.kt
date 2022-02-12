@file:JsModule("@pixi/mixin-cache-as-bitmap")

package pixi.typings.mixin_cache_as_bitmap

import pixi.typings.core.AbstractRenderer
import pixi.typings.core.Renderer
import pixi.typings.math.IPointData
import pixi.typings.math.Rectangle
import pixi.typings.sprite.Sprite

open external class CacheData {
	open var textureCacheId: String
	open var originalRender: (renderer: Renderer) -> Unit
	open var originalRenderCanvas: (renderer: AbstractRenderer) -> Unit
	open var originalCalculateBounds: () -> Unit
	open var originalGetLocalBounds: (rect: Rectangle?) -> Rectangle
	open var originalUpdateTransform: () -> Unit
	open var originalDestroy: (options: dynamic /* undefined | IDestroyOptions | boolean */) -> Unit
	open var originalMask: dynamic /* Container | MaskData */
	open var originalContainsPoint: (point: IPointData) -> Boolean
	open var sprite: Sprite
}


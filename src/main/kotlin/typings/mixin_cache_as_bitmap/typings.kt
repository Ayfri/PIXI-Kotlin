@file:JsModule("@pixi/mixin-cache-as-bitmap")

package typings.mixin_cache_as_bitmap

import typings.core.AbstractRenderer
import typings.core.Renderer
import typings.math.IPointData
import typings.math.Rectangle
import typings.sprite.Sprite

open external class CacheData {
	open var textureCacheId: String
	open var originalRender: (renderer: Renderer) -> Unit
	open var originalRenderCanvas: (renderer: AbstractRenderer) -> Unit
	open var originalCalculateBounds: () -> Unit
	open var originalGetLocalBounds: (rect: Rectangle?) -> Rectangle
	open var originalUpdateTransform: () -> Unit
	open var originalDestroy: (options: dynamic /* undefined | IDestroyOptions | boolean */ ) -> Unit
	open var originalMask: dynamic /* Container | MaskData */
	open var originalContainsPoint: (point: IPointData) -> Boolean
	open var sprite: Sprite
}


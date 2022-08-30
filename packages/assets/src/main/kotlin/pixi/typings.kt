@file:Suppress("unused")
@file:JsModule("@pixi/assets")

package pixi

import kotlinx.js.Record
import org.w3c.dom.ImageBitmap
import pixi.typings.Object
import pixi.typings.core.IBaseTextureOptions
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.extensions.ExtensionMetadata
import pixi.typings.spritesheet.Spritesheet
import kotlin.js.Promise

external fun addFormats(vararg formats: String): (formats: Array<String>) -> Promise<Array<String>>

external interface AssetInitOptionsTexturePreference {
	var resolution: dynamic? /* undefined | Number | Array<Number> */
	var format: dynamic? /* undefined | String | Array<String> */
}

external interface AssetInitOptionsLoader {
	var parsers: Array<LoaderParser<Any, Any>>?
}

external interface AssetInitOptionsResolver {
	var urlParsers: Array<ResolveURLParser>?
	var preferOrders: Array<PreferOrder>?
}

external interface AssetInitOptions {
	var basePath: String?
	var manifest: dynamic? /* undefined | String | ResolverManifest */
	var texturePreference: AssetInitOptionsTexturePreference?
	var loader: AssetInitOptionsLoader?
	var resolver: AssetInitOptionsResolver?
}

external val Assets: AssetsClass

open external class AssetsClass {
	open var resolver: Resolver
	open var loader: Loader
	open var cache: Cache
	
	open val detections: Array<FormatDetectionParser>
	
	open fun init(options: AssetInitOptions? = definedExternally): Promise<Unit>
	open fun add(keysIn: String, assetsIn: String, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: String, data: Any? = definedExternally)
	open fun add(keysIn: String, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	open fun add(keysIn: String, assetsIn: Array<String>, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: Array<String>, data: Any? = definedExternally)
	
	open fun <T : Any /* = Any */> load(url: String, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	open fun <T : Any /* = Any */> load(url: Array<String>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	open fun <T : Any /* = Any */> load(url: LoadAsset<Any>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	open fun <T : Any /* = Any */> load(url: Array<LoadAsset<Any>>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	open fun load(url: String, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	open fun load(url: Array<String>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	open fun load(url: LoadAsset<Any>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	open fun load(url: Array<LoadAsset<Any>>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	
	open fun addBundle(bundleId: String, assets: ResolverBundleBase)
	open fun loadBundle(bundleIds: String, onProgress: ProgressCallback = definedExternally): Promise<Any>
	open fun loadBundle(bundleIds: Array<String>, onProgress: ProgressCallback = definedExternally): Promise<Any>
	open fun backgroundLoad(urls: String): Promise<Unit>
	open fun backgroundLoad(urls: Array<String>): Promise<Unit>
	open fun backgroundLoadBundle(bundleIds: String): Promise<Unit>
	open fun backgroundLoadBundle(bundleIds: Array<String>): Promise<Unit>
	open fun reset()
	open fun <T : Any /* = Any */> get(keys: String): dynamic /* T | Record<String, T> */
	open fun <T : Any /* = Any */> get(keys: Array<String>): dynamic /* T | Record<String, T> */
	open fun get(keys: String): dynamic /* Any | Record<String, Any> */
	open fun get(keys: Array<String>): dynamic /* Any | Record<String, Any> */
	open fun unload(url: String): Promise<Unit>
	open fun unload(url: Array<String>): Promise<Unit>
	open fun unload(url: LoadAsset<Any>): Promise<Unit>
	open fun unload(url: Array<LoadAsset<Any>>): Promise<Unit>
	open fun unloadBundle(bundleIds: String): Promise<Unit>
	open fun unloadBundle(bundleIds: Array<String>): Promise<Unit>
}

open external class CacheClass {
	open val parsers: Array<CacheParser<Any>>
	
	open fun reset()
	open fun has(key: String): Boolean
	open fun <T : Any /* = Any */> get(key: String): T
	open fun get(key: String): Any
	open fun <T : Any /* = Any */> set(key: String, value: T): T
	open fun set(key: String, value: Any): Any
	open fun remove(key: String): Any
}

external interface CacheParser<T : Any /* = Any */> {
	var extension: ExtensionMetadata?
	var config: Record<String, Any>?
	var test: (asset: T) -> Boolean
	var getCacheableAssets: (keys: Array<String>, asset: T) -> Record<String, Any>
}

external val cacheSpriteSheet: CacheParser<Spritesheet>
external val cacheTextureArray: CacheParser<Array<Texture<Resource>>>

external fun <T> convertToList(input: String, transform: (input: String) -> T = definedExternally): Array<T>
external fun <T> convertToList(input: T, transform: (input: String) -> T = definedExternally): Array<T>
external fun <T> convertToList(input: Array<String>, transform: (input: String) -> T = definedExternally): Array<T>
external fun <T> convertToList(input: Array<T>, transform: (input: String) -> T = definedExternally): Array<T>

external fun createStringVariations(string: String): Array<String>

external val detectAvif: FormatDetectionParser
external val detectBasis: FormatDetectionParser
external val detectCompressedTextures: FormatDetectionParser
external val detectWebp: FormatDetectionParser

external fun dirname(path: String): String

external interface FormatDetectionParser {
	var extension: ExtensionMetadata?
	var test: (asset: Any) -> Promise<Boolean>
	var add: (formats: Array<String>) -> Promise<Array<String>>
	var remove: (formats: Array<String>) -> Promise<Array<String>>
}

external fun getFontFamilyName(url: String): String
external val isSingleItem: (item: Any?) -> Boolean

external interface LoadAsset<T : Any /* = Any */> {
	var src: String
	var data: T?
}

external val loadBasis: LoaderParser<dynamic /* Texture<Resource> | Array<Texture<Resource>> */, IBaseTextureOptions<Any>>
external val loadBitmapFont: LoaderParser<dynamic /* String | BitmapFont */, Any>
external val loadDDS: LoaderParser<Any, Any>

open external class Loader {
	open var promiseCache: Record<String, PromiseAndParser>
	
	open val parsers: Array<LoaderParser<Any, Any>>
	
	open fun reset()
	open fun load(assetsToLoadIn: String, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	open fun load(assetsToLoadIn: Array<String>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	open fun load(assetsToLoadIn: LoadAsset<Any>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	open fun load(assetsToLoadIn: Array<LoadAsset<Any>>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	open fun unload(assetsToUnloadIn: String): Promise<Unit>
	open fun unload(assetsToUnloadIn: Array<String>): Promise<Unit>
	open fun unload(assetsToUnloadIn: LoadAsset<Any>): Promise<Unit>
	open fun unload(assetsToUnloadIn: Array<LoadAsset<Any>>): Promise<Unit>
}

external interface LoaderParserLoad<META_DATA : Any /* = Any */> {
	operator fun <T> invoke(url: String, loadAsset: LoadAsset<META_DATA> = definedExternally, loader: Loader = definedExternally): Promise<T>
}

external interface LoaderParserParse<ASSET: Any, META_DATA : Any> {
	operator fun <T> invoke(asset: ASSET, loadAsset: LoadAsset<META_DATA> = definedExternally, loader: Loader = definedExternally): Promise<T>
}

external interface LoaderParser<ASSET : Any /* = Any */, META_DATA : Any /* = Any */> {
	var extension: ExtensionMetadata?
	var config: Record<String, Any>?
	var test: ((url: String, loadAsset: LoadAsset<META_DATA>?, loader: Loader?) -> Boolean)?
	var load: LoaderParserLoad<META_DATA>?
	var testParse: ((asset: ASSET, loadAsset: LoadAsset<META_DATA>?, loader: Loader?) -> Promise<Boolean>)?
	var parse: LoaderParserParse<ASSET, META_DATA>?
	var unload: ((asset: ASSET, loadAsset: LoadAsset<META_DATA>?, loader: Loader?) -> Promise<Unit>)?
}

external interface LoadFontData {
	var family: String
	var display: String
	var featureSettings: String
	var stretch: String
	var style: String
	var unicodeRange: String
	var variant: String
	var weights: Array<String>
}

external fun loadImageBitmap(url: String): Promise<ImageBitmap>

external val loadJSON: LoaderParser<Any, Any>
external val loadKTX: LoaderParser<dynamic /*Texture<Resource> | Array<Texture<Resource>> */, IBaseTextureOptions<Any>>
external val loadSpriteSheet: LoaderParser<Any, Any>
external val loadSVG: LoaderParser<dynamic /* String | Texture<Resource> */ , IBaseTextureOptions<Any>>
external val loadTextures: LoaderParser<Texture<Resource>, IBaseTextureOptions<Any>>
external val loadTxt: LoaderParser<Any, Any>
external val loadWebFont: LoaderParser<dynamic /* FontFace | Array<FontFace> */, Any>

external interface PreferOrder {
	var priority: Array<String>?
	var params: Object<String, Any>
}

external interface PromiseAndParser {
	var promise: Promise<Any>
	var parser: LoaderParser<Any, Any>
}

external fun removeFormats(vararg format: String): (formats: Array<String>) -> Promise<Array<String>>

external interface ResolveAsset : Record<String, Any> {
	var alias: Array<String>?
	var src: String
}

external val resolveCompressedTextureUrl: ResolveURLParser

open external class Resolver {
	open var basePath: String
	open var rootPath: String
	open val parsers: Array<ResolveURLParser>
	
	open fun prefer(vararg preferOrder: PreferOrder)
	open fun reset()
	open fun addManifest(manifest: ResolverManifest)
	open fun addBundle(bundleId: String, assets: ResolverBundleBase)
	
	open fun add(keysIn: String, assetsIn: String, data: Any? = definedExternally)
	open fun add(keysIn: String, assetsIn: ResolveAsset, data: Any? = definedExternally)
	open fun add(keysIn: String, assetsIn: Array<String>, data: Any? = definedExternally)
	open fun add(keysIn: String, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: String, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: ResolveAsset, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: Array<String>, data: Any? = definedExternally)
	open fun add(keysIn: Array<String>, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	
	open fun resoleBundle(bundleIds: String): Record<String, ResolveAsset>
	open fun resoleBundle(bundleIds: Array<String>): Record<String, Record<String, ResolveAsset>>
	open fun resolveUrl(url: String): String
	open fun resolveUrl(url: Array<String>): Array<String>
	open fun resolve(keys: String): ResolveAsset
	open fun resolve(keys: Array<String>): Record<String, ResolveAsset>
}

external interface ResolverBundleBase

external interface ResolverAssetsArray : ResolverBundleBase {
	var name: Any /* String | Array<String> */
	var srcs: Any /* String | Array<ResolveAsset> */
}

external interface ResolverAssetsObject : ResolverBundleBase, Record<String, Any /* String | ResolveAsset */>

external interface ResolverBundle {
	var name: String
	var assets: ResolverBundleBase
}

external interface ResolverManifest {
	var bundles: Array<ResolverBundle>
}

external val resolveSpriteSheetUrl: ResolveURLParser
external val resolveTextureUrl: ResolveURLParser

external interface ResolveURLParser {
	var extension: ExtensionMetadata?
	var config: Record<String, Any>?
	var test: (url: String) -> Boolean
	var parse: (value: String) -> ResolveAsset
}

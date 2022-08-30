@file:Suppress("unused")
@file:JsModule("@pixi/assets")

package pixi

import kotlinx.js.Record
import org.w3c.dom.ImageBitmap
import pixi.typings.Object
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
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

external class AssetsClass {
	var resolver: Resolver
	var loader: Loader
	var cache: Cache
	
	val detections: Array<FormatDetectionParser>
	
	fun init(options: AssetInitOptions? = definedExternally): Promise<Unit>
	fun add(keysIn: String, assetsIn: String, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: String, data: Any? = definedExternally)
	fun add(keysIn: String, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	fun add(keysIn: String, assetsIn: Array<String>, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: Array<String>, data: Any? = definedExternally)
	
	fun <T : Any /* = Any */> load(url: String, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	fun <T : Any /* = Any */> load(url: Array<String>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	fun <T : Any /* = Any */> load(url: LoadAsset<Any>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	fun <T : Any /* = Any */> load(url: Array<LoadAsset<Any>>, onProgress: ProgressCallback): Promise<dynamic /* T | Record<String, T>> */>
	fun load(url: String, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	fun load(url: Array<String>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	fun load(url: LoadAsset<Any>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	fun load(url: Array<LoadAsset<Any>>, onProgress: ProgressCallback): Promise<dynamic /* Any | Record<String, Any>> */>
	
	fun addBundle(bundleId: String, assets: ResolverBundleBase)
	fun loadBundle(bundleIds: String, onProgress: ProgressCallback = definedExternally): Promise<Any>
	fun loadBundle(bundleIds: Array<String>, onProgress: ProgressCallback = definedExternally): Promise<Any>
	fun backgroundLoad(urls: String): Promise<Unit>
	fun backgroundLoad(urls: Array<String>): Promise<Unit>
	fun backgroundLoadBundle(bundleIds: String): Promise<Unit>
	fun backgroundLoadBundle(bundleIds: Array<String>): Promise<Unit>
	fun reset()
	fun <T : Any /* = Any */> get(keys: String): dynamic /* T | Record<String, T> */
	fun <T : Any /* = Any */> get(keys: Array<String>): dynamic /* T | Record<String, T> */
	fun get(keys: String): dynamic /* Any | Record<String, Any> */
	fun get(keys: Array<String>): dynamic /* Any | Record<String, Any> */
	fun unload(url: String): Promise<Unit>
	fun unload(url: Array<String>): Promise<Unit>
	fun unload(url: LoadAsset<Any>): Promise<Unit>
	fun unload(url: Array<LoadAsset<Any>>): Promise<Unit>
	fun unloadBundle(bundleIds: String): Promise<Unit>
	fun unloadBundle(bundleIds: Array<String>): Promise<Unit>
}

external fun basename(path: String, ext: String): String

external class CacheClass {
	val parsers: Array<CacheParser<Any>>
	
	fun reset()
	fun has(key: String): Boolean
	fun <T : Any /* = Any */> get(key: String): T
	fun get(key: String): Any
	fun <T : Any /* = Any */> set(key: String, value: T): T
	fun set(key: String, value: Any): Any
	fun remove(key: String): Any
}

external interface CacheParser<T : Any /* = Any */> {
	var extension: ExtensionMetadata?
	var config: Record<String, Any>?
	var test: (asset: T) -> Boolean
	var getCacheableAssets: (keys: Array<String>, asset: T) -> Record<String, Any>
}

external val cacheSpriteSheet: CacheParser<Spritesheet>
external val cacheTextureArray: CacheParser<Array<Texture<Resource>>>

external interface ConvertToList<T> {
	fun invoke(value: T): Array<T>
}

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
external fun extname(path: String): String

external interface FormatDetectionParser {
	var extension: ExtensionMetadata?
	var test: (asset: Any) -> Promise<Boolean>
	var add: (formats: Array<String>) -> Promise<Array<String>>
	var remove: (formats: Array<String>) -> Promise<Array<String>>
}

external fun getBaseUrl(url: String): String
external fun getFontFamilyName(url: String): String
external fun isAbsouteUrl(url: String): Boolean
external val isSingleItem: (item: Any?) -> Boolean
external fun join(vararg parts: String): String

external interface LoadAsset<T : Any /* = Any */> {
	var src: String
	var data: T?
}

external val loadBasis: LoaderParser<dynamic /* Texture<Resource> | Array<Texture<Resource>> */, LoadTextureData>
external val loadBitmapFont: LoaderParser<dynamic /* String | BitmapFont */, Any>
external val loadDDS: LoaderParser<Any, Any>

external class Loader {
	var promiseCache: Record<String, PromiseAndParser>
	
	val parsers: Array<LoaderParser<Any, Any>>
	
	fun reset()
	fun load(assetsToLoadIn: String, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	fun load(assetsToLoadIn: Array<String>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	fun load(assetsToLoadIn: LoadAsset<Any>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	fun load(assetsToLoadIn: Array<LoadAsset<Any>>, onProgress: (progress: Number) -> Unit = definedExternally): Promise<dynamic /* Any | Record<String, Any> */>
	fun unload(assetsToUnloadIn: String): Promise<Unit>
	fun unload(assetsToUnloadIn: Array<String>): Promise<Unit>
	fun unload(assetsToUnloadIn: LoadAsset<Any>): Promise<Unit>
	fun unload(assetsToUnloadIn: Array<LoadAsset<Any>>): Promise<Unit>
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
external val loadKTX: LoaderParser<Any /*Texture<Resource> | Array<Texture<Resource>> */, LoadTextureData>
external val loadSpriteSheet: LoaderParser<Any, Any>

external interface LoadTextureData {
	var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
}

external val loadTexture: LoaderParser<Texture<Resource>, LoadTextureData>
external val loadTxt: LoaderParser<Any, Any>
external val loadWebFont: LoaderParser<Any /* FontFace | Array<FontFace> */, Any>

external fun makeAbsoluteURL(url: String, customBaseUrl: String): String

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

external class Resolver {
	var basePath: String
	val parsers: Array<ResolveURLParser>
	
	fun prefer(vararg preferOrder: PreferOrder)
	fun reset()
	fun addManifest(manifest: ResolverManifest)
	fun addBundle(bundleId: String, assets: ResolverBundleBase)
	
	fun add(keysIn: String, assetsIn: String, data: Any? = definedExternally)
	fun add(keysIn: String, assetsIn: ResolveAsset, data: Any? = definedExternally)
	fun add(keysIn: String, assetsIn: Array<String>, data: Any? = definedExternally)
	fun add(keysIn: String, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: String, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: ResolveAsset, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: Array<String>, data: Any? = definedExternally)
	fun add(keysIn: Array<String>, assetsIn: Array<ResolveAsset>, data: Any? = definedExternally)
	
	fun resoleBundle(bundleIds: String): Record<String, ResolveAsset>
	fun resoleBundle(bundleIds: Array<String>): Record<String, Record<String, ResolveAsset>>
	fun resolveUrl(url: String): String
	fun resolveUrl(url: Array<String>): Array<String>
	fun resolve(keys: String): ResolveAsset
	fun resolve(keys: Array<String>): Record<String, ResolveAsset>
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

external fun urlJoin(vararg url: String): String
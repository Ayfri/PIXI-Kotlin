@file:JsModule("@pixi/text-bitmap")

package pixi.typings.text_bitmap

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.w3c.dom.XMLDocument
import pixi.externals.Color
import pixi.typings.VarArgFun
import pixi.typings.core.Renderer
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.display.Container
import pixi.typings.display.IDestroyOptions
import pixi.typings.loaders.Loader
import pixi.typings.loaders.LoaderResource
import pixi.typings.math.ObservablePoint
import pixi.typings.math.Rectangle
import pixi.typings.mesh.Mesh
import pixi.typings.mesh.MeshMaterial
import pixi.typings.text.PartialTextStyle
import pixi.typings.text.TextStyle
import pixi.typings.text.TextStyleAlign
import pixi.typings.utils.Dict

open external class BitMapFont(data: BitMapFontData, textures: Array<Texture<Resource>>, ownsTextures: Boolean = definedExternally) {
	constructor(data: BitMapFontData, textures: Dict<Texture<Resource>>, ownsTextures: Boolean = definedExternally)
	
	open val font: String
	open val size: Double
	open val lineHeight: Double
	open val chars: Dict<IBitmapFontCharacter>
	open val pageTextures: Dict<Texture<Resource>>
	open val distanceFieldRange: Double
	open val distanceFieldType: String
	
	open fun destroy()
	
	companion object {
		val ALPHA: dynamic /* Array<String> | Array<Array<String>> */
		val NUMERIC: Array<Array<String>>
		val ALPHANUMERIC: dynamic /* Array<String> | Array<Array<String>> */
		val ASCII: Array<Array<String>>
		val defaultOptions: IBitmapFontOptions
		val available: Dict<BitMapFont>
		
		fun install(data: String, textures: Texture<Resource>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: XMLDocument, textures: Texture<Resource>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: BitMapFontData, textures: Texture<Resource>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: String, textures: Array<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: XMLDocument, textures: Array<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: BitMapFontData, textures: Array<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: String, textures: Dict<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: XMLDocument, textures: Dict<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun install(data: BitMapFontData, textures: Dict<Texture<Resource>>, ownsTextures: Boolean = definedExternally): BitMapFont
		fun uninstall(name: String)
		fun from(name: String, textStyle: TextStyle = definedExternally, options: IBitmapFontOptions = definedExternally): BitMapFont
		fun from(name: String, textStyle: PartialTextStyle = definedExternally, options: IBitmapFontOptions = definedExternally): BitMapFont
	}
}

open external class BitMapFontData {
	open var info: Array<IBitmapFontDataInfo>
	open var common: Array<IBitmapFontDataCommon>
	open var page: Array<IBitmapFontDataPage>
	open var char: Array<IBitmapFontDataChar>
	open var kerning: Array<IBitmapFontDataKerning>
	open var distanceField: IBitmapFontDataDistanceField
}

external class BitmapFontLoader {
	companion object {
		fun add()
		fun use(`this`: Loader, resource: LoaderResource, next: VarArgFun<Any?, Unit>)
	}
}

open external class BitmapText(text: String, style: PartialIBitmapTextStyle = definedExternally) : Container {
	open var dirty: Boolean
	protected open var _textWidth: Double
	protected open var _textHeight: Double
	protected open var _text: String
	protected open var _maxWidth: Double
	protected open var _maxLineHeight: Double
	protected open var _letterSpacing: Double
	protected open var _anchor: ObservablePoint<Any?>
	protected open var _fontName: String
	protected open var _fontSize: Double
	protected open var _align: TextStyleAlign
	protected open var _activePageMeshData: Array<PageMeshData>
	protected open var _tint: Color
	protected open var _roundPixels: Boolean
	
	open var tint: Color
	open var align: TextStyleAlign
	open var fontName: String
	open var fontSize: Double
	open var anchor: ObservablePoint<Any?>
	open var text: String
	open var maxWidth: Double
	open val maxLineHeight: Double
	open val textWidth: Double
	open var letterSpacing: Double
	open var roundPixels: Boolean
	open val textHeight: Double
	
	open fun updateText()
	override fun updateTransform()
	override fun _render(renderer: Renderer)
	fun getLocalBounds(): Rectangle
	protected open fun validate()
	override fun destroy(options: Boolean)
	override fun destroy(options: IDestroyOptions)
	
	companion object {
		var styleDefaults: PartialIBitmapTextStyle
	}
}

external interface IBitmapFontCharacter {
	var xOffset: Double
	var yOffset: Double
	var xAdvance: Double
	var texture: Texture<Resource>
	var page: Int
	var kerning: Dict<Double>
}

external interface IBitmapFontDataChar {
	var id: Int
	var page: Int
	var x: Double
	var y: Double
	var width: Double
	var height: Double
	var xoffset: Double
	var yoffset: Double
	var xadvance: Double
}

external interface IBitmapFontDataCommon {
	var lineHeight: Double
}

external interface IBitmapFontDataDistanceField {
	var fieldType: String
	var distanceRange: Double
}

external interface IBitmapFontDataInfo {
	var face: String
	var size: Double
}

external interface IBitmapFontDataKerning {
	var first: Int
	var second: Int
	var amount: Double
}

external interface IBitmapFontDataPage {
	var id: Int
	var file: String
}

external interface IBitmapFontOptions {
	var chars: dynamic? /* String | Array<String> | Array<Array<String>> */
	var resolution: Double?
	var padding: Int?
	var textureWidth: Int?
	var textureHeight: Int?
}

external interface IBitmapTextFontDescriptor {
	var name: String
	var size: Double
}

external interface IBitmapTextStyle {
	var fontName: String
	var fontSize: Double
	var tint: Color
	var align: TextStyleAlign
	var letterSpacing: Double
	var maxWidth: Double
}

external interface PageMeshData {
	var index: Int
	var indexCount: Int
	var vertexCount: Int
	var uvsCount: Int
	var total: Int
	var mesh: Mesh<MeshMaterial>
	var vertices: Float32Array?
	var uvs: Float32Array?
	var indices: Uint16Array?
}

external interface PartialIBitmapTextStyle {
	var fontName: String?
	var fontSize: Double?
	var tint: Color?
	var align: TextStyleAlign?
	var letterSpacing: Double?
	var maxWidth: Double?
}

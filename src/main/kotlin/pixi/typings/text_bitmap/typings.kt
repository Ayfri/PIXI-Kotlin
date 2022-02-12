@file:JsModule("@pixi/text-bitmap")

package pixi.typings.text_bitmap

import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array
import org.w3c.dom.XMLDocument
import pixi.typings.Number
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
	open val size: Number
	open val lineHeight: Number
	open val chars: Dict<IBitmapFontCharacter>
	open val pageTextures: Dict<Texture<Resource>>
	open val distanceFieldRange: Number
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
	protected open var _textWidth: Number
	protected open var _textHeight: Number
	protected open var _text: String
	protected open var _maxWidth: Number
	protected open var _maxLineHeight: Number
	protected open var _letterSpacing: Number
	protected open var _anchor: ObservablePoint<Any?>
	protected open var _fontName: String
	protected open var _fontSize: Number
	protected open var _align: TextStyleAlign
	protected open var _activePageMeshData: Array<PageMeshData>
	protected open var _tint: Number
	protected open var _roundPixels: Boolean
	
	open var tint: Number
	open var align: TextStyleAlign
	open var fontName: String
	open var fontSize: Number
	open var anchor: ObservablePoint<Any?>
	open var text: String
	open var maxWidth: Number
	open val maxLineHeight: Number
	open val textWidth: Number
	open var letterSpacing: Number
	open var roundPixels: Boolean
	open val textHeight: Number
	
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
	var xOffset: Number
	var yOffset: Number
	var xAdvance: Number
	var texture: Texture<Resource>
	var page: Number
	var kerning: Dict<Number>
}

external interface IBitmapFontDataChar {
	var id: Number
	var page: Number
	var x: Number
	var y: Number
	var width: Number
	var height: Number
	var xoffset: Number
	var yoffset: Number
	var xadvance: Number
}

external interface IBitmapFontDataCommon {
	var lineHeight: Number
}

external interface IBitmapFontDataDistanceField {
	var fieldType: String
	var distanceRange: Number
}

external interface IBitmapFontDataInfo {
	var face: String
	var size: Number
}

external interface IBitmapFontDataKerning {
	var first: Number
	var second: Number
	var amount: Number
}

external interface IBitmapFontDataPage {
	var id: Number
	var file: String
}

external interface IBitmapFontOptions {
	var chars: dynamic? /* String | Array<String> | Array<Array<String>> */
	var resolution: Number?
	var padding: Number?
	var textureWidth: Number?
	var textureHeight: Number?
}

external interface IBitmapTextFontDescriptor {
	var name: String
	var size: Number
}

external interface IBitmapTextStyle {
	var fontName: String
	var fontSize: Number
	var tint: Number
	var align: TextStyleAlign
	var letterSpacing: Number
	var maxWidth: Number
}

external interface PageMeshData {
	var index: Number
	var indexCount: Number
	var vertexCount: Number
	var uvsCount: Number
	var total: Number
	var mesh: Mesh<MeshMaterial>
	var vertices: Float32Array?
	var uvs: Float32Array?
	var indices: Uint16Array?
}

external interface PartialIBitmapTextStyle {
	var fontName: String?
	var fontSize: Number?
	var tint: Number?
	var align: TextStyleAlign?
	var letterSpacing: Number?
	var maxWidth: Number?
}

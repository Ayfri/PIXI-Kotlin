@file:JsModule("@pixi/spritesheet")

package pixi.typings.spritesheet

import pixi.typings.VarArgFun
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.loaders.LoaderResource
import pixi.typings.math.IPointData
import pixi.typings.utils.Dict
import kotlin.js.Promise

external interface ISpritesheetDataMeta {
	var scale: String
	var related_multi_packs: Array<String>?
}

external interface ISpritesheetData {
	var frames: Dict<ISpritesheetFrameData>
	var animations: Dict<Array<String>>?
	var meta: ISpritesheetDataMeta
}

external interface ISpritesheetFrameDataFrame {
	var x: Int
	var y: Int
	var w: Int
	var h: Int
}

external interface ISpritesheetFrameDataSourceSize {
	var w: Int
	var h: Int
}

external interface ISpritesheetFrameDataSpriteSourceSize : IPointData

external interface ISpritesheetFrameData {
	var frame: ISpritesheetFrameDataFrame
	var trimmed: Boolean?
	var rotated: Boolean?
	var sourceSize: ISpritesheetFrameDataSourceSize?
	var spriteSourceSize: ISpritesheetFrameDataSpriteSourceSize?
	var anchor: IPointData?
}

open external class Spritesheet(texture: Texture<Resource>, data: ISpritesheetData, resolutionFilename: String = definedExternally) {
	constructor(texture: BaseTexture<Resource, IAutoDetectOptions>, data: ISpritesheetData, resolutionFilename: String = definedExternally)
	
	open var linkedSheets: Array<Spritesheet>
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var textures: Dict<Texture<Resource>>
	open var animations: Dict<Array<Texture<Resource>>>
	open var data: ISpritesheetData
	open var resolution: Double
	
	open fun destroy(destroyBase: Boolean = definedExternally)
	open fun parse(callback: (textures: Dict<Texture<Resource>>) -> Unit)
	open fun parse(callback: () -> Unit)
	open fun parse(): Promise<Dict<Texture<Resource>>>
	
	companion object {
		val BATCH_SIZE: Int /* = 1000 */
	}
}

external class SpriteSheetLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any? /* unknown */, Unit>)
		fun getResourcePath(resource: LoaderResource, baseUrl: String)
	}
}

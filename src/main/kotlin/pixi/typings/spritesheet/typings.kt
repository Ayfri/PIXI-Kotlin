@file:JsModule("@pixi/spritesheet")

package pixi.typings.spritesheet

import pixi.typings.Number
import pixi.typings.VarArgFun
import pixi.typings.core.BaseTexture
import pixi.typings.core.IAutoDetectOptions
import pixi.typings.core.Resource
import pixi.typings.core.Texture
import pixi.typings.loaders.LoaderResource
import pixi.typings.math.IPointData
import pixi.typings.utils.Dict

external interface ISpritesheetDataMeta {
	var scale: String
}

external interface ISpritesheetData {
	var frames: Dict<ISpritesheetFrameData>
	var animations: Dict<Array<String>>?
	var meta: ISpritesheetDataMeta
}

external interface ISpritesheetFrameDataFrame {
	var x: Number
	var y: Number
	var w: Number
	var h: Number
}

external interface ISpritesheetFrameDataSourceSize {
	var w: Number
	var h: Number
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
	
	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var textures: Dict<Texture<Resource>>
	open var animations: Dict<Array<Texture<Resource>>>
	open var data: ISpritesheetData
	open var renderer: Number
	
	open fun destroy(destroyBase: Boolean = definedExternally)
	open fun parse(callback: (textures: Dict<Texture<Resource>>) -> Unit)
	open fun parse(callback: () -> Unit)
	
	companion object {
		val BATCH_SIZE: Number /* = 1000 */
	}
}

external class SpriteSheetLoader {
	companion object {
		fun use(resource: LoaderResource, next: VarArgFun<Any? /* unknown */, Unit>)
		fun getResourcePath(resource: LoaderResource, baseUrl: String)
	}
}

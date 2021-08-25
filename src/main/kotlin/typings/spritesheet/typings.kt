@file:JsModule("@pixi/spritesheet")

package typings.spritesheet

import typings.utils.Dict
import typings.VarArgFun
import typings.core.BaseTexture
import typings.core.IAutoDetectOptions
import typings.core.Resource
import typings.core.Texture
import typings.loaders.LoaderResource
import typings.math.IPointData

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

external interface ISpritesheetFrameDataSpriteSourceSize : ISpritesheetFrameDataSourceSize

external interface ISpritesheetFrameData {
	var frame: ISpritesheetFrameDataFrame
	var trimmed: Boolean?
	var rotated: Boolean?
	var sourceSize: ISpritesheetFrameDataSourceSize?
	var spriteSourceSize: ISpritesheetFrameDataSpriteSourceSize?
	var anchor: IPointData?
}

open external class Spritesheet(texture: Texture<Resource>, data: ISpritesheetData, resolutionFilename: String) {
	constructor(texture: Texture<Resource>, data: ISpritesheetData)
	constructor(texture: BaseTexture<Resource, IAutoDetectOptions>, data: ISpritesheetData, resolutionFilename: String)
	constructor(texture: BaseTexture<Resource, IAutoDetectOptions>, data: ISpritesheetData)

	open var baseTexture: BaseTexture<Resource, IAutoDetectOptions>
	open var textures: Dict<Texture<Resource>>
	open var animations: Dict<Array<Texture<Resource>>>
	open var data: ISpritesheetData
	open var renderer: Number

	open fun destroy(destroyBase: Boolean)
	open fun destroy()

	companion object {
		val BATCH_SIZE: Number /* = 1000 */
	}
}

external object SpriteSheetLoader {
	fun use(resource: LoaderResource, next: VarArgFun<Any? /* unknown */, Unit>)
	fun getResourcePath(resource: LoaderResource, baseUrl: String)
}
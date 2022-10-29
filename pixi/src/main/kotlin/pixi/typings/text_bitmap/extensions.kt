package pixi.typings.text_bitmap

import pixi.typings.loaders.IResourceMetadata
import pixi.typings.loaders.LoaderResource

external interface IBitmapFontResource {
	var bitmapFont: BitmapFont
}

inline var LoaderResource.bitmapFont
	get() = asDynamic().bitmapFont as? BitmapFont
	set(value) {
		asDynamic().bitmapFont = value
	}

external interface IBitmapFontResourceMetadata {
	var pageFile: String
}

inline var IResourceMetadata.pageFile
	get() = asDynamic().pageFile as? String
	set(value) {
		asDynamic().pageFile = value
	}

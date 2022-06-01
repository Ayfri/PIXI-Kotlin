package pixi.typings.compressed_textures

import org.khronos.webgl.DataView
import pixi.typings.core.BaseTexture

inline var BaseTexture<*, *>.ktxKeyValueData
	get() = asDynamic().ktxKeyValueData as Map<String, DataView>
	set(value) {
		asDynamic().ktxKeyValueData = value
	}
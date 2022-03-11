package pixi.externals

import kotlinx.js.jso
import pixi.externals.extensions.Sprite
import pixi.externals.extensions.color
import pixi.typings.app.Application
import pixi.typings.core.IGenerateTextureOptions
import pixi.typings.core.IRenderableObject
import pixi.typings.core.RenderTexture
import pixi.typings.core.Texture

interface Tuple<T1, T2, T3> {
	operator fun component1(): T1
	operator fun component2(): T2
	operator fun component3(): T3
}

class GenerateBlankTextureOptions {
	var width: Number? = null
	var height: Number? = null
	var color: Color? = null
	var resolution: Double? = null
}

fun generateBlankTexture(application: Application, options: GenerateBlankTextureOptions.() -> Unit): RenderTexture {
	val opts = jso(options)
	val sprite = Sprite(Texture.WHITE).apply {
		opts.width?.let { width = it.toDouble() }
		opts.height?.let { height = it.toDouble() }
		opts.color?.let { color = it }
	}
	
	return application.renderer.generateTexture(sprite.unsafeCast<IRenderableObject>(), jso<IGenerateTextureOptions> {
		opts.resolution?.let { resolution = it }
		region = sprite.getBounds()
	})
}

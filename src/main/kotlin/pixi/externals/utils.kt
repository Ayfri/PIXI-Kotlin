package pixi.externals

import kotlinx.js.jso
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

interface GenerateBlankTextureOptions {
	var width: Double?
	var height: Double?
	var color: Color?
	var resolution: Double?
}

fun generateBlankTexture(application: Application, options: GenerateBlankTextureOptions.() -> Unit): RenderTexture {
	val opts = jso(options)
	val sprite = Sprite(Texture.WHITE).apply {
		opts.width?.let { width = it }
		opts.height?.let { height = it }
		opts.color?.let { color = it }
	}
	
	return application.renderer.generateTexture(sprite as IRenderableObject, jso<IGenerateTextureOptions> {
		opts.resolution?.let { resolution = it }
		region = sprite.getBounds()
	})
}

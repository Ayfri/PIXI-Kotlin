package pixi.typings.extensions

import seskar.js.JsString

enum class ExtensionType {
	@JsString("application")
	Application,
	
	@JsString("renderer-plugin")
	RendererPlugin,
	
	@JsString("canvas-renderer-plugin")
	CanvasRendererPlugin,
	
	@JsString("loader")
	Loader,
	
	@JsString("load-parser")
	LoadParser,
	
	@JsString("resolve-parser")
	ResolveParser,
	
	@JsString("cache-parser")
	CacheParser,
	
	@JsString("detection-parser")
	DetectionParser,
}
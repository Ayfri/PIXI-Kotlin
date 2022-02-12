package pixi.typings.core

import org.khronos.webgl.ArrayBuffer
import pixi.typings.Object
import pixi.typings.VarArgFun

typealias ImageSource = Any
typealias IArrayBuffer = ArrayBuffer
typealias IRendererPlugins = Object<String, Any?>
typealias IRenderingContext = WebGL2RenderingContext
typealias IResourcePluginOptions = Object<String, Any?>
typealias UniformsSyncCallback = VarArgFun<Any?, Any?>
typealias UniformsSyncCallback_2 = VarArgFun<Any?, Any?>

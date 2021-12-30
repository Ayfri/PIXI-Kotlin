package typings.core

import org.khronos.webgl.ArrayBuffer
import typings.Indexed
import typings.VarArgFun

typealias ImageSource = Any
typealias IArrayBuffer = ArrayBuffer
typealias IRendererPlugins = Indexed<String, Any?>
typealias IRenderingContext = WebGL2RenderingContext
typealias IResourcePluginOptions = Indexed<String, Any?>
typealias UniformsSyncCallback = VarArgFun<Any?, Any?>
typealias UniformsSyncCallback_2 = VarArgFun<Any?, Any?>

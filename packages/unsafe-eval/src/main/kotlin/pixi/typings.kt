@file:JsModule("@pixi/unsafe-eval")

package pixi

import pixi.typings.core.ShaderSystem

external fun install(install: PIXICore)

external interface PIXICore {
	var ShaderSystem: ShaderSystem
}

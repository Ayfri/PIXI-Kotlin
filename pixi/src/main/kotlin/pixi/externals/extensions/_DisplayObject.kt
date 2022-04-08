package pixi.externals.extensions

import kotlinx.browser.window
import pixi.typings.app.Application
import pixi.typings.display.DisplayObject

fun DisplayObject.addToApplication(application: Application) {
	application.stage.addChild(this)
}

fun DisplayObject.collidesWith(other: DisplayObject) = getBounds().collidesWith(other.getBounds())

fun DisplayObject.hide() {
	this.visible = false
}

fun DisplayObject.removeFromApplication(application: Application) {
	application.stage.removeChild(this)
}

fun DisplayObject.setPositionFromApplication(application: Application, factorX: Double, factorY: Double) {
	x = application.renderer.width * factorX
	y = application.renderer.height * factorY
}


fun DisplayObject.setPositionFromWindow(factorX: Double, factorY: Double) {
	x = window.innerWidth * factorX
	y = window.innerHeight * factorY
}

fun DisplayObject.show() {
	this.visible = true
}

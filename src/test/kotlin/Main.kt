import kotlinx.browser.document
import kotlinx.browser.window
import pixi.typings.app.Application
import pixi.typings.loaders.Loader
import pixi.typings.sprite.Sprite
import pixi.typings.ticker.Ticker
import pixi.utils.Application
import kotlin.random.Random

lateinit var app: Application

fun main() {
	pixi.typings.require("pixi.js")
	app = Application {
		backgroundColor = (0xf0f0f0).toDouble()
		resizeTo = window
	}
	
	Loader.shared.add("test", "test.png").load(::start)
	document.getElementById("root")!!.appendChild(app.view)
}

fun start() {
	val sprite = Sprite.from("test.png").apply {
		width = 100.0
		height = 100.0
		x = window.innerWidth / 2.0
		y = window.innerHeight / 2.0
	}
	
	Ticker.shared.add<Any>({ _, _ ->
		sprite.apply {
			x = Random.nextDouble() * window.innerWidth
			y = Random.nextDouble() * window.innerHeight
		}
	})
	
	app.stage.addChild(sprite)
}

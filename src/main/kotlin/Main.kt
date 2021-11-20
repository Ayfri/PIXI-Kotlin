
import kotlinx.browser.document
import kotlinx.browser.window
import typings.app.Application
import typings.app.resizeTo
import typings.loaders.Loader
import typings.sprite.Sprite
import typings.ticker.Ticker
import utils.create
import kotlin.random.Random

lateinit var app: Application

fun main() {
	typings.require("pixi.js")
	app = Application.create { backgroundColor = 08080808.0 }
	app.resizeTo = window
	
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

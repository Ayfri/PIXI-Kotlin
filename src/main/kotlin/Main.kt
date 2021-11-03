
import kotlinx.browser.document
import kotlinx.browser.window
import typings.app.Application
import typings.loaders.Loader
import typings.sprite.Sprite
import typings.ticker.Ticker
import utils.create
import kotlin.random.Random

lateinit var app: Application

fun main() {
	app = Application.create { backgroundColor = 08080808f }
	app.asDynamic().resizeTo = window
	
	Loader.shared.add("test", "test.png").load(::start)
	document.getElementById("root")!!.appendChild(app.view)
}

fun start() {
	val sprite = Sprite.from("test.png").apply {
		width = 100
		height = 100
		x = window.innerWidth / 2
		y = window.innerHeight / 2
	}

	var i = 0
	Ticker.shared.add<Any>({ _, _ ->
		i++
		sprite.apply {
			x = Random.nextFloat() * window.innerWidth
			y = Random.nextFloat() * window.innerHeight
		}
	})
	
	app.stage.addChild(sprite)
}


import kotlinx.browser.document
import kotlinx.browser.window
import typings.app.Application
import typings.loaders.Loader
import typings.sprite.Sprite
import typings.ticker.Ticker
import utils.Application
import kotlin.random.Random

lateinit var app: Application

external interface Test {
	var test: dynamic
}
class Options(val options: Test)
fun Options(options: TestImpl.() -> Unit) = Options(TestImpl().apply(options))
class TestImpl : Test {
	override var test: String? = null
}

fun test() {
	val options = Options {
		test = "test"
	}
	println(JSON.stringify(options))
}

fun main() {
	typings.require("pixi.js")
	test()
	app = Application {
		backgroundColor = (0xf0f0f0).toDouble()
		resizeTo = window
	}
//	(app.renderer as Renderer).backgroundColor = (0xffffff).toDouble()
	
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

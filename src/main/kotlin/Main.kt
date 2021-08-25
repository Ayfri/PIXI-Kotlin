
import kotlinext.js.get
import kotlinx.browser.document
import kotlinx.browser.window
import typings.app.Application
import typings.core.Texture
import typings.loaders.Loader
import typings.sprite.Sprite
import typings.ticker.Ticker
import typings.ticker.UPDATE_PRIORITY
import kotlin.random.Random

fun main() {
	val app = Application()
	app.resizeTo = window
	val sprite = Sprite.from(Texture.WHITE).apply {
		width = 100
		height = 100
		x = window.innerWidth / 2
		y = window.innerHeight / 2
	}
	
	app.stage.addChild(sprite)
	
	window.onload = {
		val ticker = Ticker.shared.add<Any>({ _, _ ->
			sprite.apply {
				x = Random.nextFloat() * window.innerWidth
				y = Random.nextFloat() * window.innerHeight
			}
		}, {}, UPDATE_PRIORITY.HIGH)
		
		console.log(ticker)
		
		Loader.shared.add("test", "./test.png")
		Loader.shared.load { _, resources ->
			sprite.texture = resources["test"]!!.texture!!
		}
		
		document.getElementById("root")!!.appendChild(app.view)
	}
}

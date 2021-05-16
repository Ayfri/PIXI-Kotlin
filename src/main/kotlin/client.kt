
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Window
import typings.PIXI
import typings.UPDATE_PRIORITY
import kotlin.random.Random

fun main() {
	val app = PIXI.Application()
	app.resizeTo = window
	val sprite = PIXI.Sprite.from(PIXI.Texture.WHITE).apply {
		width = 100
		height = 100
		x = window.innerWidth / 2
		y = window.innerHeight / 2
	}
	
	app.stage.addChild(sprite)
	
	window.onload = {
		val ticker = PIXI.Ticker.shared.add<Any>({ _, _ ->
			sprite.apply {
				x = Random.nextFloat() * window.innerWidth
				y = Random.nextFloat() * window.innerHeight
			}
		}, {}, UPDATE_PRIORITY.HIGH)
		
		console.log(ticker)
		
		PIXI.Loader.shared.add("test", "./test.png")
		PIXI.Loader.shared.load { _, resources ->
			sprite.texture = resources.test.texture as PIXI.Texture
		}
		
		document.getElementById("root")!!.appendChild(app.view)
	}
	
	window.PIXI = PIXI
}

inline var Window.PIXI
	get() = asDynamic().PIXI
	set(value) {
		asDynamic().PIXI = value
	}

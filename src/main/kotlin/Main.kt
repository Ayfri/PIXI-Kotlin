
import kotlinext.js.Record
import kotlinext.js.set
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Document
import typings.app.Application
import typings.sprite.Sprite
import typings.ticker.Ticker
import utils.create
import kotlin.random.Random

var data: Record<Any, Any> = Record {}

fun main() {
	val app = Application.create {
		width = window.innerWidth
		height = window.innerHeight
		backgroundColor = 08080808f
	}
	
	document.getElementById("root")!!.appendChild(app.view)
	
	val sprite = Sprite.from("test.png").apply {
		width = 100
		height = 100
		x = window.innerWidth / 2
		y = window.innerHeight / 2
	}
	
	val ticker = Ticker.shared.add<Any>({ _, _ ->
		sprite.apply {
			x = Random.nextFloat() * window.innerWidth
			y = Random.nextFloat() * window.innerHeight
		}
	})

	console.log(ticker)
	app.stage.addChild(sprite)
	console.log(app);
	
//	document.body!!.appendChild(app.view)
	
//		val canvas =
	document.d()["body"] = document.body!!
	console.log(document.d())
	
	
}

@Suppress("NOTHING_TO_INLINE")
inline fun Document.d(): Record<Any, Any> = data

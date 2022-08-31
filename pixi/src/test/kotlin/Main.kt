package tests

import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.div
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.js.get
import kotlinx.js.import
import pixi.externals.Color
import pixi.externals.extensions.add
import pixi.externals.extensions.addToApplication
import pixi.externals.extensions.load
import pixi.externals.extensions.once
import pixi.externals.extensions.plus
import pixi.externals.extensions.setPositionFromWindow
import pixi.externals.generateBlankTexture
import pixi.typings.app.Application
import pixi.typings.core.VERSION
import pixi.typings.loaders.Loader
import pixi.typings.loaders.loader
import pixi.typings.math.Point
import pixi.typings.sprite.Sprite
import pixi.typings.ticker.Ticker
import pixi.typings.utils.EventEmitter
import pixi.utils.application
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object Tests : EventEmitter() {
	lateinit var sprite: Sprite
	var app: Application
	
	init {
		import<Any>("pixi.js")
		
		document.body!!.append {
			div {
				id = "root"
			}
		}
		
		app = application {
			backgroundColor = Color("#eee")
			resizeTo = window
		}
		
		document.querySelector("#root")!!.appendChild(app.view)
		
		console.log("Pixi.js version: $VERSION")
		app.loader.add("test", "test.png").load(::start)
	}
	
	fun start(loader: Loader) {
		val texture = (loader.resources["test"] ?: return).texture ?: return
		
		val size = 250.0
		sprite = Sprite(texture).apply {
			width = size
			height = size
			anchor.set(0.5)
		}
		
		Ticker.shared.add {
			sprite.apply {
				x = (Random.nextDouble() * window.innerWidth).coerceIn(size / 2, window.innerWidth - size / 2)
				y = (Random.nextDouble() * window.innerHeight).coerceIn(size / 2, window.innerHeight - size / 2)
			}
		}
		
		app.stage.addChild(sprite)
		emit("ready")
	}
	
	@Test
	fun appIsAdded() {
		once("ready") {
			assertNotNull(app)
			assertNotNull(document.querySelector("canvas"))
		}
	}
	
	@Test
	fun spriteIsAdded() {
		once("ready") {
			assertNotNull(sprite)
			assertContains(app.stage.children, sprite)
		}
	}
	
	@Test
	fun generatedTexture() {
		once("ready") {
			val sprite = Sprite(generateBlankTexture(app) {
				color = Color(255, 0, 0)
				width = 300
				height = 300
			})
			sprite.anchor.set(0.5)
			sprite.setPositionFromWindow(0.5, 0.5)
			sprite.addToApplication(app)
		}
	}
	
	@Test
	fun immutablePoint() {
		var a = Point(1.0, 2.0)
		val b = Point(4.0, -2.5)
		a + b
		assertEquals(a.x, 1.0)
		a += b
		assertEquals(a.x, 5.0)
		val c = a.clone() + b
		assertEquals(c.x, 9.0)
		assertEquals(a.x, 5.0)
	}
}

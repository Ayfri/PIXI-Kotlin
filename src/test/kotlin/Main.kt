package tests

import kotlinx.browser.document
import kotlinx.browser.window
import pixi.typings.app.Application
import pixi.typings.loaders.Loader
import pixi.typings.sprite.Sprite
import pixi.typings.ticker.Ticker
import utils.Application
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertNotNull

lateinit var app: Application

class Tests {
	lateinit var sprite: Sprite
	
	init {
		println("executing main")
		pixi.typings.require("pixi.js")
		app = Application {
			backgroundColor = 0xf0f0f0
			resizeTo = window
		}
		
		Loader.shared.add("test", "test.png").load { _, _ -> start() }
		document.getElementById("root")!!.appendChild(app.view)
	}
	
	fun start() {
		val size = 250.0
		sprite = Sprite.from("test.png").apply {
			width = size
			height = size
			anchor.set(0.5)
		}
		
		Ticker.shared.add<Any>({ _, _ ->
			sprite.apply {
				x = (Random.nextDouble() * window.innerWidth).coerceIn(size / 2, window.innerWidth - size / 2)
				y = (Random.nextDouble() * window.innerHeight).coerceIn(size / 2, window.innerHeight - size / 2)
			}
		})
		
		app.stage.addChild(sprite)
	}
	
	@Test
	fun testAppAdded() {
		assertNotNull(document.querySelector("canvas"))
	}
	
	@Test
	fun testSprite() {
		assertContains(app.stage.children, sprite)
	}
}

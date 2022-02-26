![GitHub Workflow Status](https://img.shields.io/github/workflow/status/Ayfri/PIXI-Kotlin/Kotlin%20CI?style=flat-square)
![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/io.github.ayfri/PIXI-Kotlin?server=https%3A%2F%2Fs01.oss.sonatype.org&style=flat-square&label=Latest%20Version)
![PIXI.js Version](https://img.shields.io/badge/pixi.js-6.2.2-blue?style=flat-square&logo=npm)

# PIXI-Kotlin

This is a simple example of what a [PIXI.js](https://github.com/pixijs/pixijs) transcription in Kotlin could look like.

For now, there are all the classes, interfaces, enums, functions, type aliases, and objects. The private members are not present.

The types are from [`PIXI 6.2.2`](https://github.com/pixijs/pixijs/releases/tag/v6.2.2).

## Usage

To use it in a project, just add this to your dependencies:

```kotlin
repositories {
	...
	mavenLocal()
}

dependencies {
	implementation("io.github.ayfri:PIXI-Kotlin:VERSION")
}
```

## Example

Simple application with a sprite, size change when clicking.

```kotlin
fun main() {
	val app = Application {
		backgroundColor = Color(120, 200, 230)
		resizeTo = window
	}
	app.addToBody()
	
	val bunny = Sprite.from("bunny.png").apply {
		setPositionFromWindow(0.5, 0.5)
		anchor.set(0.5)
		interactive = true
		addToApplication(app)
	}
	
	bunny.on(DisplayObjectEvents.mousedown) {
		bunny.scale.set(1.1)
	}
	
	bunny.on(DisplayObjectEvents.mouseup) {
		bunny.scale.set(1.0)
	}
}
```

Application with keymap and test if sprite sticks out of area.

```kotlin
fun main() {
	val app = Application {
		resizeTo = window
	}
	app.addToBody()
	
	val speed = 10.0
	val sprite = Sprite(generateBlankTexture(app) {
		width = 300.0
		height = 300.0
		color = Color(255, 0, 0)
	}).apply {
		addToApplication(app)
		anchor.set(0.5)
		setPositionFromApplication(app, 0.5, 0.5)
		window["sprite"] = this
	}
	
	val area = Rectangle(0.0, 0.0, app.screen.width, app.screen.height)
	
	app.renderer.on(AbstractRendererEvents.resize) {
		area.setSize(app.screen.width, app.screen.height)
	}
	
	
	val english = "en" in window.navigator.languages.elementAtOrElse(0) { window.navigator.language }
	KeyMap(
		mapOf(
			"forward" to setOf(if (english) "W" else "Z", "ArrowUp"),
			"backward" to setOf("S", "ArrowDown"),
			"left" to setOf(if (english) "A" else "Q", "ArrowLeft"),
			"right" to setOf("D", "ArrowRight"),
			"power" to setOf(" ")
		),
		ignoreCase = true
	).apply {
		onKeep("forward") {
			if ((sprite.hitBox.top + speed * 2) > area.top) sprite.y -= speed
		}
		onKeep("backward") {
			if ((sprite.hitBox.bottom - speed * 2) < area.bottom) sprite.y += speed
		}
		onKeep("left") {
			if ((sprite.hitBox.left + speed * 2) > area.left) sprite.x -= speed
		}
		onKeep("right") {
			if ((sprite.hitBox.right - speed * 2) < area.right) sprite.x += speed
		}
		
		onPress("power") {
			sprite.alpha = if (sprite.alpha == 0.1) 1.0 else 0.1
		}
	}
}
```

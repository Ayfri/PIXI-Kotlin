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
	
	val english = "en" in window.navigator.languages.elementAtOrElse(0) { window.navigator.language }
	
	val keyMap = KeyMap(
		mapOf(
			"forward" to listOf(if (english) "W" else "Z", "ArrowUp"),
			"backward" to listOf("S", "ArrowDown"),
			"left" to listOf(if (english) "A" else "Q", "ArrowLeft"),
			"right" to listOf("D", "ArrowRight"),
		),
		ignoreCase = true
	)
	
	val sprite = Sprite(generateBlankTexture(app) {
		width = 300.0
		height = 300.0
		color = Color(255, 0, 0)
	})
	sprite.addToApplication(app)
	sprite.setPositionFromApplication(app, 0.5, 0.5)
	
	val area = Rectangle(0.0, 0.0, app.screen.width, app.screen.height)
	
	keyMap.onPress("forward") {
		sprite.y -= 10
		if (sprite.hitBox !in area) sprite.y += 10
	}
	
	keyMap.onPress("backward") {
		sprite.y += 10
		if (sprite.hitBox !in area) sprite.y -= 10
	}
	
	keyMap.onPress("left") {
		sprite.x -= 10
		if (sprite.hitBox !in area) sprite.x += 10
	}
	
	keyMap.onPress("right") {
		sprite.x += 10
		if (sprite.hitBox !in area) sprite.x -= 10
	}
	
	keyMap.keyboardManager.onPress("Space") {
		sprite.visible = !sprite.visible
	}
}
```

![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/Ayfri/PIXI-Kotlin/Kotlin%20CI/mastere?style=flat-square)
![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/io.github.ayfri/PIXI-Kotlin?style=flat-square&label=Latest%20Version)
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

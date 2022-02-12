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
	implementation("io.github.ayfri:PIXI-Kotlin:0.1.0")
}
```

For now, you have to build the lib locally by running:

```
./gradlew publishToMavenLocal
```


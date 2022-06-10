rootProject.name = "PIXI-Kotlin"

pluginManagement {
	resolutionStrategy {
		plugins {
			val kotlinVersion = "1.7.0"
			kotlin("js") version kotlinVersion
			kotlin("jvm") version kotlinVersion
		}
	}
	
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

include("pixi")

include("packages:basis")

include("packages:events")

include("packages:graphics-extras")

include("packages:math-extras")

include("packages:unsafe-eval")

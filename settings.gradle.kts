rootProject.name = "PIXI-Kotlin"

pluginManagement {
	resolutionStrategy {
		plugins {
			val kotlinVersion = extra["kotlin.version"] as String
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

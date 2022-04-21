rootProject.name = "PIXI-Kotlin"

pluginManagement {
	resolutionStrategy {
		plugins {
			kotlin("js") version "1.6.21"
			kotlin("jvm") version "1.6.21"
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

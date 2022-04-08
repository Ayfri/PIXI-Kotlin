rootProject.name = "PIXI-Kotlin"

pluginManagement {
	resolutionStrategy {
		plugins {
			kotlin("js") version "1.6.20"
		}
	}
}

dependencyResolutionManagement {
	repositories {
		mavenCentral()
	}
}

include("pixi")

include("packages:basis")

include("packages:events")

include("packages:graphics-extras")

include("packages:math-extras")

include("packages:unsafe-eval")

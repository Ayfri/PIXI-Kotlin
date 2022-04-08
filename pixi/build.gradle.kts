import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR

plugins {
	kotlin("js")
	id("io.github.turansky.seskar") version Versions.seskar
	`js-conventions`
	`publish-conventions`
}

repositories {
	uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}

dependencies {
	api("io.github.turansky.seskar:seskar-core:${Versions.seskar}")
	api("org.jetbrains.kotlinx:kotlinx-html:${Versions.kotlinxHTML}")
	api("org.jetbrains.kotlin-wrappers:kotlin-extensions:${Versions.kotlinWrappers}")
	implementation(npm("pixi.js", Versions.pixi))
	testImplementation(kotlin("test-js"))
}

tasks.withType<KotlinCompile<*>>().configureEach {
	kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
}

kotlin.js(IR) {
	browser {
		commonWebpackConfig {
			sourceMaps = true
		}
		
		testTask {
			useKarma {
				useSourceMapSupport()
				useFirefox()
				
				webpackConfig.sourceMaps = true
			}
		}
	}
}


import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR
import java.util.*

val props = Properties().apply {
	file("../gradle.properties").inputStream().use { load(it) }
}

fun version(target: String) = props.getProperty("${target}.version")


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
	api("org.jetbrains.kotlinx:kotlinx-html-js:${Versions.kotlinxHTML}")
	api("org.jetbrains.kotlin-wrappers:kotlin-js:${Versions.kotlinWrappers}")
	implementation(npm("pixi.js", Versions.pixi))
	testImplementation(kotlin("test-js", version("kotlin")))
}

kotlin.js(IR) {
	browser {
		commonWebpackConfig {
			sourceMaps = true
		}
		
		testTask {
			useKarma {
				useSourceMapSupport()
				useChrome()
				
				webpackConfig.sourceMaps = true
			}
		}
	}
}

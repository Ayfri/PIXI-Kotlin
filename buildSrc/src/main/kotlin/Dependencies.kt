object Project {
	const val group = "fr.ayfri.pixi-kotlin"
	const val version = "0.1.0"
}

object Versions {
	const val java = "16"
	const val kotlin = "1.5.31"
	const val seskar = "0.1.0"
	const val kotlinExtensions = "1.0.1-pre.263-kotlin-1.5.31"
	const val kotlinHtml = "0.7.3"
	const val pixi = "6.2.0"
}

object Dependencies {
	val mavenRepositories = listOf(
		"https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers"
	)

	val dependencies = mapOf(
		"com.github.turansky.seskar:seskar-core" to Versions.seskar,
		"org.jetbrains.kotlinx:kotlinx-html" to Versions.kotlinHtml,
		"org.jetbrains.kotlin-wrappers:kotlin-extensions" to Versions.kotlinExtensions,
		"org.jetbrains.kotlin:kotlin-stdlib-js" to Versions.kotlin
	)

	val plugins = mapOf(
		"org.jetbrains.kotlin.js" to Versions.kotlin,
		"com.github.turansky.seskar" to Versions.seskar
	)

	val npmDependencies = mapOf(
		"pixi.js" to Versions.pixi
	)
}

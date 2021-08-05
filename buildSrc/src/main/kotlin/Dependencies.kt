object Project {
    const val group = "fr.ayfri.pixi-kotlin"
    const val version = "0.1.0"
}

object Versions {
    const val java = "16"
    const val kotlin = "1.5.21"
    const val seskar = "0.0.10"
    const val kotlinHtml = "0.7.3"
    const val pixi = "6.1.0"
}

object Dependencies {
    val dependencies = mapOf(
        "com.github.turansky.seskar:seskar-core" to Versions.seskar,
        "org.jetbrains.kotlinx:kotlinx-html" to Versions.kotlinHtml
    )

    val plugins = mapOf(
        "org.jetbrains.kotlin.js" to Versions.kotlin,
        "com.github.turansky.seskar" to Versions.seskar
    )

    val npmDependencies = mapOf(
        "pixi.js" to Versions.pixi
    )
}
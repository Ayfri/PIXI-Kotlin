import org.gradle.api.Project

internal val Project.isKotlinJsProject get() = plugins.hasPlugin("org.jetbrains.kotlin.js")

import org.gradle.api.Project as GradleProject

internal val GradleProject.isKotlinJsProject get() = plugins.hasPlugin("org.jetbrains.kotlin.js")

internal val GradleProject.publishName get() = "${Project.name}-$name"

internal val GradleProject.publishVersion
	get() = when (name) {
		else -> Project.version
	}

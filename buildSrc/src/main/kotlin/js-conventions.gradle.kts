import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinJsPluginWrapper

plugins.withType<KotlinJsPluginWrapper> {
	extensions.configure<KotlinJsProjectExtension> {
		js(IR) {
			moduleName = project.name
			
			useCommonJs()
			browser {
				commonWebpackConfig {
					outputFileName = "dist.js"
				}
			}
			
			logger.lifecycle("Configured Kotlin.js project")
			logger.lifecycle(project.publishName, project.publishVersion)
			binaries.library()
		}
	}
}


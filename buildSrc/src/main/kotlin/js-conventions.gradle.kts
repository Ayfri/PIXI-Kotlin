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
			
			binaries.executable()
		}
	}
}


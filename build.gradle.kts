import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
	kotlin("js") apply false
}

allprojects {
	nexusPublishing {
		repositories {
			sonatype {
				nexusUrl.set(uri(Project.publishUrl))
				snapshotRepositoryUrl.set(uri(Project.snapshotUrl))
				username.set(System.getenv("OSSRH_USER") ?: return@sonatype)
				password.set(System.getenv("OSSRH_PASSWORD") ?: return@sonatype)
			}
		}
	}
}

tasks.wrapper {
	gradleVersion = "7.4.2"
}

plugins.withType<YarnPlugin> {
	the<YarnRootExtension>().apply {
		lockFileDirectory = projectDir
		version = "1.22.18"
	}
}

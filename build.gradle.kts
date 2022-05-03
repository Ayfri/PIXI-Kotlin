import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
	kotlin("js") apply false
}

nexusPublishing {
	repositories {
		sonatype {
			nexusUrl.set(uri(Project.publishUrl))
			snapshotRepositoryUrl.set(uri(Project.snapshotUrl))
			username.set(System.getenv("OSSRH_USER") ?: return@sonatype)
			password.set(System.getenv("OSSRH_PASSWORD") ?: return@sonatype)
			stagingProfileId.set(
				properties["oss.stagingProfileId"]?.toString() ?: System.getenv("OSS_STAGING_PROFILE_ID") ?: return@sonatype
			)
		}
	}
}

val initializeSonatypeStagingRepository by tasks.existing
subprojects {
	initializeSonatypeStagingRepository {
		shouldRunAfter(tasks.withType<Sign>())
	}
}

tasks.wrapper {
	gradleVersion = "7.4.2"
}

tasks.register("buildAndPublish") {
	group = "publishing"
	description = "Builds and publishes the project"
	
	dependsOn(":clean")
	dependsOn(":assemble")
	dependsOn(":publishToSonatype")
}

plugins.withType<YarnPlugin> {
	the<YarnRootExtension>().apply {
		lockFileDirectory = projectDir
		version = "1.22.18"
	}
}

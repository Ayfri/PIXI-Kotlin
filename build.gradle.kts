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

tasks.register("publishAll") {
	group = "publishing"
	description = "Publish all submodules at once."
	
	dependsOn(subprojects.filter {
		it.childProjects.isEmpty()
	}.map {
		"${it.path}:buildAndPublish"
	}).finalizedBy("closeAndReleaseSonatypeStagingRepository")
}

plugins.withType<YarnPlugin> {
	the<YarnRootExtension>().apply {
		lockFileDirectory = projectDir
		version = "1.22.18"
	}
}

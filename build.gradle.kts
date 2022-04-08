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

tasks.wrapper {
	gradleVersion = "7.4.2"
}

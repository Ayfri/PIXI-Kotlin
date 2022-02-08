object Project {
	const val group = "io.github.ayfri"
	const val version = "0.1.0-SNAPSHOT"
	const val name = "PIXI-Kotlin"
	const val description = "Kotlin bindings for PIXI.js"
	const val url = "https://github.com/Ayfri/PIXI-kotlin"
}


plugins {
	kotlin("js") version "1.6.10"
	id("io.github.turansky.seskar") version "0.5.0"
	id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
	`maven-publish`
	signing
}

val projectName = Project.name
group = Project.group
version = Project.version

repositories {
	mavenCentral()
	uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}


dependencies {
	implementation("io.github.turansky.seskar:seskar-core:0.5.0")
	implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
	implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.294-kotlin-1.6.10")
	implementation("org.jetbrains.kotlin-wrappers:kotlin-typescript:4.5.5-pre.294-kotlin-1.6.10")
	implementation(npm("pixi.js", "6.2.2"))
}

tasks {
	create("buildAndPublish") {
		group = "publishing"
		description = "Builds and publishes the project"
		dependsOn("clean")
		dependsOn("build")
		dependsOn("publishAllPublicationsToSonatypeRepository", "closeAndReleaseSonatypeStagingRepository")
	}
}


kotlin {
	kotlinDaemonJvmArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
	
	js(IR) {
		useCommonJs()
		browser {
			commonWebpackConfig {
				cssSupport.enabled = true
				outputFileName = "dist.js"
			}
		}
		binaries.executable()
	}
}

val sourcesJar by tasks.registering(Jar::class) {
	archiveClassifier.set("sources")
	from(kotlin.sourceSets.main.get().kotlin)
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = project.group.toString()
			artifactId = project.name
			version = project.version.toString()
			from(components["kotlin"])
			artifact(tasks["sourcesJar"])
			pom {
				name.set("PIXI-Kotlin")
				description.set("Library for using PIXI.js in Kotlin-js")
				url.set("https://github.com/Ayfri/PIXI-Kotlin")
				licenses {
					license {
						name.set("GPLv3")
						url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
					}
				}
				developers {
					developer {
						id.set("Ayfri")
						name.set("Roy Pierre")
						email.set("me@ayfri.fr")
					}
					developer {
						id.set("…")
						name.set("…")
						email.set("…")
					}
					developer {
						id.set("…")
						name.set("…")
						email.set("…")
					}
				}
				scm { // TODO not sure what this is. https://en.wikipedia.org/wiki/Software_configuration_management
					connection.set("…")
					developerConnection.set("…")
					url.set("…")
				}
			}
		}
	}
	repositories {
		maven {
			name = "OSSRH"
			setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2")
			credentials {
				username = System.getenv("OSSRH_USER") ?: return@credentials
				password = System.getenv("OSSRH_PASSWORD") ?: return@credentials
			}
		}
	}
}


signing {
	val key = System.getenv("SIGNING_KEY") ?: return@signing
	val password = System.getenv("SIGNING_PASSWORD") ?: return@signing
	val publishing: PublishingExtension by project
	useInMemoryPgpKeys(key, password)
	sign(publishing.publications)
}


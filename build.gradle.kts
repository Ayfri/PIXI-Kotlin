object Project {
	const val group = "io.github.ayfri"
	const val version = "0.2.2"
	const val name = "PIXI-Kotlin"
	const val description = "Kotlin bindings for PIXI.js"
	const val githubUrl = "Ayfri/PIXI-kotlin"
	const val url = "https://github.com/$githubUrl"
	const val publishUrl = "https://s01.oss.sonatype.org/service/local/"
	const val snapshotUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
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
	implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.307-kotlin-1.6.10")
	implementation(npm("pixi.js", "6.2.2"))
	implementation(npm("@pixi/events", "6.2.2"))
	implementation(npm("@pixi/graphics-extras", "6.2.2"))
	testImplementation(kotlin("test-js"))
}

tasks {
	create("buildAndPublish") {
		group = "publishing"
		description = "Builds and publishes the project"
		dependsOn("clean")
		dependsOn("assemble")
		dependsOn("publishToSonatype", "closeAndReleaseSonatypeStagingRepository")
	}
}


kotlin {
	kotlinDaemonJvmArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
	
	js(IR) {
		useCommonJs()
		browser {
			commonWebpackConfig {
				outputFileName = "dist.js"
				sourceMaps = true
			}
			
			testTask {
				useKarma {
					useSourceMapSupport()
					useFirefox()
					
					webpackConfig.sourceMaps = true
				}
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
				name.set(Project.name)
				description.set(Project.description)
				url.set(Project.url)
				
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
						email.set("pierre.ayfri@gmail.com")
					}
					developer {
						id.set("maxonline")
						name.set("Maximilian Skog")
					}
				}
				
				scm {
					connection.set("scm:git:git://github.com/${Project.githubUrl}")
					developerConnection.set("scm:git:git://github.com/${Project.githubUrl}")
					url.set(Project.url)
				}
			}
		}
	}
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

signing {
	val key = System.getenv("SIGNING_KEY") ?: return@signing
	val password = System.getenv("SIGNING_PASSWORD") ?: return@signing
	val extension = extensions.getByName("publishing") as PublishingExtension
	
	useInMemoryPgpKeys(key, password)
	sign(extension.publications)
}

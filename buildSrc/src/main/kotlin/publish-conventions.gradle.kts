plugins {
	`maven-publish`
	signing
}

configure<PublishingExtension> {
	publications {
		if (isKotlinJsProject) {
			create<MavenPublication>("kotlin") {
				from(components["kotlin"])
				
				groupId = Project.group
				artifactId = project.publishName
				version = project.publishVersion
				
				artifact(tasks.getByName<Zip>("jsSourcesJar"))
				
				pom {
					name.set(project.name)
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
						connection.set("scm:git:${"git://github.com/${Project.githubUrl}"}")
						developerConnection.set("scm:git:${"git://github.com/${Project.githubUrl}"}")
						url.set(Project.url)
					}
				}
			}
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

tasks {
	create("buildAndPublish") {
		group = "publishing"
		description = "Builds and publishes the project"
		dependsOn("clean")
		dependsOn("assemble")
		dependsOn("publishToSonatype:closeAndReleaseSonatypeStagingRepository")
	}
}

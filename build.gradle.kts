import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


object Project {
    const val group = "io.github.ayfri"
    const val version = "0.1.0"
    const val name = "PIXI-Kotlin"
    const val description = "Kotlin bindings for PIXI.js"
    const val url = "https://github.com/Ayfri/PIXI-kotlin"
}


plugins {
    kotlin("js") version "1.6.0"
    id("com.github.turansky.seskar") version "0.2.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    //id("com.github.turansky.kfc.library") version "4.50.0"
    //id("com.github.turansky.kfc.maven-central-publish") version "4.50.0"
    `maven-publish`
}

val projectName = Project.name
group = Project.group
version = Project.version

repositories {
    mavenCentral()
    uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}



dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js:1.6.0")
    implementation("com.github.turansky.seskar:seskar-core:0.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.263-kotlin-1.5.31")
    implementation(npm("pixi.js", "6.2.0"))
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

fun getExtraString(name: String) = ext[name]?.toString()

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
        }
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
        }
    }
}
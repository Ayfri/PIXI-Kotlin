
object Project {
    const val group = "io.github.ayfri.pixi-kotlin"
    const val version = "0.1.0"
    const val name = "pixi-kotlin"
    const val description = "Kotlin bindings for library PIXI.js"
    const val url = "https://github.com/Ayfri/PIXI-kotlin"
}


plugins {
    kotlin("js") version "1.5.31"
    id("com.github.turansky.seskar") version "0.1.0"
//    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
//    id("com.github.turansky.kfc.library") version "4.50.0"
//    id("com.github.turansky.kfc.maven-central-publish") version "4.50.0"
//    `maven-publish`
}

val projectName = Project.name
group = Project.group
version = Project.version

repositories {
    mavenCentral()
    uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("com.github.turansky.seskar:seskar-core:0.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.263-kotlin-1.5.31")
    implementation(npm("pixi.js", "6.2.0"))
}

//tasks {
//    create("buildAndPublish") {
//        group = "publishing"
//        description = "Builds and publishes the project"
//        dependsOn("clean")
//        dependsOn("build")
//        dependsOn("publishAllPublicationsToSonatypeRepository", "closeAndReleaseSonatypeStagingRepository")
//    }
//}
//
//fun getExtraString(name: String) = ext[name]?.toString()
//
//nexusPublishing {
//    repositories {
//        sonatype {
//            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//        }
//    }
//}

//rootProject.plugins.withType<YarnPlugin> {
//    rootProject.the<YarnRootExtension>().apply {
//        resolution("@webpack-cli/serve", "1.5.2")
//    }
//}

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
    }
}

//val sourcesJar by tasks.registering(Jar::class) {
//    archiveClassifier.set("sources")
//    from(kotlin.sourceSets.main.get().kotlin)
//}
//
//publishing {
//    publications {
//        create<MavenPublication>("maven") {
//            groupId = project.group.toString()
//            artifactId = project.name
//            version = project.version.toString()
//            from(components["kotlin"])
//            artifact(tasks["sourcesJar"])
//        }
//    }
//}
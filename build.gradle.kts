
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val projectName = Project.name
group = Project.group
version = Project.version

repositories {
    mavenCentral()
    Dependencies.mavenRepositories.forEach { r -> maven { url = uri(r) }}
}

plugins {
    Dependencies.plugins.forEach { (n, v) -> id(n) version v }
}

dependencies {
    Dependencies.dependencies.forEach { (n, v) -> implementation("$n:$v") }
    Dependencies.npmDependencies.forEach { (n, v) -> implementation(npm(n, v)) }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = Versions.java
    }
    
    create("buildAndPublish") {
        group = "publishing"
        description = "Builds and publishes the project"
        dependsOn("clean")
        dependsOn("build")
        dependsOn("preparePublish")
        dependsOn("publishAllPublicationsToSonatypeRepository", "closeAndReleaseSonatypeStagingRepository")
        dependsOn("prepareDevelopment")
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

rootProject.plugins.withType<YarnPlugin> {
    rootProject.the<YarnRootExtension>().apply {
        resolution("@webpack-cli/serve", "1.5.2")
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

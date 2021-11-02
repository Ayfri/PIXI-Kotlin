import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
}

kotlin {
    kotlinDaemonJvmArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")

    js(IR) {
        useCommonJs()
        binaries.executable()
        browser {
            commonWebpackConfig {
                outputFileName = "dist.js"
                cssSupport.enabled = true
            }
        }
    }
}

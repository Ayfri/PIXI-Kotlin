import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = Project.group
version = Project.version

repositories {
    mavenCentral()
}

plugins {
    Dependencies.plugins.forEach { (n, v) -> id(n) version v }
}

dependencies {
    Dependencies.dependencies.forEach { (n, v) -> implementation(n, v) }
    Dependencies.npmDependencies.forEach { (n, v) -> implementation(npm(n, v)) }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = Versions.java
    }
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}

val seskarVersion = "0.0.9"
val kotlinxHtmlVersion = "0.7.2"

plugins {
    kotlin("js") version "1.5.0"
    id("com.github.turansky.seskar") version "0.0.9"
}

group = "fr.ayfri"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")
    // Generation of externals doesn't work with PIXI yet, so everything is write-handed
    implementation(npm("pixi.js", "6.0.4", generateExternals = true))
    implementation("com.github.turansky.seskar:seskar-core:$seskarVersion")
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

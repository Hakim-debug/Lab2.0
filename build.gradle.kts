import org.jetbrains.kotlin.gradle.targets.js.npm.importedPackageDir
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "me.ictte"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
    implementation("com.google.code.gson:gson:2.8.6")
        testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}
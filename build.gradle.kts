import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.8.0"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.6.10"

    // Plugin for Ktlint
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"

    jacoco
    application
}

group = "me.diego"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // dependencies for logging
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    // For Streaming to XML and JSON
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
    implementation("org.codehaus.jettison:jettison:1.4.1")

    // For generating a Dokka Site from KDoc
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.6.10")
}

tasks.test {
    useJUnitPlatform()
    // report is always generated after tests run
    finalizedBy(tasks.jacocoTestReport)
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
    // for building a fat jar - include all dependencies
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
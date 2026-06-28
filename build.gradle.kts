plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.anvasy"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.anvasy.App")
}

repositories {
    mavenCentral()
}

dependencies {

    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")

    implementation(platform("com.fasterxml.jackson:jackson-bom:2.22.0"))

    // Core Jackson libraries
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

javafx {
    version = "21"

    modules = listOf(
        "javafx.controls",
        "javafx.fxml"
    )
}

tasks.test {
    useJUnitPlatform()
}
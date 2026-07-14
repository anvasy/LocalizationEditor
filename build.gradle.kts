plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.anvasy"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.anvasy.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.22.0"))
    //implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("ch.qos.logback:logback-classic:1.5.37")

    // Core Jackson libraries
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
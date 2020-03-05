import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val cdkVersion = "1.20.0"

group = "co.instil"
version = "1.0"

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC16"
    id("org.jmailen.kotlinter") version "2.1.0"
    application
    kotlin("jvm") version "1.3.50"
}

application {
    mainClassName = "co.instil.CdkLunchAndLearnAppKt"
}

dependencies {
    implementation("software.amazon.awscdk:core:$cdkVersion")
    implementation("software.amazon.awscdk:iam:$cdkVersion")
    implementation("software.amazon.awscdk:s3:$cdkVersion")
    implementation("software.amazon.awscdk:s3-notifications:$cdkVersion")
    implementation("software.amazon.awscdk:ecs:$cdkVersion")
    implementation("software.amazon.awscdk:ecs-patterns:$cdkVersion")
    implementation("software.amazon.awscdk:efs:$cdkVersion")
    implementation("software.amazon.awscdk:lambda:$cdkVersion")
    implementation("software.amazon.awscdk:lambda-destinations:$cdkVersion")
    implementation("software.amazon.awscdk:rds:$cdkVersion")
    implementation("software.amazon.awscdk:route53:$cdkVersion")
    implementation("software.amazon.awscdk:route53-targets:$cdkVersion")
    implementation("software.amazon.awscdk:certificatemanager:$cdkVersion")

    implementation(kotlin("stdlib-jdk8"))
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

detekt {
    config = files(project.rootDir.resolve("detekt-config.yml"))
}

kotlinter {
    disabledRules = arrayOf("import-ordering")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

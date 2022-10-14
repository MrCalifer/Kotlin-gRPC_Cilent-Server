plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    id("com.google.protobuf") version "0.8.15" apply false
    kotlin("jvm") version "1.5.31" apply false
}

ext["grpcVersion"] = "1.46.0"
ext["grpcKotlinVersion"] = "1.3.0" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.20.1"

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

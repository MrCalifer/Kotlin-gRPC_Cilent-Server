plugins {
//    id("java-library")
//    id("org.jetbrains.kotlin.jvm")
    application
    kotlin("jvm")
}

dependencies {
    implementation(project(":stub"))
    implementation("androidx.concurrent:concurrent-futures-ktx:1.1.0")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.5.2")
    runtimeOnly("io.grpc:grpc-netty:${rootProject.ext["grpcVersion"]}")
}

tasks.register<JavaExec>("HelloServer") {
    dependsOn("classes")
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("edu.califer.server.HelloServerKt")
}

val helloServerStartScripts = tasks.register<CreateStartScripts>("helloServerStartScripts") {
    mainClass.set("edu.califer.server.HelloServerKt")
    applicationName = "hello-server"
    outputDir = tasks.named<CreateStartScripts>("startScripts").get().outputDir
    classpath = tasks.named<CreateStartScripts>("startScripts").get().classpath
}

tasks.named("startScripts") {
    dependsOn(helloServerStartScripts)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}


//java {
//    sourceCompatibility = JavaVersion.VERSION_1_7
//    targetCompatibility = JavaVersion.VERSION_1_7
//}
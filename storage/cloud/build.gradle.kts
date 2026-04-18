import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    compileOnly(project(":didim-domain"))
    implementation(project(":didim-common"))
    runtimeOnly("com.google.cloud:spring-cloud-gcp-starter-storage:8.0.1")
    implementation("com.google.cloud:spring-cloud-gcp-storage:8.0.1")
}

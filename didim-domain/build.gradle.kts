import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":didim-common"))

    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-tx")
}

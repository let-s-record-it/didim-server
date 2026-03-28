import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    api("io.github.microutils:kotlin-logging-jvm:3.0.5")

}

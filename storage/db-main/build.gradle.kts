import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
val jar: Jar by tasks

bootJar.enabled = false
jar.enabled = true

val queryDslVersion: String by project

dependencies {
    compileOnly(project(":didim-domain"))
    implementation(project(":didim-common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-neo4j")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("io.github.openfeign.querydsl:querydsl-core:$queryDslVersion")
    implementation("io.github.openfeign.querydsl:querydsl-jpa:$queryDslVersion")
    kapt("io.github.openfeign.querydsl:querydsl-apt:$queryDslVersion:jpa")
}

// Querydsl Settings
val kaptGeneratedDir = "build/generated/source/kapt/main"

kapt {
    keepJavacAnnotationProcessors = true
    arguments {
        arg("querydsl.entityAccessors", "true")
    }
}

sourceSets {
    main {
        java.srcDirs(kaptGeneratedDir)
    }
}

tasks.named("clean") {
    doLast {
        file(kaptGeneratedDir).deleteRecursively()
    }
}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.3.11"
	kotlin("jvm") version kotlinVersion
	java
	id("org.springframework.boot") version "2.1.1.RELEASE"
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
}

apply(plugin = "io.spring.dependency-management")

repositories {
	maven(url = "http://repo.spring.io/milestone")
	jcenter()
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation("org.springframework.boot:spring-boot-starter-web")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.junit.jupiter:junit-jupiter-params")
	testImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:2.1.0.RC3")
}

group = "org.t4atf.wiremock"
version = "0.0.1-SNAPSHOT"

tasks {
	withType<JavaCompile> {
		options.encoding = "UTF-8"
	}
	withType(KotlinCompile::class.java).all {
		kotlinOptions {
			jvmTarget = "1.8"
			freeCompilerArgs = listOf("-Xjsr305=strict")
		}
	}
	withType<Test> {
		useJUnitPlatform()
	}
}

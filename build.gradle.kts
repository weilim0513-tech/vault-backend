plugins {
	java
	id("org.springframework.boot") version "4.0.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.back"
version = "0.0.1-SNAPSHOT"
description = "vault-backend"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
    // 1. [Web & Core] 웹 서버 및 검증
    implementation("org.springframework.boot:spring-boot-starter-web") // webmvc 포함
    implementation("org.springframework.boot:spring-boot-starter-validation") // @NotNull 등 검증용
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // 2. [Database] JPA & MySQL
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j:9.5.0")
    runtimeOnly("com.h2database:h2") // 테스트/로컬용

    // 3. [Redis] 캐싱 & 분산락 (트레이딩 핵심 엔진)
    // Data Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // Redisson: 선착순/동시성 제어를 위한 분산락 라이브러리
    implementation("org.redisson:redisson-spring-boot-starter:3.52.0")

    // 4. [Security & Auth] 보안 및 토큰
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    // 5. [Socket] 실시간 통신 (호가창)
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // 6. [Dev Tools] 개발 편의성
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

    // 쿼리 파라미터 로그 확인 (P6Spy)
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:2.0.0")
    // API 명세 자동화 (Swagger v3)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.0")

    // 7. [Test] 테스트
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test") // 시큐리티 테스트
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

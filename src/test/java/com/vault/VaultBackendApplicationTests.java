package com.vault;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers // 1. Testcontainers 활성화
class VaultBackendApplicationTests {

    // 2. Redis 컨테이너 정의 (Docker 이미지 지정)
    // static으로 선언해야 모든 테스트 메서드에서 공유해서 씁니다 (속도 향상)
    @Container
    static GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis:alpine"))
            .withExposedPorts(6379); // 컨테이너 내부 6379 포트를 엽니다.

    // 3. 동적 프로퍼티 주입
    // Docker가 Redis를 띄울 때 호스트의 랜덤 포트(예: 32789)에 매핑합니다.
    // 그 랜덤 포트를 알아내서 Spring 설정(RedissonConfig)에 주입해줘야 합니다.
    @DynamicPropertySource
    static void overrideRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", redisContainer::getHost);
        registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort);

        // (선택) 만약 application.yml에 비밀번호가 설정되어 있다면 여기서도 맞춰줘야 함
        // registry.add("spring.data.redis.password", ...);
    }

    @Test
    void contextLoads() {
        // 이제 가짜(Mock)가 아니라,
        // Testcontainers가 띄운 '진짜 Redis'에 연결되어 성공해야 합니다.
    }
}
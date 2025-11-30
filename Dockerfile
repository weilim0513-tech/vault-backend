# 1. 베이스 이미지: GraalVM Community Edition 21
FROM ghcr.io/graalvm/graalvm-community:21

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 Jar 파일 복사
COPY build/libs/*.jar app.jar

# 4. 실행 명령어
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
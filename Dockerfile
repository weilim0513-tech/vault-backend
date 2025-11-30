# 1. 베이스 이미지: 자바 21이 설치된 리눅스 환경을 가져옴
FROM openjdk:21-jdk-slim

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 Jar 파일 복사 (Github Actions가 빌드한 파일 -> 도커 내부로)
# libs/*.jar 경로가 중요
COPY build/libs/*.jar app.jar

# 4. 실행 명령어 (java -jar app.jar)
# 프로필은 운영(prod)으로 설정
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
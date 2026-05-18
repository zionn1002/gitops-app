# 1단계: 빌드
FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /build

# 의존성 캐시 최적화
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 소스 복사 후 빌드
COPY src ./src
RUN mvn clean package -DskipTests -B

# 2단계: 실행 이미지 (경량)
FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl
WORKDIR /app

# JAR 복사 및 권한 설정
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# 헬스체크 (Actuator 필요)
# HEALTHCHECK --interval=30s --timeout=5s --start-period=60s --retries=3 \
#  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace/app

COPY pom.xml .
COPY src src

RUN apt-get update && apt-get install -y maven --no-install-recommends && rm -rf /var/lib/apt/lists/*

RUN mvn package -DskipTests
RUN mkdir -p target/extracted && \
    java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

# Runtime stage
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

ARG EXTRACTED=/workspace/app/target/extracted
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "org.springframework.boot.loader.launch.JarLauncher"]
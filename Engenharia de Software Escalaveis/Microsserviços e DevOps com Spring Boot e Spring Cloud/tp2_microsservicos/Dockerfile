# syntax=docker/dockerfile:1.7

# ===== STAGE 1: build =====
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# ===== STAGE 2: runtime =====
FROM eclipse-temurin:21-jre
WORKDIR /app
# ajuste o nome do jar se mudar a versão
COPY --from=build /workspace/target/tp2_microsservicos-1.0.0.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

# ====== Build stage ======
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests clean package

# ====== Runtime stage ======
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080
ENTRYPOINT ["/bin/sh","-c","java $JAVA_OPTS -jar /app/app.jar"]

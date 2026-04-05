# Compile and package project
FROM maven:3.9.14-eclipse-temurin-25-alpine AS build
WORKDIR /build
# Copy only pom.xml first (before source code).
# Docker builds in layers — if pom.xml hasn't changed,
# the next RUN layer (dependency download) will be reused from cache.
COPY pom.xml .
# Download all dependencies based on pom.xml.
# This layer is cached separately from the source code,
# so if only your code changes, Maven won't re-download everything.
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run application
FROM eclipse-temurin:25-jre-alpine
WORKDIR /codechella-api
COPY --from=build /build/target/*.jar codechella-api.jar
ENTRYPOINT ["java", "-jar", "codechella-api.jar"]

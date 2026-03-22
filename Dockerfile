# Compile and package project
FROM eclipse-temurin:25-jre AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# Run application
FROM eclipse-temurin:25-jre
WORKDIR /spring-reactive-codechella
COPY --from=build /build/target/*.jar spring-reactive-codechella.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", ".jar"]
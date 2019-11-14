# syntax=docker/dockerfile:experimental

ARG OPENJDK_VERSION="12"
ARG MAVEN_VERSION="3"

FROM maven:${MAVEN_VERSION}-jdk-${OPENJDK_VERSION} as builder

COPY pom.xml .

RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src ./src

RUN mvn -Dmaven.test.skip=true package

FROM openjdk:${OPENJDK_VERSION}-jdk-alpine
EXPOSE 8080

WORKDIR /app

COPY --from=builder /target/*.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
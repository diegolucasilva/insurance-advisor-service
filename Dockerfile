FROM gradle:7.5.0-jdk11-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/insurance-advisor-service-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "insurance-advisor-service-0.0.1-SNAPSHOT.jar"]
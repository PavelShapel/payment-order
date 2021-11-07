FROM openjdk:8-jdk-alpine as builder
WORKDIR application
COPY target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="pavel.shapel@gmail.com"
LABEL SERVICE="kafka-courier"
RUN apk update && apk upgrade && apk add bash
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
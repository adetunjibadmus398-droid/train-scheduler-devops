FROM openjdk:17

WORKDIR /app

COPY target/train-scheduler.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]

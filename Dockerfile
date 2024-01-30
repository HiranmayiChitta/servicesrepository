FROM openjdk:17-alpine
RUN echo HIRANMAYI1
EXPOSE 8080
RUN echo HIRANMAYI2
ADD target/springdemo.jar springdemo.jar
RUN echo HIRANMAYI3
ENTRYPOINT ["java", "-jar", "springdemo.jar"]
RUN echo HIRANMAYI4
FROM openjdk:8
WORKDIR /usr/local/spring-cloud-config-service/
COPY spring-cloud-config-service.jar /usr/local/spring-cloud-config-service/
EXPOSE 80
ENTRYPOINT ["java","-jar","spring-cloud-config-service.jar"]
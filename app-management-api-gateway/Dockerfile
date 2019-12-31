FROM openjdk:8
WORKDIR /usr/local/spring-cloud-getaway-service/
COPY spring-cloud-config-getaway.jar /usr/local/spring-cloud-getaway-service/
EXPOSE 80
ENTRYPOINT ["java","-jar","spring-cloud-config-getaway.jar"]
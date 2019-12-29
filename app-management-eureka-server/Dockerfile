FROM openjdk:7
WORKDIR /usr/local/eureka/
COPY ./target/application-eureka.jar /usr/local/eureka/
EXPOSE 80
ENTRYPOINT ["java","-jar","application-eureka.jar"]
FROM openjdk:11.0-jdk
WORKDIR /usr/local/eureka/
COPY eureka.jar /usr/local/eureka/
EXPOSE 80
ENTRYPOINT [ "nohup","java","-jar","eureka.jar","&" ]
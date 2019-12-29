#！/bin/bash
mvn clean package -P dev
cp target/*.jar /usr/local/cicd/eureka.jar
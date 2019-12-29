#！/bin/bash
mvn clean package -P pro
cp target/*.jar /usr/local/spring-cloud/eureka.jar
# shellcheck disable=SC2164
cd /usr/local/spring-cloud/
cp Dockerfile .
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v1 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v1

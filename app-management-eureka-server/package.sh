#！/bin/bash
mvn clean package -P prod
cp target/*.jar /usr/local/spring-cloud/build/eureka.jar
cp Dockerfile /usr/local/spring-cloud/build/Dockerfile
# shellcheck disable=SC2164
cd /usr/local/spring-cloud/build/
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2

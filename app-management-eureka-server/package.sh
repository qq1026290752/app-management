#！/bin/bash
mvn clean package -P pro
cp target/*.jar /usr/local/spring-cloud/build/eureka.jar
cp Dockerfile /usr/local/spring-cloud/build/
# shellcheck disable=SC2164
cd /usr/local/spring-cloud/build/
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v1
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v1 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v1

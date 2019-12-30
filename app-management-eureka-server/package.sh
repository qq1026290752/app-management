#！/bin/bash
mvn clean package -P prod
var path = usr/local/spring-cloud/build/eureka/
if [ ! -d  "$path" ]; then
  mkdir  "$path"
fi
cp target/*.jar "${path}"eureka.jar
cp Dockerfile "${path}"Dockerfile
cp application-eureka.yaml "${path}"application-eureka.yaml
# shellcheck disable=SC2164
cd /usr/local/spring-cloud/build/
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2

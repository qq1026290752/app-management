#！/bin/bash
mvn clean package -P prod
cp target/*.jar /usr/local/spring-cloud/build/spring-cloud-config-service.jar
cp Dockerfile /usr/local/spring-cloud/build/Dockerfile
# shellcheck disable=SC2164
cd /usr/local/spring-cloud/build/
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2
kubectl delete -f spring-cloud-config-service.yaml
kubectl apply -f spring-cloud-config-service.yaml

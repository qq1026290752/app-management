#！/bin/bash
mvn clean package -P prod
path='/usr/local/spring-cloud/build/config-service/'
if [ ! -d  ${path} ]; then
   mkdir  ${path}
fi
rm -rf ${path}*
cp target/*.jar ${path}spring-cloud-config-service.jar
cp Dockerfile ${path}Dockerfile
cp spring-cloud-config-service.yaml ${path}spring-cloud-config-service.yaml
# shellcheck disable=SC2164
cd ${path}
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/spring-cloud-config-service:v2
kubectl delete -f spring-cloud-config-service.yaml
kubectl apply -f spring-cloud-config-service.yaml

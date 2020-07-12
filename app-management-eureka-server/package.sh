#！/bin/bash
mvn clean package -P prod
path='/usr/local/spring-cloud/build/eureka/'
if [ ! -d  ${path} ]; then
   mkdir  ${path}
fi
rm -rf ${path}*

cp target/*.jar ${path}application-eureka.jar
cp Dockerfile ${path}Dockerfile
cp application-eureka.yaml ${path}application-eureka.yaml
# shellcheck disable=SC2164
cd ${path}
docker rmi registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2
docker build -t registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2 .
docker push registry.cn-beijing.aliyuncs.com/application-spring-cloud/eureka:v2
kubectl delete -f application-eureka.yaml
kubectl create -f application-eureka.yaml

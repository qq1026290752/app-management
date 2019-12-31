#！/bin/bash
mvn clean package -P prod
path='/usr/local/spring-cloud/build/getaway-service/'
FILE_NAME='spring-cloud-config-getaway'
K8S_CONFIG_NAME='spring-cloud-config-gateway.yaml'
DOCKER_REG_SERVICE='registry.cn-beijing.aliyuncs.com/application-spring-cloud/'
if [ ! -d  ${path} ]; then
   mkdir  ${path}
fi
rm -rf ${path}*
cp target/*.jar ${path}${FILE_NAME}.jar
cp Dockerfile ${path}Dockerfile
cp ${K8S_CONFIG_NAME} ${path}${K8S_CONFIG_NAME}
# shellcheck disable=SC2164
cd ${path}
docker rmi ${DOCKER_REG_SERVICE}${FILE_NAME}:v2
docker build -t ${DOCKER_REG_SERVICE}${FILE_NAME}:v2 .
docker push ${DOCKER_REG_SERVICE}${FILE_NAME}:v2
kubectl delete -f ${K8S_CONFIG_NAME}
kubectl create -f ${K8S_CONFIG_NAME}

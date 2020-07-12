## spring-cloud课程管理系统

> 本工程用eureka server作为项目的注册服务中心,eureka client作为服务端，zuul组件作为网关，后期可能替换为API-GETWAY。本项目是一个开源的在线教育后台管理系统,采用前后端分离模式。

### 项目组成
- `app-management-comments` 主要是异步上传,邮件服务,短信服务,考虑到学习为目的该服务与其他服务调用可能采用MQ来实现功能
- `app-management-eureka-server` spring eureka服务器,如果考虑高可用可以采用两台服务器互相注册完成
- `app-management-config-server` spring config 管理服务器
- `app-management-zuul`服务网管模块  
- `app-management-user` 管理员用户管理模块
- `app-management-category` 类目管理相关服务放在这里
- `app-management-consumer` 用户/消费者管理模块
- `app-management-course` 课程模块
- `app-management-order` 课程订单管理模块
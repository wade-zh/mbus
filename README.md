# MBUS
基于机器学习Caffe框架，配合RabbitMQ消息队列技术实现图像验证码识别平台

验证码识别服务竞争消费模式，支持集群部署以支撑大流量服务；

经过观察，目前市场上的小平台基本都是这种模式，再优化也是拆分服务，做限流一类的


# 停止演示 #
> 由于服务器到期，之前部署在139服务器用于演示的站点，现在停止服务，接口无法继续使用


自动化编译安装教程请查看：**install/使用教程.md**


通过```xxx/api/uploadAsync```上传验证码

然后轮询```xxx/api/getResult```获取验证码

以上操作在mbus-client\js中有调用实例

mbus-client 多个语言的调用的demo

mbus-common 项目公用代码

mbus-docs 网络与模型文件

mbus-model 项目公用实体类

mbus-site 项目网站代码  

mbus-worker 使用易语言写的调用caffe模型的服务端

该项目完成的非常仓促，很多地方细节都没有注意，最初只是为了学习CC如何调用，继而拓展出了这个项目  

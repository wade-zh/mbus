# MBUS
基于RabbitMQ简单实现验证码识别平台


# 停止演示站点通知 #
> 请各位下载安装部署服务，我提供的测试站点即日起关停，没办法，大流量影响到了我其他程序的作业，谢谢关注，如遇到安装上的问题可联系我QQ2175656094，仅提供支持，不代安装！

**安装说明：**  
1、部署网站需要安装JRE 64位运行环境  
2、部署验证码识别服务端需要安装JRE 32位运行环境  
3、需要额外安装Redis以及RabbitMQ服务  


> GitHub: [https://github.com/wade-zh/mbus  ](https://github.com/wade-zh/mbus   "https://github.com/wade-zh/mbus  ")

通过```xxx/api/uploadAsync```上传验证码

mbus-client 多个语言的调用的demo

mbus-common 项目公用代码

mbus-docs 网络与模型文件

mbus-model 项目公用实体类

mbus-site 项目网站代码  

mbus-worker 使用易语言写的调用caffe模型的服务端


## site项目打包  
    mvn clean package

## worker项目打包  
	mvn clean package
	

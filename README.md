# MBUS
基于RabbitMQ简单实现验证码识别平台


机器学习, 目前支持识别英数图像验证码类型, 建议先下载Demo测试识别率  
这是一个公益性的免费项目，您可以不需经过权限认证即可快速接入SDK  



> GitHub: [https://github.com/wade-zh/mbus  ](https://github.com/wade-zh/mbus   "https://github.com/wade-zh/mbus  ")



> v1.1.0-beta: [https://github.com/wade-zh/mbus/releases/download/v1.1/mbus-beta.zip](https://github.com/wade-zh/mbus/releases/download/v1.1/mbus-beta.zip "https://github.com/wade-zh/mbus/releases/download/v1.1/mbus-beta.zip")   



> v1.0.0-beta: [https://github.com/wade-zh/mbus/releases/download/v1.0/mbus-beta.zip  ](https://github.com/wade-zh/mbus/releases/download/v1.0/mbus-beta.zip   "https://github.com/wade-zh/mbus/releases/download/v1.0/mbus-beta.zip  ")

为了快速的实现识别多种类型的验证码，我借用了不同作者训练好的神经网络模型，每一份文件目录下我都注明了是哪个作者的作品，如果您不同意请联系我删除！  

由于我个人时间不是太充足，有心要整理出来一个能够基本覆盖互联网验证码类型的模型，英数、汉字、行为、答题等等，但是这个进度可能会很慢，如果您有兴趣，欢迎您fork！  

识别成功返回内容(JSON)：
        {
            "error": 0,
            "msg": "xxx"
        }
    
识别识别返回内容(JSON)：
        {
            "error": 1,
            "msg": "Error"
        }
    
上传答题：http://139.199.211.96:8090/api/upload
### Java OkHttp Demo：  
``` java
	OkHttpClient client = new OkHttpClient();
	
	MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
	RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"file\"; filename=\"[object Object]\"\r\nContent-Type: false\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"type\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
	Request request = new Request.Builder()
	.url("http://139.199.211.96:8090/api/upload")
	.post(body)
	.addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
	.addHeader("Content-Type", "application/x-www-form-urlencoded")
	.addHeader("Cache-Control", "no-cache")
	.build();
	
	Response response = client.newCall(request).execute();
```
    
### C# RestSharp Demo：  
``` c#
	var client = new RestClient("http://139.199.211.96:8090/api/upload");
    var request = new RestRequest(Method.POST);
    request.AddHeader("Cache-Control", "no-cache");
    request.AddHeader("Content-Type", "application/x-www-form-urlencoded");
    request.AddHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
    request.AddParameter("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW", "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"file\"; filename=\"[object Object]\"\r\nContent-Type: false\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"type\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--", ParameterType.RequestBody);
    IRestResponse response = client.Execute(request);
```
    
### 易语言 Demo：  
```
.版本 2

    .局部变量 header, 文本型
    .局部变量 url, 文本型
    .局部变量 api, 文本型
    .局部变量 bytes, 字节集
    .局部变量 response, 文本型
    .局部变量 data, 字节集

    url ＝ “http://验证码地址”
    api ＝ “http://139.199.211.96:8090/api/upload”
    bytes ＝ 网页_访问_对象 (url, 0)
    header ＝ #常量1
    header ＝ 文本_替换 (header, , , , “{type}”, 到文本 (#缺省类型))
    data ＝ 子字节集替换 (到字节集 (header), 到字节集 (“{data}”), bytes, , )
    response ＝ 编码_Utf8到Ansi (网页_访问_对象 (api, 1, , , , #常量2, , , , data))
```
        


###计划  
1、学习TensorFlow API  
2、识别验证码将使用训练好的神经模型  
3、这是一个长期计划，要将各个类型验证码识别率训练到90%以上



## site项目打包  
    mvn clean package

## worker项目打包  
	mvn clean package
	

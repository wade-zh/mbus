using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;

namespace SendMessageDesk
{
    public class Http
    {
        public static Stream GetStream(string url,out string refcookies)
        {

            HttpWebResponse httpWebResponse = null;
            HttpWebRequest httpWebRequest;

            httpWebRequest = WebRequest.Create(url) as HttpWebRequest;
            
            httpWebRequest.Method = "GET";
            httpWebRequest.Accept = "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*";
            httpWebRequest.Headers["Accept-Charset"] = "GBK,utf-8;q=0.7,*;q=0.3";
            httpWebRequest.Headers["Accept-Language"] = "zh-CN,zh;q=0.8";
            httpWebRequest.Headers["Cache-Control"] = "max-age=0";
            httpWebRequest.ContentType = "application/x-www-form-urlencoded";

            httpWebRequest.Referer = url;
            httpWebRequest.UserAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.56 Safari/536.5";
            httpWebRequest.Proxy = null;
            httpWebRequest.AllowAutoRedirect = true;

            httpWebRequest.MaximumAutomaticRedirections = 4;
            httpWebRequest.MaximumResponseHeadersLength = 4;
            httpWebRequest.Credentials = CredentialCache.DefaultCredentials;
            //httpWebRequest.Timeout = 20000;

            httpWebResponse = (HttpWebResponse)httpWebRequest.GetResponse();



            refcookies = "";
            foreach (Cookie ck in httpWebResponse.Cookies)
            {
                refcookies += ck.Name + "=" + ck.Value + "; ";
            }

            try
            {
                using (Stream inStream = httpWebResponse.GetResponseStream()) //从WEB请求创建流（读）  
                {
                    MemoryStream outStream = new MemoryStream();
                    byte[] buffer = new byte[1024];
                    int size = 0;
                    while ((size = inStream.Read(buffer, 0, (int)buffer.Length)) > 0)
                    {
                        outStream.Write(buffer, 0, size);
                    }
                    return outStream;
                }
            }
            catch
            {
                if (httpWebResponse != null)
                {
                    httpWebResponse.Close();
                    httpWebResponse = null;
                }
                return null;
                //throw;
            }
            finally
            {
                httpWebRequest = null;
            }

        }
    }
}

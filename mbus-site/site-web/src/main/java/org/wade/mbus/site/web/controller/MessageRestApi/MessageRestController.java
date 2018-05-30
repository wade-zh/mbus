package org.wade.mbus.site.web.controller.MessageRestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wade.mbus.model.CallMsgReq;
import org.wade.mbus.site.model.HttpResp;
import org.wade.mbus.site.service.IMessageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MessageRestController {
    @Autowired
    private IMessageService messageService;

    @RequestMapping(value = "/upload")
    public HttpResp upload(@RequestParam("file") MultipartFile file,
                           @RequestParam("type") Integer type, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        if (!file.isEmpty()) {
            try {
                HttpResp resp = messageService.upload(file.getBytes(), type);
                return resp;
            } catch (Exception e) {
                System.out.println(e);
                return new HttpResp(0,"error");
            }
        }
        return new HttpResp(0,null);
    }


    @RequestMapping(value = "/uploadAsync")
    public HttpResp uploadAsync(@RequestParam("file") MultipartFile file,
                           @RequestParam("type") Integer type, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        if (!file.isEmpty()) {
            try {
                HttpResp resp = messageService.uploadAsync(file.getBytes(), type);
                return resp;
            } catch (Exception e) {
                System.out.println(e);
                return new HttpResp(0,"error");
            }
        }
        return new HttpResp(0,null);
    }

    @RequestMapping(value = "/getResult")
    public HttpResp getResult(String ticket){
        try {
            HttpResp resp = messageService.getResult(ticket);
            return resp;
        } catch (Exception e) {
            System.out.println(e);
            return new HttpResp(0,"error");
        }
    }

    @RequestMapping(value = "/update")
    public HttpResp update(CallMsgReq msg){
        return messageService.update(msg);
    }


    @RequestMapping(value = "/getServerCount")
     public HttpResp getServerCount() {
        return messageService.getServerCount();
     }
}

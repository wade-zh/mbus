package org.wade.mbus.site.web.controller.MessageRestApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
                return messageService.upload(file.getBytes(), type);
            } catch (IOException e) {
                return new HttpResp(0,"Error for uoload");
            }
        }
        return new HttpResp(0,null);
    }
}

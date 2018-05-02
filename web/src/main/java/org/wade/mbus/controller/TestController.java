package org.wade.mbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wade.mbus.model.StandardRespMsg;
import org.wade.mbus.service.IMessageService;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private IMessageService messageService;

    @RequestMapping("/insert/{number}")
    public boolean upload(@PathVariable Integer number){
        return messageService.upload(number);
    }

    @RequestMapping("/get/{number}")
    public boolean isEven(@PathVariable Integer number){
        return messageService.get(number);
    }
}

package org.wade.mbus.site.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index", "/default", "index.html"})
    public String index(){
        return "index";
    }
}

package org.wade.mbus.site.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("org.wade")
public class MbusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbusApplication.class, args);
    }
}
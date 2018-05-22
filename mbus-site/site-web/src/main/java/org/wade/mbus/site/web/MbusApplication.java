package org.wade.mbus.site.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication()
@ComponentScan("org.wade")
@EnableAsync
public class MbusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbusApplication.class, args);
    }
}
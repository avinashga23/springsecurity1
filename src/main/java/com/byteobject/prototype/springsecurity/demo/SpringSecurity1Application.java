package com.byteobject.prototype.springsecurity.demo;

import com.byteobject.prototype.springsecurity.demo.security.ApplicationSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages = "com.byteobject.prototype.springsecurity",
        scanBasePackageClasses = {ApplicationSecurityConfiguration.class})
@ImportResource("classpath:applicationContext.xml")
public class SpringSecurity1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity1Application.class, args);
    }

}

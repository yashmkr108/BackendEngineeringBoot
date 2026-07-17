package com.yash.user_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserApplication.class, args);
        System.out.println(context);
        // AnnotationConfigServletWebServerApplicationContext → the actual runtime class of the Spring application context. Since this is a web app, Spring Boot created a context capable of managing the embedded web server and your beans.
        // @3e44f2a5 → the object's hash-code representation in hexadecimal. It is not a memory address.
        // started on ... → when this application context was started.


        // To inspect each bean's actual runtime class:
//        for (String beanName : context.getBeanDefinitionNames()) {
//            Object bean = context.getBean(beanName);
//
//            System.out.println(
//                    beanName + " -> " + bean.getClass().getName()
//            );
//        }
    }

}

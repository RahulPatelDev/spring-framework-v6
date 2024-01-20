package com.rahulpateldev.springframeworkv6.helloworld;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class HelloWorldApp {

    @Bean
    public String helloWorld() {
        return "Hello World";
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(HelloWorldApp.class)) {
            String text = context.getBean("helloWorld", String.class);
            System.out.println("Bean value: " + text);
        }
    }

}

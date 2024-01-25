package com.rspatel031.springframeworkv6.sampleapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.rspatel031.springframeworkv6.sampleapp")
public class ShapeApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ShapeApp.class)) {
            var shapeRunner = context.getBean(ShapeRunner.class);
            shapeRunner.run();
        }
    }
}

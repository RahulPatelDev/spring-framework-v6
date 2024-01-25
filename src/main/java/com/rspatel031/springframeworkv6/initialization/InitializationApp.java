package com.rspatel031.springframeworkv6.initialization;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Component
class EagerInitialization {

    public EagerInitialization() {
        System.out.println("EagerInitialization Class Initialized!!");
    }

    @Bean
    public String sayHello() {
        return "Hello from EagerInitialization Class!!";
    }
}

@Lazy
@Component
class LazyInitialization {

    public LazyInitialization() {
        System.out.println("LazyInitialization Class Initialized!!");
    }

    @Bean
    public String sayHello() {
        System.out.println("Hello from LazyInitialization Class!!");
        return "Hello from LazyInitialization Class!!";
    }
}

@Configuration
@ComponentScan
public class InitializationApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(InitializationApp.class)) {
            System.out.println("Application Initialization has been completed!!");

            var eager = context.getBean(EagerInitialization.class);
            System.out.println("Calling sayHello form EagerInitialization Class: " + eager.sayHello());

            // LazyInitialization class instantiate at this step.
            // To validate this please comment below 2 lines.
            var lazy = context.getBean(LazyInitialization.class);
            System.out.println("Calling sayHello form LazyInitialization Class: " + lazy.sayHello());
        }
    }
}

# 👋 Initialization of Spring Beans

- Default initialization for Spring Beans: Eager
- Eager initialization is recommended:
    - Errors in the configuration are discovered immediately at application startup
- We you can configure beans to be lazily initialized using Lazy annotation.
    - Not recommended and not frequently used by developers.

## ✍️ Example

```java
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
            // To validate this, please comment below 2 lines.
            var lazy = context.getBean(LazyInitialization.class);
            System.out.println("Calling sayHello form LazyInitialization Class: " + lazy.sayHello());
        }
    }
}
```
- Lazy annotation:
    - It is used everywhere along with **@Configuration**, **@Component** and **@Bean**.
- Lazy resolution proxy object will be injected instead of original dependency.

| Annotations      | Description                                                                                                                                                            | 
|:-----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Configuration` | Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions                                    |
| `@ComponentScan` | Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation |
| `@Component`     | An instance of class will be managed by Spring framework                                                                                                               |
| `@Bean`          | Indicates that a method produces a bean to be managed by the Spring container                                                                                          |
| `@Lazy`          | Indicates that a bean has to be lazily initialized. Absence of @Lazy annotation will lead to eager initialization.                                                     |


## ⇋ Difference Eager vs. Lazy Initialization

| Points         | Eager Initialization                          | Lazy Initialization                             |
|:---------------|:----------------------------------------------|:------------------------------------------------|
| Initialization | When application gets started                 | When it is first made use of in the application |
| How to use     | Without using `@Lazy` or `@Lazy(value=false)` | Using `@Lazy` or `@Lazy(value=true)` Annotation |
| Default        | Enabled                                       | Disabled                                        |
| Usage          | Frequently                                    | Rarely based on need                            |
| Memory Usage   | All beans are initialized at startup          | Less                                            |    

## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com

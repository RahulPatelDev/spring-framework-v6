# 👋 Hello World App

Basic example of simple Hello World App using Spring Framework.
## ✍️ Example

```java 
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
```
- Here, we are using **AnnotationConfigApplicationContext** class to create Spring Context / Spring IoC Container.
- **@Bean** is used to create a Bean / Object in Spring Container, and it is totally managed by Spring Container itself.
- **context.getBean("helloWorld", String.class)** will return the value of helloWorld bean, and type is determined by the second argument, i.e. This bean will return the value of type String.

| Class, Methods & Annotations         | Description                                                                                                                                                                                                                        |
|:-------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `AnnotationConfigApplicationContext` | This class allows us to create Spring Context / Container without XML Configuration. You can instantiate an AnnotationConfigApplicationContext by using a no-arg constructor and then configure it by using the register() method. |
| `register()`                         | Register ApplicationContext using this method                                                                                                                                                                                      |
| `getBean()`                          | This method will return the beans from the Spring Context                                                                                                                                                                          |
| `@Bean`                              | The @Bean annotation is a method-level annotation in the Spring Framework. A bean is an object that is instantiated, assembled, and managed by the Spring IoC container.                                                           |

## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com
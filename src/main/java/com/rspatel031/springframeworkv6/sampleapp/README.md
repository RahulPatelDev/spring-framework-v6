# 👋 A Sample Spring Application

- A demonstration of a spring application.
## ✍️ Example

#### Shape.java
```java
public interface Shape {
    void findArea();
}
```
- Shape interface has only one method i.e. **findArea()** method.

#### Circle.java
```java
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Circle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Circle!!");
    }
}

```
- Circle class implements Shape interface and findArea() method implementation is defined in the same class.
- It only prints the message i.e. **"Calculating Area of Circle!!"**.

#### Rectangle.java
```java
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Rectangle")
public class Rectangle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Rectangle!!");
    }
}

```
- Rectangle class implements Shape interface and findArea() method implementation is defined in the same class.
- It only prints the message i.e. **"Calculating Area of Rectangle!!"**.

#### Triangle.java
```java
import org.springframework.stereotype.Component;

@Component
public class Triangle implements Shape {
    @Override
    public void findArea() {
        System.out.println("Calculating Area of Triangle!!");
    }
}

```
- Triangle class implements Shape interface and findArea() method implementation is defined in the same class.
- It only prints the message i.e. **"Calculating Area of Triangle!!"**.

#### ShapeRunner.java
```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ShapeRunner {

    private final Shape shape;

    public ShapeRunner(@Qualifier("Rectangle") Shape shape) {
        this.shape = shape;
    }

    public void run() {
        System.out.println("Running Shape: " + shape);
        shape.findArea();
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("******************* Starting Calculation *******************");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("******************* Calculation Completed ******************");
    }


}

```

| Annotations      | Description                                                                                                                                                                                                      | 
|:-----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@PostConstruct` | Identifies the method that will be executed after dependency injection is done to perform any initialization                                                                                                     |
| `@PreDestroy`    | Identifies the method that will receive the callback notification to signal that the instance is in the process of being removed by the container. Typically used to release resources that it has been holding. |

- With the help of Constructor Injection, Spring will instantiate an object of implementations of Shape interface.

#### ShapeApp.java
```java
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.rahulpateldev.springframeworkv6.sampleapp")
public class ShapeApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ShapeApp.class)) {
            var shapeRunner = context.getBean(ShapeRunner.class);
            shapeRunner.run();
        }
    }
}


```
- The main method will start the Spring Application and get the Bean of ShapeRunner class. shapeRunner object can call run method.
- **run()** will be called based on the implementation (Could be Circle, Triangle or Rectangle) of Shape interface.

| Annotations      | Description                                                                                                                                                            | 
|:-----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Configuration` | Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions                                    |
| `@ComponentScan` | Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation |
| `@Component`     | An instance of class will be managed by Spring framework                                                                                                               |
| `@Bean `         | Indicates that a method produces a bean to be managed by the Spring container                                                                                          |
| `@Primary`       | Indicates that a bean should be given preference when multiple candidates are qualified to autowire a single valued dependency                                         |
| `@Qualifier`     | Used on a field or parameter as a qualifier for candidate beans when autowiring                                                                                        |


## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com
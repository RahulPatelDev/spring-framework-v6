# 👋 Dependency Injection Types and Auto Wiring

- **Constructor Injection**: Dependencies are set by creating the Bean using its Constructor
- **Setter Injection**: Dependencies are set by calling setter methods on your beans
- **Field**: No setter or constructor. Dependency is injected using reflection.

#### Note: The Spring teams recommend Constructor-based injection as dependencies are automatically set when an object is created!
## ✍️ Example

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

record Student(int id, String name, int age) { 
}

@Component
class DataSource {

    public List<Student> getStudentData() {
        return Arrays.asList(
                new Student(1, "Karan", 12),
                new Student(2, "Lucky", 8),
                new Student(2, "Ravi", 18)
        );
    }
}

@Component
class FieldInjection {

    // Field Injection
    @Autowired
    private DataSource dataSource;

    public void iterateOverData() {
        System.out.println("Calling iterateOverData() from FieldInjection Class!!");
        this.dataSource.getStudentData().forEach(System.out::println);
    }

}

@Component
class ConstructorInjection {

    private final DataSource dataSource;

    // Constructor Injection
    public ConstructorInjection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void iterateOverData() {
        System.out.println("Calling iterateOverData() from ConstructorInjection Class!!");
        this.dataSource.getStudentData().forEach(System.out::println);
    }

}

@Component
class SetterInjection {
    private DataSource dataSource;

    // Setter Injection
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void iterateOverData() {
        System.out.println("Calling iterateOverData() from SetterInjection Class!!");
        this.dataSource.getStudentData().forEach(System.out::println);
    }
}


@Configuration
@ComponentScan
public class InjectionApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(InjectionApp.class)) {
            var fieldInjection = context.getBean(FieldInjection.class);
            fieldInjection.iterateOverData();

            var constructorInjection = context.getBean(ConstructorInjection.class);
            constructorInjection.iterateOverData();

            var setterInjection = context.getBean(SetterInjection.class);
            setterInjection.iterateOverData();
        }
    }
}

```

| Annotations      | Description                                                                                                                                                                                | 
|:-----------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Configuration` | Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions                                                        |
| `@ComponentScan` | Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation                     |
| `@Component`     | An instance of class will be managed by Spring framework                                                                                                                                   |
| `@Bean`          | Indicates that a method produces a bean to be managed by the Spring container                                                                                                              |
| `@Autowired`     | The @Autowired annotation is used for automatic dependency injection in Spring. It is used to inject the required dependencies into a bean, eliminating the need for manual configuration. |

## ↺ Auto Wiring in Spring
- When dependency needs to be auto wired by **@Autowired** Annotation, Sprint Context will look for matches / candidate by name or type. Below are the possible results:
    - **Negative Match**: Spring will be thrown an Exception
    - **Single Match**: Autowiring successful
    - **Multiple Match**: Spring will be thrown an Exception, to avoid this exception use below annotation:
        - **@Primary**: If only one of the candidates is marked @Primary, it becomes the auto-wired value
        - **@Qualifier**: Provides more specific control Can be used on class, member variables and method parameters
            - Ex: @Qualifier("nameOfQualifier")

## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com
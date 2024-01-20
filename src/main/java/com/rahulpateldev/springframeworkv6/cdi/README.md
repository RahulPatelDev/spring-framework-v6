
# CDI (Contexts and Dependency Injection)

CDI (Contexts and Dependency Injection) is a standard dependency injection framework included in Java EE 6 and higher.

Spring offers support for JSR-330 standard annotations (Dependency Injection). Those annotations are scanned in the same way as the Spring annotations. To use them, you need to have the relevant jars in your classpath.
## 🔄 Update pom.xml file

Add below dependency in your pom.xml file before running the program.

```xml
<dependency>
    <groupId>jakarta.inject</groupId>
    <artifactId>jakarta.inject-api</artifactId>
    <version>2.0.0</version>
</dependency>
```

## 🔍 Dependency Injection with @Inject and @Named

#### Instead of @Autowired, you can use @jakarta.inject.Inject as follows:

```java
@Named
class Service {

    private DataSource dataSource;

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

}
```

- As with **@Autowired**, you can use **@Inject** at the field level, method level, and constructor-argument level.

#### Instead of @Component, you can use @jakarta.inject.Named, as the following example shows:

```java
@Named
class DataSource {

    public List<Employee> data() {
        return Arrays.asList(
                new Employee(1, "Rahul", true),
                new Employee(1, "Sahil", false)
        );
    }

}
```

- It is widespread to use **@Component** without specifying a name for the component. **@Named** can be used in a similar fashion.
- When you use **@Named**, you can use component scanning in the exact same way as when you use Spring annotations


## 🛑 Limitations

- When you work with standard annotations, there are some significant features are not available. Please refer below table.

| Spring                | jakarta.Inject.*      | Description                                                                                                                                                                                                                                                                                                                                      |
|:----------------------|:----------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Autowired`          | `@Inject`             | @Inject has no 'required' attribute. Can be used with Java 8’s Optional instead.                                                                                                                                                                                                                                                                 |
| `@Component`          | `@Named`              | JSR-330 does not provide a composable model, only a way to identify named components.                                                                                                                                                                                                                                                            |
| `@Scope("singleton")` | `@Singleton`          | The JSR-330 default scope is like Spring’s prototype. However, in order to keep it consistent with Spring’s general defaults, a JSR-330 bean declared in the Spring container is a singleton by default. In order to use a scope other than singleton, you should use Spring’s @Scope annotation. jakarta.inject also provides a jakarta.inject. |
| `@Qualifier`          | `@Qualifier / @Named` | jakarta.inject.Qualifier is just a meta-annotation for building custom qualifiers. Concrete String qualifiers (like Spring’s @Qualifier with a value) can be associated through jakarta.inject.Named.                                                                                                                                            |
| `@Value`              | `-`                   | Not available                                                                                                                                                                                                                                                                                                                                    |
| `@Lazy`               | `-`                   | Not Available                                                                                                                                                                                                                                                                                                                                    |

## ✍️ Example

```java
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

record Employee(int id, String name, boolean married) {
}

@Named
class DataSource {

    public List<Employee> data() {
        return Arrays.asList(
                new Employee(1, "Rahul", true),
                new Employee(1, "Sahil", false)
        );
    }

}

@Named
class Service {

    private DataSource dataSource;

    @Inject
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

}

@Configuration
@ComponentScan
public class CDIApp {
    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(CDIApp.class)) {
            Arrays.stream(context.getBeanDefinitionNames())
                    .forEach(System.out::println);
            Service data = context.getBean(Service.class);
            System.out.println(data.getDataSource().data());
        }
    }
}
```


## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com

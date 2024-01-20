# 👋 Spring Bean Scopes

- Spring Beans are defined to be used in a specific scope:
    - **Singleton**: One object instance per Spring IoC container
    - **Prototype**: Possibly many object instances per Spring IoC container
    - Below Scopes are applicable if Spring ApplicationContext is configured for web application:
        - **Request**: One object instance per single HTTP request
        - **Session**: One object instance per-user HTTP Session
        - **Application**: One object instance per web application runtime
        - **Websocket**: One object instance per WebSocket instance
## ✍️ Example

```java
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
//Above Line is Optional, As Spring creates Objects as Singleton only!!
class Singleton {

}

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Prototype {

}


@Configuration
@ComponentScan
public class ScopeApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ScopeApp.class)) {
            System.out.println("Singleton Objects");
            System.out.println(context.getBean(Singleton.class));
            System.out.println(context.getBean(Singleton.class));
            System.out.println(context.getBean(Singleton.class));
            System.out.println(context.getBean(Singleton.class));

            System.out.println("Prototype Objects");
            System.out.println(context.getBean(Prototype.class));
            System.out.println(context.getBean(Prototype.class));
            System.out.println(context.getBean(Prototype.class));
            System.out.println(context.getBean(Prototype.class));
        }
    }
}

```
- **Singleton**: Will always return the same object from Spring Container. Need to pass
    - Set **ConfigurableBeanFactory.SCOPE_SINGLETON** as value in **@Scope** annotation.
- **Prototype**: Will always instantiate return a new object from Spring Container.
    - Set **ConfigurableBeanFactory.SCOPE_PROTOTYPE** as value in **@Scope** annotation.

| Annotations      | Description                                                                                                                                                            | 
|:-----------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Configuration` | Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions                                    |
| `@ComponentScan` | Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation |
| `@Component`     | An instance of class will be managed by Spring framework                                                                                                               |
| `@Scope`         | The @Scope annotation in Spring is used to specify the lifecycle of a Spring bean.                                                                                     |

## ⇋ Difference Prototype vs. Lazy Singleton

| Points               | Prototype                                             | Singleton                                               |
|:---------------------|:------------------------------------------------------|:--------------------------------------------------------|
| Instances            | Many per Spring Container                             | Single per Spring Container                             |
| Beans                | New bean instance is created                          | Same bean instance reused                               |
| Default              | No                                                    | Yes                                                     |
| Code Snippet         | @Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE) | @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON) |
| Usage                | Rare                                                  | Very frequently                                         |
| Recommended Scenario | Stateful beans                                        | Stateless beans                                         |     

## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com
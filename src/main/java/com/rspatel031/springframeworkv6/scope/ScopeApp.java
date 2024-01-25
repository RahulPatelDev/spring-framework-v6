package com.rspatel031.springframeworkv6.scope;


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

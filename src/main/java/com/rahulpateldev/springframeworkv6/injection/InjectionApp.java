package com.rahulpateldev.springframeworkv6.injection;

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

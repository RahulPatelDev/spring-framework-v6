package com.rahulpateldev.springframeworkv6.cdi;

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

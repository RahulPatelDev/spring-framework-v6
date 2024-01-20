package com.rahulpateldev.springframeworkv6.accessingbean;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

record Address(String city, String state) {
}

record Person(String firstName, String lastName, int age, Address address) {
}

@Configuration
class Name {

    @Bean
    public String firstName() {
        return "Rahul";
    }

    @Bean
    public String lastName() {
        return "Patel";
    }

    @Bean
    public int age() {
        return 25;
    }

    @Bean
    public String fullName(String firstName) {
        return firstName + " " + this.lastName();
    }

    @Bean
    @Primary
    public Address primaryAddress() {
        return new Address("Surat", "Guj");
    }

    @Bean
    @Qualifier(value = "secondary_address")
    public Address secondaryAddress() {
        return new Address("Ahmedabad", "Guj");
    }

    @Bean
    @Qualifier(value = "other")
    public Address other() {
        return new Address("Jaunpur", "Uttar Pradesh");
    }

    @Bean
    public Person personInArg(String firstName, String lastName, int age, Address address) {
        return new Person(firstName, lastName, age, address);
    }

    @Bean
    public Person personUsingFunctionCall() {
        return new Person(firstName(), lastName(), age(), primaryAddress());
    }

    @Bean
    public Person personWithQualifierSecondaryAddress(String firstName, String lastName, int age, @Qualifier("secondary_address") Address address) {
        return new Person(firstName, lastName, age, address);
    }

    @Bean
    public Person personWithQualifierOtherAddress(String firstName, String lastName, int age, @Qualifier("other") Address address) {
        return new Person(firstName, lastName, age, address);
    }


}

@Configuration
@ComponentScan
public class AccessingBeanApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(AccessingBeanApp.class)) {
            System.out.println("Full name: " + context.getBean("fullName"));
            System.out.println("Person calling with argument bean: " + context.getBean("personInArg"));
            System.out.println("Person using Function call Bean: " + context.getBean("personUsingFunctionCall"));
            System.out.println("Person with Qualifier Secondary Address: " + context.getBean("personWithQualifierSecondaryAddress"));
            System.out.println("Person with Qualifier Secondary Other: " + context.getBean("personWithQualifierOtherAddress"));
        }
    }
}

package com.rahulpateldev.springframeworkv6.stereotype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

record Employee(int id, String name, String department) {
}

@Repository
class EmployeeRepository {
    static List<Employee> data = new ArrayList<>();

    static {
        data.add(new Employee(1, "Savita", "CE"));
        data.add(new Employee(2, "Hemali", "CE"));
        data.add(new Employee(3, "Hiten", "CE"));
        data.add(new Employee(4, "Alpesh", "CE"));
    }

    public Employee findEmployeeById(int id) {
        return data.stream()
                .filter(employee -> employee.id() == id)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Employee ID: " + id + " not found"));
    }

    public Employee addEmployee(Employee emp) {
        boolean empty = data.stream().
                filter(employee -> employee.id() == emp.id())
                .findFirst()
                .isEmpty();
        if (!empty) {
            throw new IllegalStateException("Employee ID: " + emp.id() + " is already exists");
        }
        data.add(emp);
        return emp;
    }

    public Employee updateEmployee(Employee emp) {
        Employee employee = this.findEmployeeById(emp.id());
        String name, department;

        if (Objects.nonNull(emp.name())) {
            name = emp.name();
        } else {
            name = employee.name();
        }

        if (Objects.nonNull(emp.department())) {
            department = emp.department();
        } else {
            department = employee.department();
        }

        var updatedEmployee = new Employee(employee.id(), name, department);
        int index = data.indexOf(employee);
        data.set(index, updatedEmployee);

        return findEmployeeById(emp.id());
    }

    public Employee removeEmployeeById(int id) {
        Employee employee = this.findEmployeeById(id);
        data.remove(employee);
        return employee;
    }

    public List<Employee> getAllEmployee() {
        return data;
    }
}


@Service
class EmployeeService {
    private final EmployeeRepository repository;

    EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee getEmployee(int id) {
        return this.repository.findEmployeeById(id);
    }

    public Employee addEmployee(Employee emp) {
        return this.repository.addEmployee(emp);
    }

    public Employee updateEmployee(Employee emp) {
        return this.repository.updateEmployee(emp);
    }

    public Employee removeEmployeeById(int id) {
        return this.repository.removeEmployeeById(id);
    }

    public List<Employee> getAllEmployee() {
        return this.repository.getAllEmployee();
    }
}

@Component
class EmployeeComponent {

    private final EmployeeService service;

    EmployeeComponent(EmployeeService service) {
        this.service = service;
    }

    public Employee getEmployee(int id) {
        return this.service.getEmployee(id);
    }

    public Employee addEmployee(Employee emp) {
        return this.service.addEmployee(emp);
    }

    public Employee updateEmployee(Employee emp) {
        return this.service.updateEmployee(emp);
    }

    public Employee removeEmployeeById(int id) {
        return this.service.removeEmployeeById(id);
    }

    public List<Employee> getAllEmployee() {
        return this.service.getAllEmployee();
    }
}

@Configuration
@ComponentScan
public class StereotypeApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(StereotypeApp.class)) {
            var component = context.getBean(EmployeeComponent.class);
            System.out.println("Employee By ID: " + component.getEmployee(1));
            System.out.println("All Employees: " + component.getAllEmployee());
            System.out.println("Added New Employee: " + component.addEmployee(new Employee(10, "Rahul", "CE")));
            System.out.println("All Employees: " + component.getAllEmployee());
            System.out.println("Updated Employee: " + component.updateEmployee(new Employee(2, "Rahul", "CE")));
            System.out.println("All Employees: " + component.getAllEmployee());
            System.out.println("Deleted Employee: " + component.removeEmployeeById(4));
            System.out.println("All Employees: " + component.getAllEmployee());
        }
    }
}

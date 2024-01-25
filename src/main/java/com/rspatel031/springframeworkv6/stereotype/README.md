# 👋 Spring Stereotype Annotations

- **@Component**: It is a generic annotation applicable for any class
    - Base for all Spring Stereotype Annotations
- Specializations of @Component:
    - **@Service**: Indicates that an annotated class has business logic
    - **@Controller**: Indicates that an annotated class is a **Controller** (Ex: Web / Rest Controller) and used to define controllers in your web applications and REST API
    - **@Repository**: Indicates that an annotated class is used to retrieve and manipulate data in a database

## ✍️ Example

```java
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

```

#### RECOMMENDATION: Use the most specific annotation possible during the development.
- By using a specific annotation, you are giving more information to the framework about your intentions.

| Annotations      | Description                                                                                                                                                                     | 
|:-----------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@Configuration` | Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions                                             |
| `@ComponentScan` | Define specific packages to scan for components. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation          |
| `@Component`     | An instance of class will be managed by Spring framework                                                                                                                        |
| `@Controller `   | Specialization of @Component indicating that an annotated class is a "Controller" (Ex: Web / REST Controller). Used to define controllers in your web applications and REST API |
| `@Service`       | Specialization of @Component indicating that an annotated class has business logic                                                                                              |
| `@Repository`    | Specialization of @Component indicating that an annotated class is used to retrieve and/or manipulate data in a database                                                        |


## 💬 Feedback

If you have any feedback, please reach out to me at rspatel031@gmail.com
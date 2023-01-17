package com.example.springpost;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeService() {
        employeeList = new ArrayList<>();

//        employeeList.add(new Employee("Test1", "Test1", new Date()));
//        employeeList.add(new Employee("Test2", "Test2", new Date()));
    }

    @GetMapping("/showEmployees")
    List<Employee> showEmployees() {
        return this.employeeList;
    }

    @PostMapping("/addEmployee")
    Employee addEmployee(@RequestBody Employee employee) {
        employeeList.add(employee);
        return employee;
    }
}

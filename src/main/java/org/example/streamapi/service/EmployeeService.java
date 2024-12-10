package org.example.streamapi.service;

import org.example.streamapi.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }


    public void addEmployee(Employee employee) {
        employees.put(employee.getFullName(), employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee.getFullName());
    }

    public Employee findEmployees(String fullName) {
        return employees.get(fullName);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}

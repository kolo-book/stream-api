package org.example.streamapi.service;

import org.example.streamapi.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeMinSalary(String department) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee getEmployeeMaxSalary(String department) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);

    }

    public Map<String, List<Employee>> getAllEmployeesDepartment(String department) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Map<String, List<Employee>> getAllEmployeesDepartments() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}

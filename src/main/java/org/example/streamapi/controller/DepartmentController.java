package org.example.streamapi.controller;

import org.example.streamapi.Employee;
import org.example.streamapi.service.DepartmentService;
import org.example.streamapi.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    public final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable String id) {
        return departmentService.getEmployeesByDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSalarySumByDepartment(@PathVariable String id) {
        return departmentService.getSalarySumByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee getMaxSalaryEmployeeByDepartment(@PathVariable String id) {
        return departmentService.getEmployeeMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getMinSalaryEmployeeByDepartment(@PathVariable String id) {
        return departmentService.getEmployeeMinSalary(id);
    }

    @GetMapping("/employees")
    public Map<String, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesDepartments();
    }
}

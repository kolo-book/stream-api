package org.example.streamapi.controller;

import org.example.streamapi.Employee;
import org.example.streamapi.service.DepartmentService;
import org.example.streamapi.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping("/max-salary")
    public Employee getMaxSalary(@RequestParam("depatmentId") String depatmentId) {
        return departmentService.getEmployeeMaxSalary(depatmentId);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalary(@RequestParam("depatmentId") String depatmentId) {
        return departmentService.getEmployeeMinSalary(depatmentId);
    }

    @GetMapping("/all")
    public Map<String, List<Employee>> getAllEmployees(@RequestParam(value = "depatmentId", required = false) String depatmentId) {
        if (depatmentId != null) {
            return departmentService.getAllEmployeesDepartments();
        }
        return departmentService.getAllEmployeesDepartment(depatmentId);
    }

}

package org.example.streamapi;

import org.example.streamapi.service.DepartmentService;
import org.example.streamapi.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeServiceTest {

    @Test
    void testGetEmployeeMaxSalary() {
        EmployeeService employeeServiceMock = Mockito.mock(EmployeeService.class);
        DepartmentService departmentService = new DepartmentService(employeeServiceMock);

        List<Employee> employees = List.of(
                new Employee("John Doe", 1000, "1"),
                new Employee("Jane Smith", 2000, "1"),
                new Employee("Emily Davis", 1500, "2")
        );

        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(employees);

        Employee maxSalaryEmployee = departmentService.getEmployeeMaxSalary("1");
        assertNotNull(maxSalaryEmployee);
        assertEquals("Jane Smith", maxSalaryEmployee.getFullName());
    }

}

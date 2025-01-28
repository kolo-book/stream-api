package org.example.streamapi;

import org.example.streamapi.service.DepartmentService;
import org.example.streamapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Employee> mockEmployees = Arrays.asList(
                new Employee("John Dog", 50000, "IT"),
                new Employee("Jane Doe", 60000, "IT"),
                new Employee("Wil Smith", 60000, "Finance"),
                new Employee("Mike Bus", 45000, "HR")
        );

        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);
    }

    @Test
    void testGetSalarySumByDepartment() {
        int itSalarySum = departmentService.getSalarySumByDepartment("IT");
        int hrSalarySum = departmentService.getSalarySumByDepartment("HR");
        int nonExistentDeptSalarySum = departmentService.getSalarySumByDepartment("Finance");
        assertEquals(110000, itSalarySum);
        assertEquals(45000, hrSalarySum);
        assertEquals(60000, nonExistentDeptSalarySum);
    }

    @Test
    void testGetEmployeeMaxSalary() {
        Employee maxSalaryEmployee = departmentService.getEmployeeMaxSalary("Finance");
        assertNotNull(maxSalaryEmployee);
        assertEquals("Wil Smith", maxSalaryEmployee.getFullName());
    }

    @Test
    void testGetEmployeeMinSalary() {
        Employee minSalaryEmployee = departmentService.getEmployeeMinSalary("HR");
        assertNotNull(minSalaryEmployee);
        assertEquals("Mike Bus", minSalaryEmployee.getFullName());
    }

    @Test
    void testGetAllEmployeesDepartment() {
        Map<String, List<Employee>> employeesByDepartment = departmentService.getAllEmployeesDepartment("IT");
        assertEquals(2, employeesByDepartment.get("IT").size());  // Изменили на 2, так как в IT два сотрудника
    }

    @Test
    void testGetAllEmployeesDepartments() {
        Map<String, List<Employee>> allEmployees = departmentService.getAllEmployeesDepartments();
        assertEquals(4, allEmployees.values().stream().mapToInt(List::size).sum());  // Изменили на 4, так как у нас 4 сотрудника
    }

    @Test
    void testGetEmployeeMaxSalaryForNonExistentDepartment() {
        Employee maxSalaryEmployee = departmentService.getEmployeeMaxSalary("Unknown");
        assertNull(maxSalaryEmployee);
    }

    @Test
    void testGetEmployeesByDepartment() {
        List<Employee> itEmployees = departmentService.getEmployeesByDepartment("IT");
        assertEquals(2, itEmployees.size());
        assertTrue(itEmployees.stream().anyMatch(e -> e.getFullName().equals("John Dog")));
        assertTrue(itEmployees.stream().anyMatch(e -> e.getFullName().equals("Jane Doe")));

        List<Employee> hrEmployees = departmentService.getEmployeesByDepartment("HR");
        assertEquals(1, hrEmployees.size());
        assertTrue(hrEmployees.stream().anyMatch(e -> e.getFullName().equals("Mike Bus")));

        List<Employee> financeEmployees = departmentService.getEmployeesByDepartment("Finance");
        assertEquals(1, financeEmployees.size());
        assertTrue(financeEmployees.stream().anyMatch(e -> e.getFullName().equals("Wil Smith")));

        List<Employee> unknownDeptEmployees = departmentService.getEmployeesByDepartment("Unknown");
        assertEquals(0, unknownDeptEmployees.size());
    }

}

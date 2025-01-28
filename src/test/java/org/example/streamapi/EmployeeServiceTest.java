package org.example.streamapi;

import org.example.streamapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee("John Doe", 50000, "IT");
        employeeService.addEmployee(employee);

        assertEquals(1, employeeService.getAllEmployees().size());
        assertTrue(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    void testAddDuplicateEmployee() {
        Employee employee = new Employee("John Doe", 50000, "IT");
        employeeService.addEmployee(employee);

        employeeService.addEmployee(employee); // Attempt to add duplicate
        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    void testRemoveEmployee() {
        Employee employee = new Employee("John Doe", 50000, "IT");
        employeeService.addEmployee(employee);
        employeeService.removeEmployee(employee);
        assertTrue(employeeService.getAllEmployees().isEmpty());
    }

    @Test
    void testRemoveNonExistentEmployee() {
        Employee employee = new Employee("John Doe", 50000, "IT");

        employeeService.removeEmployee(employee); // Attempt to remove non-existent employee
        assertTrue(employeeService.getAllEmployees().isEmpty());
    }

    @Test
    void testFindEmployee() {
        Employee employee = new Employee("John Smit", 50000, "IT");
        employeeService.addEmployee(employee);
        Employee foundEmployee = employeeService.findEmployees("John Smit");
        assertNotNull(foundEmployee);
        assertEquals(employee, foundEmployee);
    }

    @Test
    void testFindNonExistentEmployee() {
        Employee foundEmployee = employeeService.findEmployees("Non Existent");
        assertNull(foundEmployee);
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee("John Trapm", 50000, "IT");
        Employee employee2 = new Employee("Jane Smith", 60000, "HR");
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.contains(employee1));
        assertTrue(allEmployees.contains(employee2));
    }

}

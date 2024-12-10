package org.example.streamapi;

import java.util.Objects;

public class Employee {

    private String fullName;
    private int salary;
    private String department;
    private static int count_id = 1;

    public Employee(String fullName, int salary, String department) {
        this.fullName = fullName;
        this.salary = salary;
        this.department = department;
        count_id++;
    }

    public String getFullName() {
        return fullName;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public static int getCount_id() {
        return count_id;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(fullName, employee.fullName) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, salary, department);
    }
}
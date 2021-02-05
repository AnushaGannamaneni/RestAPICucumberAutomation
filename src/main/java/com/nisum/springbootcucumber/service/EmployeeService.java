package com.nisum.springbootcucumber.service;

import com.nisum.springbootcucumber.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee addEmployee(Employee employee);
    public Employee getEmployee(Long employeeId);
    public List<Employee> getAllEmployees();
    public Employee updateEmployee(Employee employee);
    public void deleteEmployeeById(Long id);
}

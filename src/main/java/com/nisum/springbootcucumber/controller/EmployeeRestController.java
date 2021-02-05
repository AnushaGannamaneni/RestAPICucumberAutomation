package com.nisum.springbootcucumber.controller;

import com.nisum.springbootcucumber.entity.Employee;
import com.nisum.springbootcucumber.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/api/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/api/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/api/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PutMapping("/api/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,
                                   @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        Employee empUpdated = new Employee();
        if (emp != null) {
            empUpdated = employeeService.updateEmployee(employee);
        }
        return empUpdated;
    }

    @DeleteMapping("/api/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}

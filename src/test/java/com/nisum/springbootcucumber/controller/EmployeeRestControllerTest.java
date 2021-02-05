package com.nisum.springbootcucumber.controller;

import com.nisum.springbootcucumber.entity.Employee;
import com.nisum.springbootcucumber.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class EmployeeRestControllerTest {

    @InjectMocks
    EmployeeRestController employeeRestController;

    @Mock
    EmployeeService employeeService;

    @Test
    public void testAddEmployee(){
        Employee employee = prepareEmployeeObject("Alex", "TestDepartment", 23);
        Mockito.when(employeeService.addEmployee(employee)).thenReturn(prepareEmployeeResponseObject(45, "Alex", "TestDepartment", 23));
        Employee empRes = employeeRestController.addEmployee(employee);
        assertEquals(45, empRes.getId());
        assertEquals("Alex", empRes.getEmployeeName());
        assertEquals("TestDepartment", empRes.getDepartment());
        assertEquals(23, empRes.getSalary(), 0);
    }

    private Employee prepareEmployeeObject(String employeeName, String department, int age) {
        return Employee.builder().department(department).salary(age).employeeName(employeeName).build();
    }

    private Employee prepareEmployeeResponseObject(int id, String employeeName, String department, int age) {
        return Employee.builder().id(id).department(department).salary(age).employeeName(employeeName).build();
    }
}

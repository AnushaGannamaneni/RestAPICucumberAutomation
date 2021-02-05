package com.nisum.springbootcucumber;

import com.nisum.springbootcucumber.entity.Employee;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = SpringBootCucumberApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EmployeeStepDefinitions {
    String addURI = "http://localhost:9992";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders;
    Employee employee;

    @Given("I Set employee service api endpoint {string}")
    public void setPostEndpoint(String uri){
        addURI = addURI + uri;
        System.out.println("Add URL :"+addURI);
    }

    @When("^I set request header$")
    public void setHttpHeaders(){
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @When("Send a POST HTTP request Name {} and Department {} and Age {}")
    public void addEmployeeRecord(String employeeName, String department, int age) {
        HttpEntity<Employee> request = new HttpEntity<>(prepareEmployeeObject(employeeName, department, age),httpHeaders);
        //POST Method to Add New Employee
        employee = restTemplate.postForObject(addURI, request, Employee.class);
    }

    @Then("I get valid http response for Name {} and Department {} and Age {}")
    public void validateAddEmployeeResponse(String employeeName, String department, int age) {
        assertEquals(department, employee.getDepartment());
        assertEquals(age, employee.getSalary(), 0);
        assertEquals(employeeName, employee.getEmployeeName());
    }

    @When("Send a GET HTTP request With Employee Id {int}")
    public void getEmployeeById(int employeeId){
        //GET Method to Add New Employee
        employee = restTemplate.getForObject(addURI + employeeId, Employee.class);
    }

    @Then("I receive valid employee record as Name {} and Department {} and Age {}")
    public void validateGetEmployeeResponse(String employeeName, String department, int age) {
        assertEquals(1, employee.getId());
        assertEquals(department, employee.getDepartment());
        assertEquals(age, employee.getSalary(), 0);
        assertEquals(employeeName, employee.getEmployeeName());
    }

    @When("^I Send a GET HTTP request$")
    public void sendGetHttpRequest() {
        // Get call to retrieve all employees.
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(addURI, Employee[].class);
        Employee[] employees = responseEntity.getBody();
        employee = employees[0];
        assertNotNull(employee);
        assertEquals(2, employees.length);
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @When("Send a Delete HTTP request With Employee Id {int}")
    public void deleteEmployeeById(int employeeId) {
        // Delete method to delete existing employee
        restTemplate.delete(addURI + employeeId);
    }

    @When("Send a GET HTTP request and I get Employee Id {int}")
    public void sendHttpRequest(int employeeId) {
        // Get call to retrieve all employees.
        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(addURI, Employee[].class);
        Employee[] employees = responseEntity.getBody();
        employee = employees[0];
        assertNotNull(employee);
        assertEquals(1, employees.length);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(employeeId, employee.getId());
    }

    @When("Send a PUT HTTP request for Employee Id {} and Name {} and Department {} and Age {}")
    public void sendPutHttpRequest(int employeeId, String employeeName, String department, int age){
        // update existing employee
        HttpEntity<Employee> request = new HttpEntity<>(prepareEmployeeObject(employeeName, department, age),httpHeaders);
        Map<String, Integer> params = new HashMap<>();
        params.put("employeeId", employeeId);
        restTemplate.put(addURI, request, params);
    }

    private Employee prepareEmployeeObject(String employeeName, String department, int age) {
        return Employee.builder().department(department).salary(age).employeeName(employeeName).build();
    }
}

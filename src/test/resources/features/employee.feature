Feature: Test CRUD methods in Employee REST API testing

  Scenario: Add Employee record
    Given I Set employee service api endpoint "/api/employees/"
    When I set request header
    When Send a POST HTTP request Name Alex and Department NorCal and Age 23
    Then I get valid http response for Name Alex and Department NorCal and Age 23

  Scenario: Add second Employee record
    Given I Set employee service api endpoint "/api/employees/"
    When I set request header
    When Send a POST HTTP request Name Michelle and Department Southern and Age 43
    Then I get valid http response for Name Michelle and Department Southern and Age 43

  Scenario: Get Employee record by Id
    Given I Set employee service api endpoint "/api/employees/"
    When I set request header
    When Send a GET HTTP request With Employee Id 1
    Then I receive valid employee record as Name Alex and Department NorCal and Age 23

  Scenario: Retrieve all employees
    Given I Set employee service api endpoint "/api/employees/"
    When I set request header
    When I Send a GET HTTP request
    Then I receive valid employee record as Name Alex and Department NorCal and Age 23

  Scenario: Delete Employee record by Id
    Given I Set employee service api endpoint "/api/employees/"
    When I set request header
    When Send a Delete HTTP request With Employee Id 2
    When Send a GET HTTP request and I get Employee Id 1
    Then I receive valid employee record as Name Alex and Department NorCal and Age 23


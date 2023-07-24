package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.demo.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

	@Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;
	
    @Test
    public void testGetEmpNameById_ValidId() throws Exception {
        // Mock the behavior of the employeeService.getEmployeeNameById() method
        Long empId = 1L;
        String expectedEmpName = "John Doe";
        when(employeeService.getEmployeeNameById(empId)).thenReturn(expectedEmpName);

        // Call the controller method and capture the response
        ResponseEntity<String> response = employeeController.getEmpNameById(empId);

        // Assert the response status and the returned employee name
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedEmpName, response.getBody());
    }
    
    
    @Test
    public void testGetEmpNameById_InvalidId() throws Exception {
        // Mock the behavior of the employeeService.getEmployeeNameById() method for an invalid ID
        Long empId = 100L;
        when(employeeService.getEmployeeNameById(empId)).thenReturn(null);

        // Call the controller method and capture the response
        ResponseEntity<String> response = employeeController.getEmpNameById(empId);

        // Assert the response status for a not found scenario
        assertEquals(404, response.getStatusCodeValue());
    }
}

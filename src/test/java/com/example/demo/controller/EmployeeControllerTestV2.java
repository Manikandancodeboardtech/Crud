package com.example.demo.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class EmployeeControllerTestV2 {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	/*
	 * @MockBean annotation tells Spring to create mock instance of EmployeeService
	 * and add it to the application context so that it is injected to the
	 * EmployeeController.
	 */
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	@DisplayName("JUnit test for getAllEmployees operation")
	public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() throws Exception {
		// given - precondition or setup
		EmployeeEntity employee1 = new EmployeeEntity("Richard Parker", 1L, "USA");
		EmployeeEntity employee2 = new EmployeeEntity("Maureen", 2L, "CANADA");
		List<EmployeeEntity> employees = List.of(employee1, employee2);
		given(employeeService.getAllEmployees()).willReturn(employees);
		// when - action or the behaviour
		ResultActions response = mockMvc.perform(get("/dnm/employees"));
		// then - verify the output
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(employees.size())));
	}
	
	@Test
	@DisplayName("JUnit test for getEmployeeNameById operation")
	public void givenEmployeee_whenGetEmployeeNameById_thenReturnEmployee() throws Exception {
		// given - precondition or setup
		String employeeName = "Stuart Little";
		given(employeeService.getEmployeeNameById(1L)).willReturn(employeeName);
		// when - action or the behaviour
		ResultActions response = mockMvc.perform(get("/dnm/employees/1"));
		// then - verify the output
		response.andDo(print()).andExpect(status().isOk()).andExpect(content().string(employeeName));
	}
	
	@Test
	@DisplayName("JUnit test for createEmployee operation")
	public void givenEmployee_whenCreateEmployee_thenReturnEmployee() {
		EmployeeEntity employee = new EmployeeEntity("Dhandapani",7L,"Kodumudi");
		
		try {
			// given - precondition or setup
			given(employeeService.saveEmployee(any(EmployeeEntity.class))).willReturn(employee);
			// when - action or the behaviour
			ResultActions response = mockMvc.perform(post("/dnm/employees/save").contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsBytes(employee)));
			// then - verify the output
			response.andDo(print());
			response.andDo(print()).andExpect(status().isOk())
					.andExpect(jsonPath("$.employeeName", is(employee.getEmployeeName())))
					.andExpect(jsonPath("$.employeeId", is(7)))
					.andExpect(jsonPath("$.employeeAddress", is(employee.getEmployeeAddress())));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	@DisplayName("JUnit test for getEmployeeNameById operation - When employee with the given id does not exist")
	public void givenEmployeee_whenGetEmployeeNameById_thenReturnEmployeeDoesNotExistMessage() throws Exception {
		// given - precondition or setup
		Long employeeId = 4L;
		String errorMessage = "Employee with Id 4 does not exist";
		willThrow(Exception.class).given(employeeService).getEmployeeNameById(employeeId);
		// when - action or the behaviour
		ResultActions response = mockMvc.perform(get("/dnm/employees/{id}",employeeId));
		// then - verify the output
		response.andDo(print()).andExpect(status().isInternalServerError());
	}
	
	
	
	
	
	
	
	
	

}

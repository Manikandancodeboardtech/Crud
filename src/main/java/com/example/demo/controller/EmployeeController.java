package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping(path = "/dnm")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(path = "/employees/{id}")
	public ResponseEntity<String> getEmpNameById(@PathVariable("id") Long empId) {
		
		try {
			String empName = employeeService.getEmployeeNameById(empId);
			return new ResponseEntity<String>(empName, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/employees")
    public ResponseEntity<?> getAllEmp(){
		
    	List<EmployeeEntity> responseEmp = new ArrayList<>();
    	try {
    		responseEmp = employeeService.getAllEmployees();
			return new ResponseEntity<List<EmployeeEntity>>(responseEmp, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PostMapping(path = "/employees/save")
	public ResponseEntity<?> saveEmp(@RequestBody EmployeeEntity employeeEntity){
		
    	EmployeeEntity responseEmp = null ;
    	try {
    		responseEmp = employeeService.saveEmployee(employeeEntity);
			return new ResponseEntity<EmployeeEntity>(responseEmp, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@DeleteMapping(path = "/employees/delete")
	public ResponseEntity<?> deletEmp(@RequestParam("id") Long empId ){
		
    	Boolean status = false ;
    	try {
    		status = employeeService.deleteById(empId);
			return new ResponseEntity<String>("Success", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PutMapping(path = "/employees/update/{id}")
	public ResponseEntity<?> updateEmp(@PathVariable("id") Long empId,@RequestBody EmployeeEntity employeeEntity){
		EmployeeEntity UpdateEmployee = null;
    	
    	try {
    		UpdateEmployee = employeeService.updateEmployee(empId, employeeEntity);
			return new ResponseEntity<EmployeeEntity>(UpdateEmployee, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}

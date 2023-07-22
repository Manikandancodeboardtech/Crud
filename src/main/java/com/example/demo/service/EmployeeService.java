package com.example.demo.service;



import java.util.List;

import com.example.demo.entity.EmployeeEntity;

public interface EmployeeService {

	
	public String getEmployeeNameById(Long id) throws Exception ;
	
    public List<EmployeeEntity> getAllEmployees() ;
    
    public EmployeeEntity getEmployeeById(Long id);
    
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);
    
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employeeEntity) throws Exception;
    
    public Boolean deleteById(Long id) throws Exception;
   
}

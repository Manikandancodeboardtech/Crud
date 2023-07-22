package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public String getEmployeeNameById(Long id) throws Exception {
		EmployeeEntity employeeEntity = null;
		String empName = null;
		employeeEntity = employeeDao.findById(id)
				.orElseThrow(() -> new Exception("Employee with Id " + id + " does not exist"));
		return employeeEntity.getEmployeeName();
	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {

		List<EmployeeEntity> employeeEntity = new ArrayList<>();
		employeeEntity = employeeDao.findAll();
		return employeeEntity;
	}

	@Override
	public EmployeeEntity getEmployeeById(Long id) {
		return null;
	}

	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
		EmployeeEntity savedEmployeeEntity = null;
		savedEmployeeEntity = employeeDao.save(employeeEntity);
		return savedEmployeeEntity;
	}

	
	@Override
	public EmployeeEntity updateEmployee(Long id, EmployeeEntity employeeEntity) throws Exception {
		
		EmployeeEntity foundEmp = employeeDao.findById(id).orElseThrow(()-> new Exception("Employe with Id "+id+" dose not exist"));
		foundEmp.setEmployeeName(employeeEntity.getEmployeeName());
		foundEmp.setEmployeeAddress(employeeEntity.getEmployeeAddress());
//		foundEmp.setEmployeeId(employeeEntity.getEmployeeId());
//		this.employeeDao.updateEmployeeById(foundEmp.getEmployeeAddress(), foundEmp.getEmployeeName(), id);
		this.employeeDao.save(foundEmp);
		return foundEmp;
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		Boolean flag = true;
		getEmployeeNameById(id);
		employeeDao.deleteById(id);
		return flag;
	}

}

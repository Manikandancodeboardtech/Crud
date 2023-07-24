package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

	@Column(name = "employee_name")
	private String employeeName;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
	@Column(name = "employee_address")
	private String employeeAddress;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public EmployeeEntity(String employeeName, Long employeeId, String employeeAddress) {
		super();
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.employeeAddress = employeeAddress;
	}
	
	public EmployeeEntity(String employeeName, String employeeAddress) {
		super();
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
	}

	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeName=" + employeeName + ", employeeId=" + employeeId + ", employeeAddress="
				+ employeeAddress + "]";
	}

}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;

@Repository
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {

	@Modifying
	@Query(value = "update EmployeeEntity set employeeAddress =:employeeAddress, employeeName =:employeeName where employeeId =:employeeId")
	void updateEmployeeById(@Param("employeeAddress") String employeeAddress,
			@Param("employeeName") String employeeName, @Param("employeeId") Long employeeId);

}

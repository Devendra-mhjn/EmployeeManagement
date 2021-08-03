package com.management.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.management.entity.Employee;

@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, 
JpaSpecificationExecutor<Employee> {
}
	


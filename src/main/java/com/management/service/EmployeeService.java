package com.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.entity.Employee;
import com.management.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee saveOrUpdate(Employee emp) {
		// TODO Auto-generated method stub
		return employeeRepository.save(emp);
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> emps = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(emps1 -> emps.add(emps1));
		return emps;

	}

	public Employee getEmployeeById(Long emp_id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(emp_id).get();
	}

	public void deleteEmployee(Long emp_id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(emp_id);
	}

	public long count() {
		// TODO Auto-generated method stub
		return employeeRepository.count();
	}

	public List<Employee> findAll(int pageNumber, int rowPerPage) {
		// TODO Auto-generated method stub
		List<Employee> employee = new ArrayList<>();
		employeeRepository.findAll().forEach(employee::add);
		return employee;
	}

	public List<Employee> getAllEmployees(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<Employee> pagedResult = employeeRepository.findAll(paging);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<Employee>();
		}
	}

	

}

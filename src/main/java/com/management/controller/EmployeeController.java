package com.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.entity.Employee;
import com.management.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public String showHome(Model model) {
		return "redirect:/employees";
	}
	

	@GetMapping("/register")
	public String showForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "register_form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute Employee employee, Map<String, List<Employee>> model) {
		employeeService.saveOrUpdate(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/{emp_id}")
	private String getEmployee(@PathVariable("emp_id") Long emp_id, Model model) {
		Employee emp = employeeService.getEmployeeById(emp_id);
		model.addAttribute("add", false);
		model.addAttribute("emp", emp);
		return "redirect:/employee_page";
	}

	@PostMapping("/employee/{emp_id}/update")
	private String updateEmployee(@PathVariable Long emp_id, @ModelAttribute Employee emps,
			Map<String, List<Employee>> model) {
		Employee emp = employeeService.getEmployeeById(emp_id);
		emp.setEmp_name(emps.getEmp_name());
		emp.setEmp_addr(emps.getEmp_addr());
		emp.setEmp_birthday(emps.getEmp_birthday());
		emp.setEmp_dept(emps.getEmp_dept());
		emp.setEmp_doj(emps.getEmp_doj());
		emp.setEmp_gender(emps.getEmp_gender());
		emp.setEmp_mailid(emps.getEmp_mailid());
		emp.setEmp_password(emp.getEmp_password());
		employeeService.saveOrUpdate(emps);
		return "redirect:/employees";
	}

	@GetMapping(value = { "/employee/{emp_id}/edit" })
	public String editCustomerForm(@PathVariable Long emp_id, Model model) {
		Employee emp = employeeService.getEmployeeById(emp_id);
		model.addAttribute("add", false);
		model.addAttribute("emp", emp);
		return "edit_form";
	}

	@GetMapping(value = { "/employee/{emp_id}/delete" })
	private String deleteEmployee(@PathVariable("emp_id") Long emp_id, Map<String, List<Employee>> model) {
		employeeService.deleteEmployee(emp_id);
		return "redirect:/employees";
	}

	@GetMapping(value = "/employees")
	public String getAllEmployees(Model model, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		List<Employee> employee = employeeService.getAllEmployees(pageNo, pageSize);
		long count = employeeService.count();

		boolean hasPrev;
		if (pageNo > 0)
			hasPrev = true;
		else
			hasPrev = false;

		boolean hasNext = (pageNo * pageSize) < count;
		model.addAttribute("employee", employee);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNo - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNo + 1);
		return "index";
	}
	
}

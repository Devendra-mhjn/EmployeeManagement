package com.management.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "emp_0101")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long emp_id;
	@Column
	private String emp_name;
	@Column
	private String emp_addr;
	@Column
	private String emp_mailid;
    @Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate emp_doj;
	@Column
	private String emp_password;
	@Column
	private String emp_gender;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate emp_birthday;

	@Column
	private String emp_dept;

}

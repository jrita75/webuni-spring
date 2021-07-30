package hu.webuni.hr.rita.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import hu.webuni.hr.rita.model.CompanyType;

public class CompanyDto {
	private Long id;
	private String name;
	private String reg_number;
	private String address;
	@Enumerated(EnumType.STRING)
	private CompanyType type;
	
	private List<EmployeeDto> employees = new ArrayList<>();
	
	public CompanyDto(Long id, String name, String reg_number, String address, List<EmployeeDto> employees) {
		super();
		this.id = id;
		this.name = name;
		this.reg_number = reg_number;
		this.address = address;
		this.employees = employees;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg_number() {
		return reg_number;
	}
	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CompanyType getType() {
		return type;
	}
	public void setType(CompanyType type) {
		this.type = type;
	}
	public List<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	
}

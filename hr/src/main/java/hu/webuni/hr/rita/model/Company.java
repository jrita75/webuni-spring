package hu.webuni.hr.rita.model;

import java.util.HashMap;
import java.util.Map;


public class Company {
	private Long id;
	private String name;
	private String reg_number;
	private String address;
	private Map<Long, Employee> employees;
	
	public Company(Long id, String name, String reg_number, String address) {
		super();
		this.id = id;
		this.name = name;
		this.reg_number = reg_number;
		this.address = address;
		this.employees = new HashMap<Long, Employee>();
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
	public Map<Long, Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Map<Long, Employee> employees) {
		this.employees = employees;
	}
	

}

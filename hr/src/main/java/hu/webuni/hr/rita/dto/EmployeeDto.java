package hu.webuni.hr.rita.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Past;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;	

public class EmployeeDto {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String position;
	@Min(1)
	private int salary;
	@Past
	private LocalDateTime employedSince;
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public LocalDateTime getEmployedSince() {
		return employedSince;
	}
	public void setEmployedSince(LocalDateTime employedSince) {
		this.employedSince = employedSince;
	}
	public EmployeeDto(Long id, String name, String position, int salary, LocalDateTime employedSince) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.employedSince = employedSince;
	}
	public EmployeeDto() {
		
	};

}

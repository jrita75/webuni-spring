package hu.webuni.hr.rita.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String position;
	private int salary;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime employedSince;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	public Employee() {
		
	}
	public Employee(Long id, String name, String position, int salary, LocalDateTime employedSince) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.employedSince = employedSince;
	}
	
	public void printData() {
		System.out.println("----------------------------");
		System.out.println("id:\t\t"+id);
		System.out.println("name:\t\t"+name);
		System.out.println("position:\t"+position);
		System.out.println("salary:\t\t"+salary);
		System.out.println("employed since:\t"+employedSince.toString());
		System.out.println("employed years:\t"+getEmployedYears());
		System.out.println("----------------------------");
		
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	public double getEmployedYears()
	{
		return employedSince.until(LocalDateTime.now(), ChronoUnit.MONTHS) / 12.0;
	}
	
}

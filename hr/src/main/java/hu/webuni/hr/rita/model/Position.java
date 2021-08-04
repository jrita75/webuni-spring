package hu.webuni.hr.rita.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Position {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private Qualification qualification;
	private int minSalary;
	
	public Position() 
	{
		
	}
	
	public Position(Long id, String name, Qualification qualification, int minSalary) {
		super();
		this.id = id;
		this.name = name;
		this.qualification = qualification;
		this.minSalary = minSalary;
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
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	
	
}

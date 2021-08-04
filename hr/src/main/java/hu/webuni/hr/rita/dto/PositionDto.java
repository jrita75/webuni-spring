package hu.webuni.hr.rita.dto;


import hu.webuni.hr.rita.model.Qualification;

public class PositionDto {
	private Long id;
	private String name;
	private Qualification qualification;
	private int minSalary;
	
	public PositionDto() {
		
	}
	public PositionDto(Long id, String name, Qualification qualification, int minSalary) {
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

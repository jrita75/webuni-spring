package hu.webuni.hr.rita.dto;

public class CompanyTypeDto {
	Long id;
	String name;
	public CompanyTypeDto() {
		
	}
	public CompanyTypeDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}

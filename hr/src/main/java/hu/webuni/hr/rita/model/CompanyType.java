package hu.webuni.hr.rita.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*public enum CompanyType {
	BT, KFT, ZRT, NYRT
}*/

@Entity
public class CompanyType {
	@Id Long id;
	String name;
	public CompanyType() {
		
	}
	public CompanyType(Long id, String name) {
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


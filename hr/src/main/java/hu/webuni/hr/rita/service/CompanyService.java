package hu.webuni.hr.rita.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;

@Service
public class CompanyService {
	private Map<Long, Company> companies = new HashMap<>();
	{
		Company comp;
		Map<Long, Employee> emplist;
		
		comp = new Company(1L, "FORMATUM Korlátolt Felelősségű Társaság", "13 09 115040", "2009 Pilisszentlászló, Tölgyfa utca 26.");
		emplist= comp.getEmployees();
		emplist.put(1L, new Employee(1L, "Egyeske", "titkár", 150000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		comp.setEmployees(emplist);
		companies.put(1L, comp);
		
		comp = new Company(2L, "HAJÓRÉV Kereskedelmi, Szolgáltató és Idegenforgalmi Korlátolt Felelősségű Társaság", "13 09 062199", "2170 Aszód, Kossuth Lajos utca 13.");
		companies.put(2L, comp);
		
		comp = new Company(3L, "BioTech USA Korlátolt Felelősségű Társaság", "01 09 352550", "1033 Budapest, Huszti út 60.");
		companies.put(3L, comp);
		
	}
	
	public Company save(Company comapany) {
		companies.put(comapany.getId(), comapany);
		return comapany;
	}
	
	public List<Company> findAll(){
		return new ArrayList<>(companies.values());
	}
	
	public Company findById(long id){
		return companies.get(id);
	}
	
	public void delete(long id){
		companies.remove(id);
	}
	
	
}

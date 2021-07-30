package hu.webuni.hr.rita.service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.CompanyType;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.repository.EmployeeRepository;

@Service
public class InitDbService {
	@Autowired CompanyRepository companyRepository;
	@Autowired EmployeeRepository employeeRepository;
	
	@Transactional
	public void clearDb() 
	{
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}
	
	public void insertTestData()
	{
		
		Company comp;
		Employee emp;
		
		comp = new Company(1L, "FORMATUM Korlátolt Felelősségű Társaság", "13 09 115040", "2009 Pilisszentlászló, Tölgyfa utca 26.", null);
		comp.setType(CompanyType.KFT);
		comp = companyRepository.save(comp);
		emp = new Employee(1L, "Titkár1", "titkár", 150000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(2L, "Titkár2", "titkár", 100000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(3L, "Tesztelő1", "tesztelő", 250000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(4L, "Tesztelő2", "tesztelő", 350000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		
		comp = new Company(2L, "HAJÓRÉV Kereskedelmi, Szolgáltató és Idegenforgalmi Korlátolt Felelősségű Társaság", "13 09 062199", "2170 Aszód, Kossuth Lajos utca 13.", null);
		comp.setType(CompanyType.KFT);
		comp = companyRepository.save(comp);
		
		emp = new Employee(5L, "H.Titkár1", "titkár", 150000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(6L, "H.Titkár2", "titkár", 100000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(7L, "H.Tesztelő1", "tesztelő", 200000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(8L, "H.Tesztelő2", "tesztelő", 250000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		comp = new Company(3L, "BioTech USA Korlátolt Felelősségű Társaság", "01 09 352550", "1033 Budapest, Huszti út 60.", null);
		comp.setType(CompanyType.KFT);
		comp = companyRepository.save(comp);
		
		emp = new Employee(9L, "B.Titkár1", "titkár", 100000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(10L, "B.Titkár2", "titkár", 100011, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(11L, "B.Tesztelő1", "tesztelő", 200001, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		
		
		
	}

}

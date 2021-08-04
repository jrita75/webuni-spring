package hu.webuni.hr.rita.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.CompanyType;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.model.Position;
import hu.webuni.hr.rita.model.Qualification;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.repository.CompanyTypeRepository;
import hu.webuni.hr.rita.repository.EmployeeRepository;
import hu.webuni.hr.rita.repository.PositionRepository;

@Service
public class InitDbService {
	@Autowired CompanyRepository companyRepository;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired PositionRepository positionRepository;
	@Autowired CompanyTypeRepository companyTypeRepository;
	
	@Transactional
	public void clearDb() 
	{
		companyTypeRepository.deleteAll();
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		positionRepository.deleteAll();
	}
	
	public void insertTestData()
	{
		CompanyType ct;
		Company comp;
		Employee emp;
		Position pos1 = new Position(1L, "titkárnő", Qualification.COLLEGE, 100000);
		pos1 = positionRepository.save(pos1);
		Position pos2 = new Position(2L, "tesztelő", Qualification.GRADUATION, 100000);
		pos2 = positionRepository.save(pos2);
		Position pos3 = new Position(2L, "takarító", null, 70000);
		pos3 = positionRepository.save(pos3);
		
		// BT, KFT, ZRT, NYRT
		ct = new CompanyType(1L, "BT");
		ct = companyTypeRepository.save(ct);
		ct = new CompanyType(2L, "KFT");
		ct = companyTypeRepository.save(ct);
		ct = new CompanyType(3L, "ZRT");
		ct = companyTypeRepository.save(ct);
		ct = new CompanyType(4L, "NYRT");
		ct = companyTypeRepository.save(ct);
		
		ct = companyTypeRepository.getById(2L); // kft
		
		
		comp = new Company(1L, "FORMATUM Korlátolt Felelősségű Társaság", "13 09 115040", "2009 Pilisszentlászló, Tölgyfa utca 26.", null, ct);
		comp = companyRepository.save(comp);
		emp = new Employee(1L, "Titkár1", pos1, 150000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(2L, "Titkár2", pos1, 100000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(3L, "Tesztelő1", pos2, 250000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(4L, "Tesztelő2", pos2, 350000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		
		comp = new Company(2L, "HAJÓRÉV Kereskedelmi, Szolgáltató és Idegenforgalmi Korlátolt Felelősségű Társaság", "13 09 062199", "2170 Aszód, Kossuth Lajos utca 13.", null, ct);
		comp = companyRepository.save(comp);
		
		emp = new Employee(5L, "H.Titkár1", pos1, 150000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(6L, "H.Titkár2", pos1, 100000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(7L, "H.Tesztelő1", pos2, 200000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(8L, "H.Tesztelő2", pos2, 250000, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		comp = new Company(3L, "BioTech USA Korlátolt Felelősségű Társaság", "01 09 352550", "1033 Budapest, Huszti út 60.", null, ct);
		comp = companyRepository.save(comp);
		
		emp = new Employee(9L, "B.Titkár1", pos1, 100000, LocalDateTime.of(2020, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(10L, "B.Titkár2", pos1, 100011, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		emp = new Employee(11L, "B.Tesztelő1", pos2, 200001, LocalDateTime.of(2021, 1, 1, 0, 0));
		emp.setCompany(comp);
		emp = employeeRepository.save(emp);
		
		
		
		
	}

}

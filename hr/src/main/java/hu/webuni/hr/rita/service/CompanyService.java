package hu.webuni.hr.rita.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.repository.EmployeeRepository;

@Service
public class CompanyService {
	@Autowired CompanyRepository companyRepository;
	@Autowired EmployeeRepository employeeRepository;
	
	@Transactional
	public Company save(Company company) {
		companyRepository.save(company);
		return company;
	}
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id){
		return companyRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id){
		companyRepository.deleteById(id);
	}
	
	@Transactional
	public Company update(Company company) {
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company addEmployee(long id, Employee employee) {
		Company company = findById(id).get();
		company.addEmployee(employee);
		
		// companyRepository.save(company); --> csak cascade merge esetén működik
		employeeRepository.save(employee);
		
		return company;
	}
	
	@Transactional
	public Company deleteEmployee(long id, long employeeId) {
		Company company = findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	@Transactional
	public Company replaceEmployees(long id, List<Employee> employees) {
		Company company = findById(id).get();
		company.getEmployees().forEach(emp -> emp.setCompany(null));
		company.getEmployees().clear();
		
		employees.forEach(emp ->{
			company.addEmployee(emp);
			Employee savedEmployee = employeeRepository.save(emp);
			company.getEmployees()
				.set(company.getEmployees().size()-1, savedEmployee);
		});
		
		return company;
	}
	
	
}

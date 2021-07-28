package hu.webuni.hr.rita.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired CompanyRepository companyRepository;
	
	@Transactional
	public Company save(Company company) {
		companyRepository.save(company);
		return company;
	}
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public Company findById(long id){
		return companyRepository.getById(id);
	}
	
	@Transactional
	public void delete(long id){
		companyRepository.deleteById(id);
	}
	
	
}

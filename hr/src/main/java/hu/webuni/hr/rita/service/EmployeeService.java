package hu.webuni.hr.rita.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.repository.EmployeeRepository;

abstract public class EmployeeService {
	
	@Autowired EmployeeRepository employeeRepository;
	
	abstract public int getPayRaisePercent(Employee employee);
	
	@Transactional
	public Employee save(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public List<Employee> findByPosition(String position){
		return employeeRepository.findByPosition(position);
	}
	
	public List<Employee> findByNameStartsWithIgnoreCase(String name){
		return employeeRepository.findByNameStartsWithIgnoreCase(name);
	}
	
	public List<Employee> findByEmployedSinceBetween(LocalDateTime start, LocalDateTime end){
		return employeeRepository.findByEmployedSinceBetween(start, end);
	}
	
	public Employee findById(long id){
		return employeeRepository.getById(id);
	}
	
	@Transactional
	public void delete(long id){
		employeeRepository.deleteById(id);
	} 

}

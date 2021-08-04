package hu.webuni.hr.rita.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.repository.EmployeeRepository;

abstract public class AbstractEmployeeService implements EmployeeService{
	
	@Autowired EmployeeRepository employeeRepository;
	
	abstract public int getPayRaisePercent(Employee employee);
	
	@Transactional
	@Override
	public Employee save(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	
	@Override
	public List<Employee> findAll(){
		/*List<Employee> ret = new ArrayList<Employee>();
		Iterable<Employee> iterable = employeeRepository.findAll();
		iterable.forEach(ret::add);
		return ret;*/
		return employeeRepository.findAll();
	}
	
	public List<Employee> findByPosition(String position){
		return employeeRepository.findByPosition(position);
	}
	
	public List<Employee> findByNameStartsWithIgnoreCase(String name){
		return employeeRepository.findByNameStartsWithIgnoreCase(name);
	}
	
	public Page<Employee> findByEmployedSinceBetween(LocalDateTime start, LocalDateTime end, Pageable pageable){
		return employeeRepository.findByEmployedSinceBetween(start, end, pageable);
	}
	
	public Optional<Employee> findById(long id){
		return employeeRepository.findById(id);
	}
	
	@Transactional
	@Override
	public void delete(long id){
		employeeRepository.deleteById(id);
	} 
	
	@Transactional
	@Override
	public Employee update(Employee employee) {
		if(!employeeRepository.existsById(employee.getId()))
			return null;
		return employeeRepository.save(employee);
	}

}

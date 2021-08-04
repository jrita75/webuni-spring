package hu.webuni.hr.rita.service;

import java.util.List;

import hu.webuni.hr.rita.model.Employee;

public interface EmployeeService {
	public int getPayRaisePercent(Employee employee);
	public Employee save(Employee employee);
	public Employee update(Employee employee);
	public List<Employee> findAll();
	public void delete(long id);
	
}

package hu.webuni.hr.rita.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.webuni.hr.rita.model.Employee;

abstract public class EmployeeService {
	private Map<Long, Employee> employees = new HashMap<>();
	{
		employees.put(1L, new Employee(1L, "Egyeske", "titkárnő", 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employees.put(2L, new Employee(2L, "Ketteske", "tesztelő", 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employees.put(3L, new Employee(3L, "Hármaska", "tesztelő", 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employees.put(4L, new Employee(4L, "Négyeske", "tesztelő", 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employees.put(5L, new Employee(5L, "Ötöske", "tesztelő", 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
	}
	
	abstract public int getPayRaisePercent(Employee employee);
	
	public Employee save(Employee employee) {
		employees.put(employee.getId(), employee);
		return employee;
	}
	
	public List<Employee> findAll(){
		return new ArrayList<>(employees.values());
	}
	
	public Employee findById(long id){
		return employees.get(id);
	}
	
	public void delete(long id){
		employees.remove(id);
	} 

}

package hu.webuni.hr.rita.service;

import org.springframework.stereotype.Service;
import hu.webuni.hr.rita.model.Employee;

@Service
public class SalaryService {
	
	private AbstractEmployeeService employeeService;
	
	public SalaryService(AbstractEmployeeService employeeService) {
		//super();
		this.employeeService = employeeService;
	}
	
	public void setNewSalary(Employee employee) {
		employee.setSalary((int)(employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee))));
	}
	
	public int getPayRaisePercent(Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}

}

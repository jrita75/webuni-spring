package hu.webuni.hr.rita.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.service.SalaryService;

@RestController
@RequestMapping("/api/pay_raise_percent")
public class SalaryController{
	
	@Autowired
	private SalaryService salaryService;
	
	@GetMapping
	public int getPayRaisePercent(@RequestBody Employee employee) {
		
		return salaryService.getPayRaisePercent(employee);
	}

}

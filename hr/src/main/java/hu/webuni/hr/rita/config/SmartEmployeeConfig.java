package hu.webuni.hr.rita.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.rita.service.EmployeeService;
import hu.webuni.hr.rita.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployeeConfig {
	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService(); 
	}
}

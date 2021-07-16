package hu.webuni.hr.rita.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.config.HrConfigProperties;
import hu.webuni.hr.rita.model.Employee;

@Service
public class DefaultEmployeeService extends EmployeeService {
/*
	@Value("${hr.def.percent}")
	private int percent;
*/
	@Autowired
	private HrConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		// return percent;
		return config.getDef().getPercent();
	}
	
}

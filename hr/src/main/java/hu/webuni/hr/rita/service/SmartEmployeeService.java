package hu.webuni.hr.rita.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.config.HrConfigProperties;
import hu.webuni.hr.rita.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService  {

	/*@Value("${hr.smart.limit1}")
	private double limit1;
	
	@Value("${hr.smart.limit2}")
	private double limit2;
	
	@Value("${hr.smart.limit3}")
	private double limit3;
	
	@Value("${hr.smart.percent1}")
	private int percent1;
	
	@Value("${hr.smart.percent2}")
	private int percent2;
	
	@Value("${hr.smart.percent3}")
	private int percent3;
	
	@Value("${hr.smart.percent4}")
	private int percent4;*/
	
	@Autowired
	private HrConfigProperties config;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		double years = employee.getEmployedYears();
		int percent;
		/*if (years >= limit1) { 
			percent = percent1;
		} else if (years >= limit2) {
			percent = percent2;
		} else if (years >= limit3) {
			percent = percent3;
		} else {
			percent = percent4;
		}*/
		if (years >= config.getSmart().getLimit1()) { 
			percent = config.getSmart().getPercent1();
		} else if (years >= config.getSmart().getLimit2()) {
			percent = config.getSmart().getPercent2();
		} else if (years >= config.getSmart().getLimit3()) {
			percent = config.getSmart().getPercent3();
		} else {
			percent = config.getSmart().getPercent4();
		}
		return percent;
	}

}

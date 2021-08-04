package hu.webuni.hr.rita.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.rita.config.HrConfigProperties;
import hu.webuni.hr.rita.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService  {

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
		/*
		if (years >= config.getSmart().getLimit1()) { 
			percent = config.getSmart().getPercent1();
		} else if (years >= config.getSmart().getLimit2()) {
			percent = config.getSmart().getPercent2();
		} else if (years >= config.getSmart().getLimit3()) {
			percent = config.getSmart().getPercent3();
		} else {
			percent = config.getSmart().getPercent4();
		}
		*/
		float limits[] = config.getSmart().getLimits();
		int percents[] = config.getSmart().getPercents();
		
		percent = config.getSmart().getPercent();
		if (limits.length == percents.length) 
		{
			float old_limit = -1;
			for(int i=0; i<limits.length; i++)
			{
				if ((i>0) & (limits[i]>=old_limit))
				{
					throw new java.lang.Error("Limits must be in a descending order");
				}
				old_limit = limits[i];
			}
			for(int i=0; i<limits.length; i++)
			{
				if (years>=limits[i]) {
					percent = percents[i];
					break;
				} 
			}
		} else {
			throw new java.lang.Error("Limits and percents with different lenght.");
		}
		
		return percent;
	}

}

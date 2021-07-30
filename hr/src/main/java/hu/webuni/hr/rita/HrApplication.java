package hu.webuni.hr.rita;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.model.PositionAvgSalary;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.service.InitDbService;
import hu.webuni.hr.rita.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private InitDbService initDbService;
	
	@Autowired 
	private CompanyRepository companyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		employeeList.add(new Employee(1L, "Egyeske", "titkárnő", 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employeeList.add(new Employee(2L, "Ketteske", "tesztelő", 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employeeList.add(new Employee(3L, "Hármaska", "tesztelő", 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employeeList.add(new Employee(4L, "Négyeske", "tesztelő", 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employeeList.add(new Employee(5L, "Ötöske", "tesztelő", 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
		
		for(Employee emp : employeeList)
		{
			emp.printData();
			salaryService.setNewSalary(emp);
			emp.printData();
		}
		
		initDbService.clearDb();
		initDbService.insertTestData();
	
		Consumer<Company> consumer = c -> { System.out.format("%s (%s)\n", c.getName(), c.getType()); };
		
		int salary = 300000;
		System.out.format("Van dolgozója, aki többet keres, mint %d:\n", salary);
		companyRepository.findWithEmployeeSalaryGreatherThan(salary).forEach(consumer);
		
		long count = 3;
		System.out.format("Több dolgozója van, mint %d:\n", count);
		companyRepository.findWithMoreEmployees(count).forEach(consumer);
		
		long comp_id; 
		List<Company> companies = companyRepository.findAll();
		Company comp = companies.get(companies.size()-1);
		comp_id = comp.getId();
		
		Consumer<PositionAvgSalary> consumer2 = p -> {System.out.format("%s: %f\n", p.getPosition(), p.getAvgSalary());};
		System.out.format("%s átlagfizetései pzíciónként\n", comp.getName());
		companyRepository.getAvgSalariesByPosition(comp_id).forEach(consumer2);
		
		
		
	}

}

package hu.webuni.hr.rita;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.model.Position;
import hu.webuni.hr.rita.model.PositionAvgSalary;
import hu.webuni.hr.rita.model.Qualification;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.repository.EmployeeRepository;
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
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		Position pos1 = new Position(1L, "titkárnő", Qualification.COLLEGE, 100000);
		Position pos2 = new Position(2L, "tesztelő", Qualification.GRADUATION, 100000);
		employeeList.add(new Employee(1L, "Egyeske", pos1, 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employeeList.add(new Employee(2L, "Ketteske", pos2, 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employeeList.add(new Employee(3L, "Hármaska", pos2, 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employeeList.add(new Employee(4L, "Négyeske", pos2, 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employeeList.add(new Employee(5L, "Ötöske", pos2, 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
		
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
		
		Consumer<PositionAvgSalary> consumer2 = p -> {System.out.format("%s: %f\n", p.getPosition().getName(), p.getAvgSalary());};
		System.out.format("%s átlagfizetései pzíciónként\n", comp.getName());
		companyRepository.getAvgSalariesByPosition(comp_id).forEach(consumer2);
		
		Consumer<Employee> consumer_emp = e -> { System.out.format("%s\n", e.getName());};
		LocalDateTime from_dt = LocalDateTime.of(2000, 1, 1, 0, 0);
		LocalDateTime to_dt = LocalDateTime.of(2021, 8, 1, 0, 0);
		Pageable pageable = PageRequest.of(0, 10, Sort.by("name").descending());
		
		System.out.format("Dolgozók %s - %s\n", from_dt.toString(), to_dt.toString());
		Page<Employee> page;
		
		
		do {
			page = employeeRepository.findByEmployedSinceBetween(from_dt, to_dt, pageable);
			System.out.println(page.toString());
			page.forEach(consumer_emp);
		} while (page.hasNext() && (pageable = page.nextPageable()) != null);
		
		
	
		
		
		
	}

}

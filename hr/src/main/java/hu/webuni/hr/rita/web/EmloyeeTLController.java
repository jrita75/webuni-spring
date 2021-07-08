package hu.webuni.hr.rita.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.rita.model.Employee;

@Controller
public class EmloyeeTLController {
	private List<Employee> employeeList = new ArrayList<>();
	{
		employeeList.add(new Employee(1L, "Egyeske", "titkárnő", 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employeeList.add(new Employee(2L, "Ketteske", "tesztelő", 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employeeList.add(new Employee(3L, "Hármaska", "tesztelő", 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employeeList.add(new Employee(4L, "Négyeske", "tesztelő", 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employeeList.add(new Employee(5L, "Ötöske", "tesztelő", 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
		
	}
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", employeeList);
		model.put("newEmployee", new Employee());
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		employeeList.add(employee);
		return "redirect:employees";
	}
	
	@GetMapping("/edit_employee")
	public String showEmployeetoEdit(@RequestParam long id, Map<String, Object> model) 
	{
		for(Employee emp : employeeList) {
			if (emp.getId()==id) {
				model.put("actEmployee", emp);
				break;
			}
		}
		return "edit_employee";
	}
	
	@PostMapping("/edit_employee")
	public String editEmployee(Employee employee) {
		long id = employee.getId();
		for(Employee emp : employeeList) {
			if (emp.getId()==id) {
				employeeList.set(employeeList.indexOf(emp), employee);
				break;
			}
		}
		return "redirect:employees";
	}
	
	@GetMapping("/delete_employee")
	public String showEmployeetoDelete(@RequestParam long id) 
	{
		for(Employee emp : employeeList) {
			if (emp.getId()==id) {
				employeeList.remove(emp);
				break;
			}
		}
		return "redirect:employees";
	}
}

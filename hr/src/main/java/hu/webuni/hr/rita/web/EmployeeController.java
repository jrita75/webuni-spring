package hu.webuni.hr.rita.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.mapper.EmployeeMapper;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	/*private Map<Long, EmployeeDto> employees = new HashMap<>();
	{
		employees.put(1L, new EmployeeDto(1L, "Egyeske", "titkárnő", 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employees.put(2L, new EmployeeDto(2L, "Ketteske", "tesztelő", 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employees.put(3L, new EmployeeDto(3L, "Hármaska", "tesztelő", 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employees.put(4L, new EmployeeDto(4L, "Négyeske", "tesztelő", 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employees.put(5L, new EmployeeDto(5L, "Ötöske", "tesztelő", 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
	}*/
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return employeeMapper.employeesToDtos( employeeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id)
	{
		Employee employee = employeeService.findById(id);
		ResponseEntity<EmployeeDto> ret;
		
		if (employee!=null) {
			ret = ResponseEntity.ok(employeeMapper.employeeToDto(employee));
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto)
	{
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return employeeMapper.employeeToDto(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto)
	{
		Employee employee_found = employeeService.findById(id);
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		ResponseEntity<EmployeeDto> ret;
		if (employee_found != null) {
			employee.setId(id);
			employeeService.save(employee);
			ret = ResponseEntity.ok(employeeMapper.employeeToDto(employee));
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		employeeService.delete(id);
	}
	
	@GetMapping(/*params="query=*"*/"/list")
	public List<EmployeeDto> getWithHigherSalary(@RequestParam("query") int salary)
	{
		
		List<EmployeeDto> ret = new ArrayList<>();
		for(Employee emp: employeeService.findAll()) {
			if (emp.getSalary() >= salary) {
				ret.add(employeeMapper.employeeToDto(emp));
			}
		}
		
		return ret;
	}
	
}

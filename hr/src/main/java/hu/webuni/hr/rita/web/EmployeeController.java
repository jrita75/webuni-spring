package hu.webuni.hr.rita.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
	
	@GetMapping("/position")
	public List<EmployeeDto> getWithPosition(@RequestParam("position") String position)
	{
		return employeeMapper.employeesToDtos(employeeService.findByPosition(position));
		
	}
	
	@GetMapping("/starts")
	public List<EmployeeDto> getNameStratsWith(@RequestParam("name") String name)
	{
		return employeeMapper.employeesToDtos(employeeService.findByNameStartsWithIgnoreCase(name));
		
	}
	
	@GetMapping("/between")
	public List<EmployeeDto> getWorksBeetween(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, 
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end)
	{
		return employeeMapper.employeesToDtos(employeeService.findByEmployedSinceBetween(start, end));
	}
	
}

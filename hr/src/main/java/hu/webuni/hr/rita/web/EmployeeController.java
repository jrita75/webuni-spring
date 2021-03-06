package hu.webuni.hr.rita.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import hu.webuni.hr.rita.repository.EmployeeRepository;
import hu.webuni.hr.rita.service.AbstractEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	AbstractEmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return employeeMapper.employeesToDtos( employeeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id)
	{
		Optional<Employee> employee_opt = employeeService.findById(id);
		ResponseEntity<EmployeeDto> ret;
		
		if (employee_opt.isPresent()) {
			ret = ResponseEntity.ok(employeeMapper.employeeToDto(employee_opt.get()));
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
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if(updatedEmployee == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		employeeService.delete(id);
	}
	
	@GetMapping("/list")
	public List<EmployeeDto> getWithHigherSalary(@RequestParam("query") Integer salary)
	{
		
		List<Employee> employees = null;
		if(salary == null) {
			employees = employeeService.findAll();
		} else {
			employees = employeeRepository.findBySalaryGreaterThan(salary);
		}
		return employeeMapper.employeesToDtos(employees);
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
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
			@RequestParam("page") int page,
			@RequestParam("size") int size
	)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> p = employeeService.findByEmployedSinceBetween(start, end, pageable);
		return employeeMapper.employeesToDtos(p.toList());
	}
	
}

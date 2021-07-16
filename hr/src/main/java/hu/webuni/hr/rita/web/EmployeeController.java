package hu.webuni.hr.rita.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private Map<Long, EmployeeDto> employees = new HashMap<>();
	{
		employees.put(1L, new EmployeeDto(1L, "Egyeske", "titkárnő", 250000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		employees.put(2L, new EmployeeDto(2L, "Ketteske", "tesztelő", 250000, LocalDateTime.of(2018, 1, 1, 0, 0)));
		employees.put(3L, new EmployeeDto(3L, "Hármaska", "tesztelő", 250000, LocalDateTime.of(2017, 1, 1, 0, 0)));
		employees.put(4L, new EmployeeDto(4L, "Négyeske", "tesztelő", 250000, LocalDateTime.of(2010, 1, 1, 0, 0)));
		employees.put(5L, new EmployeeDto(5L, "Ötöske", "tesztelő", 250000, LocalDateTime.of(2018, 11, 1, 0, 0)));
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employees.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable long id)
	{
		EmployeeDto employeeDto = employees.get(id);
		ResponseEntity<EmployeeDto> ret;
		if (employeeDto!=null) {
			ret = ResponseEntity.ok(employeeDto);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto)
	{
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto)
	{
		ResponseEntity<EmployeeDto> ret;
		if (employees.containsKey(id)) {
			employeeDto.setId(id);
			employees.put(id, employeeDto); // replace
			ret = ResponseEntity.ok(employeeDto);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		employees.remove(id);
	}
	
	@GetMapping(/*params="query=*"*/"/list")
	public List<EmployeeDto> getWithHigherSalary(@RequestParam("query") int salary)
	{
		
		List<EmployeeDto> ret = new ArrayList<>();
		for(EmployeeDto emp: employees.values()) {
			if (emp.getSalary() >= salary) {
				ret.add(emp);
			}
		}
		
		return ret;
	}
	
}

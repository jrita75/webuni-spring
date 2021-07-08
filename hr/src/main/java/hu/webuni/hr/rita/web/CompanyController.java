package hu.webuni.hr.rita.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

import hu.webuni.hr.rita.dto.CompanyDto;
import hu.webuni.hr.rita.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	private Map<Long, CompanyDto> companies = new HashMap<>();
	{
		CompanyDto comp;
		Map<Long, EmployeeDto> emplist;
		
		comp = new CompanyDto(1L, "FORMATUM Korlátolt Felelősségű Társaság", "13 09 115040", "2009 Pilisszentlászló, Tölgyfa utca 26.");
		emplist= comp.getEmployees();
		emplist.put(1L, new EmployeeDto(1L, "Egyeske", "titkár", 150000, LocalDateTime.of(2020, 1, 1, 0, 0)));
		comp.setEmployees(emplist);
		companies.put(1L, comp);
		
		comp = new CompanyDto(2L, "HAJÓRÉV Kereskedelmi, Szolgáltató és Idegenforgalmi Korlátolt Felelősségű Társaság", "13 09 062199", "2170 Aszód, Kossuth Lajos utca 13.");
		companies.put(2L, comp);
		
		comp = new CompanyDto(3L, "BioTech USA Korlátolt Felelősségű Társaság", "01 09 352550", "1033 Budapest, Huszti út 60.");
		companies.put(3L, comp);
		
	}
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false, defaultValue = "false") boolean full) {
		List<CompanyDto> ret = new ArrayList<>();
		for(CompanyDto comp : companies.values()) {
			CompanyDto newcomp = new CompanyDto(comp.getId(), comp.getName(), comp.getReg_number(), comp.getAddress());
			if (full) newcomp.setEmployees(comp.getEmployees()); // reference is enough
			ret.add(newcomp);
		}
		return ret;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id, @RequestParam(required = false, defaultValue = "false") boolean full){
		ResponseEntity<CompanyDto> ret;
		CompanyDto comp = companies.get(id);
		if(comp==null) {
			ret=ResponseEntity.notFound().build();
		} else {
			CompanyDto newcomp = new CompanyDto(comp.getId(), comp.getName(), comp.getReg_number(), comp.getAddress());
			if (full) newcomp.setEmployees(comp.getEmployees()); // reference is enough
			ret=ResponseEntity.ok(newcomp);
		}
		return ret;
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto)
	{
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<CompanyDto> createEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto)
	{
		ResponseEntity<CompanyDto> ret;
		if (companies.containsKey(id)) {
			CompanyDto companyDto = companies.get(id);
			companyDto.setId(id);
			companyDto.getEmployees().put(employeeDto.getId(), employeeDto);
			companies.put(id, companyDto);
			ret = ResponseEntity.ok(companyDto);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
		ResponseEntity<CompanyDto> ret;
		if (companies.containsKey(id)) {
			companyDto.setId(id);
			companies.put(id, companyDto);
			ret = ResponseEntity.ok(companyDto);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@PutMapping("/{id}/employees")
	public ResponseEntity<CompanyDto> modifyEmployeeList(@PathVariable long id, @RequestBody List<EmployeeDto> emplist){
		ResponseEntity<CompanyDto> ret;
		if (companies.containsKey(id)) {
			CompanyDto comp = companies.get(id);
			
			/*Map<Long, EmployeeDto> employees = new HashMap<>();
			for(EmployeeDto emp : emplist) {
				employees.put(emp.getId(), emp);
			}*/
			
			Map<Long, EmployeeDto> employees = 
					emplist.stream().
					collect(Collectors.toMap(EmployeeDto::getId, Function.identity()));
			
			comp.setEmployees(employees);
			ret = ResponseEntity.ok(comp);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);	
	}
	
	@DeleteMapping("/{id}/{emp_id}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id, @PathVariable long emp_id) {
		ResponseEntity<CompanyDto> ret;
		if (companies.containsKey(id)) {
			CompanyDto comp = companies.get(id);
			comp.getEmployees().remove(emp_id);
			ret = ResponseEntity.ok(comp);
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
}

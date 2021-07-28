package hu.webuni.hr.rita.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import hu.webuni.hr.rita.dto.CompanyDto;
import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.mapper.CompanyMapper;
import hu.webuni.hr.rita.mapper.EmployeeMapper;
import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;
import hu.webuni.hr.rita.service.CompanyService;
import hu.webuni.hr.rita.service.EmployeeService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	/*private Map<Long, CompanyDto> companies = new HashMap<>();
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
		
	}*/
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false, defaultValue = "false") boolean full) {
		List<CompanyDto> ret = new ArrayList<>();
		List<Company> companies = companyService.findAll();
		for(Company comp : companies) {
			CompanyDto newcomp = companyMapper.companyToDto(comp);
			if (!full) newcomp.setEmployees(null);
			ret.add(newcomp);
		}
		return ret;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id, @RequestParam(required = false, defaultValue = "false") boolean full){
		ResponseEntity<CompanyDto> ret;
		Company comp = companyService.findById(id);
		if(comp==null) {
			ret=ResponseEntity.notFound().build();
		} else {
			CompanyDto newcomp = companyMapper.companyToDto(comp);
			if (!full) newcomp.setEmployees(null);
			ret=ResponseEntity.ok(newcomp);
		}
		return ret;
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto)
	{
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<CompanyDto> createEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto)
	{
		ResponseEntity<CompanyDto> ret;
		Company comp = companyService.findById(id);
		if(comp==null) {
			ret = ResponseEntity.notFound().build();
		} else {
			
			Employee emp = employeeMapper.dtoToEmployee(employeeDto);
			emp.setCompany(comp);
			comp.getEmployees().put(employeeDto.getId(), emp);
			CompanyDto companyDto = companyMapper.companyToDto(comp);
			employeeService.save(emp);
			//companyService.save(comp);
			
			ret = ResponseEntity.ok(companyDto);
		}
		
		return ret;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
		ResponseEntity<CompanyDto> ret;
		Company company_found = companyService.findById(id);
		Company company = companyMapper.dtoToCompany(companyDto);
		if (company_found != null) {
			company.setId(id);
			companyService.save(company);
			ret = ResponseEntity.ok(companyMapper.companyToDto(company));
		} else {
			ret = ResponseEntity.notFound().build();
		}
		return ret;
	}
	
	@PutMapping("/{id}/employees")
	public ResponseEntity<CompanyDto> modifyEmployeeList(@PathVariable long id, @RequestBody List<EmployeeDto> emplist){
		ResponseEntity<CompanyDto> ret;
		Company comp = companyService.findById(id);
		if(comp==null) {
			ret = ResponseEntity.notFound().build();
		} else {
			Map<Long, Employee> employees = 
					emplist.stream().
					collect(Collectors.toMap(EmployeeDto::getId, employeeMapper::dtoToEmployee));
			
			
			employees.entrySet().stream().forEach(e -> {
				e.getValue().setCompany(comp);
				employeeService.save(e.getValue());
			});
			
			comp.setEmployees(employees);
			// companyService.save(comp);
			ret = ResponseEntity.ok(companyMapper.companyToDto(comp));
			
		}
		
		return ret;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
	@DeleteMapping("/{id}/{emp_id}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id, @PathVariable long emp_id) {
		ResponseEntity<CompanyDto> ret;
		Company comp = companyService.findById(id);
		if(comp==null) {
			ret = ResponseEntity.notFound().build();
		} else {
			comp.getEmployees().remove(emp_id);
			employeeService.delete(emp_id);
			ret = ResponseEntity.ok(companyMapper.companyToDto(comp));
		}
		
		return ret;
	}
	
}

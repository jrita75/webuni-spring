package hu.webuni.hr.rita.web;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.rita.dto.CompanyDto;
import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.mapper.CompanyMapper;
import hu.webuni.hr.rita.mapper.EmployeeMapper;
import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.repository.CompanyRepository;
import hu.webuni.hr.rita.service.CompanyService;
import hu.webuni.hr.rita.service.AbstractEmployeeService;

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
	AbstractEmployeeService employeeService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false, defaultValue = "false") Boolean full) {
		boolean notFull = full != null && full;
		List<Company> companies = null;
		if(notFull) {
			companies = companyService.findAll();
			return companyMapper.companiesToDtos(companies);
		} else {
			companyRepository.findAllWithEmployees();
			return companyMapper.companiesToSummaryDtos(companies);
		}
	}
	
	@GetMapping("/{id}")
	public CompanyDto getCompanyById(@PathVariable long id, @RequestParam(required = false, defaultValue = "false") Boolean full){
		CompanyDto newcomp;
		Company comp = findByIdOrThrow(id);
		
		if(full != null && full) {	
			newcomp = companyMapper.companyToDto(comp);
		} else {
			newcomp = companyMapper.companyToSummaryDto(comp);
		}
			
		return newcomp;
	}
	
	private Company findByIdOrThrow(long id) {
		return companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto)
	{
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		return companyMapper.companyToDto(company);
	}
	
	@PostMapping("/{id}")
	public CompanyDto createEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto)
	{
		try {
			return companyMapper.companyToDto(
					companyService.addEmployee(id, employeeMapper.dtoToEmployee(employeeDto))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto){
		companyDto.setId(id);
		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if (updatedCompany == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(companyMapper.companyToDto(updatedCompany));
	}
	
	@PutMapping("/{id}/employees")
	public CompanyDto modifyEmployeeList(@PathVariable long id, @RequestBody List<EmployeeDto> emplist){
		try {
			return companyMapper.companyToDto(
					companyService.replaceEmployees(id, employeeMapper.dtosToEmployees(emplist))
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companyService.delete(id);
	}
	
	@DeleteMapping("/{id}/{emp_id}")
	public CompanyDto deleteEmployee(@PathVariable long id, @PathVariable long emp_id) {
		try {
			return companyMapper.companyToDto(
					companyService.deleteEmployee(id, emp_id)
					);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
}

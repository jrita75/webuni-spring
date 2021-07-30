package hu.webuni.hr.rita.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.rita.dto.CompanyDto;
import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companyToDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);
	
	@Mapping(target = "employees" , ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> company);
	
	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	@Mapping(target = "company", ignore = true)
	Employee dtoToEmployee(EmployeeDto employeeDto);
}

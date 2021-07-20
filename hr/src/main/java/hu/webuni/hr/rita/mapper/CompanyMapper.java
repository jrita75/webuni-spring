package hu.webuni.hr.rita.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.rita.dto.CompanyDto;
import hu.webuni.hr.rita.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
	public List<CompanyDto> companiesToDtos(List<Company> companies);

	public CompanyDto companyToDto(Company company);

	public Company dtoToCompany(CompanyDto companyDto);
}

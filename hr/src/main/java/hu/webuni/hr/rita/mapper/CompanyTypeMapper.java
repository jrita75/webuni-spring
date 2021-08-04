package hu.webuni.hr.rita.mapper;

import org.mapstruct.Mapper;

import hu.webuni.hr.rita.dto.CompanyTypeDto;
import hu.webuni.hr.rita.model.CompanyType;

@Mapper(componentModel = "spring")
public interface CompanyTypeMapper {
	CompanyTypeDto companyTypetoDto(CompanyType companyType);
	CompanyType dtoTocompanyType(CompanyTypeDto companyTypeDto);
	
}

package hu.webuni.hr.rita.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
}

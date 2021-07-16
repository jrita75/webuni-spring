package hu.webuni.hr.rita.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	public List<EmployeeDto> employeesToDtos(List<Employee> employees);

	public EmployeeDto employeeToDto(Employee employee);

	public Employee dtoToEmployee(EmployeeDto employeeDto);
}

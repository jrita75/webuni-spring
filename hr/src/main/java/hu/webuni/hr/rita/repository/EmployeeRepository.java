package hu.webuni.hr.rita.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hu.webuni.hr.rita.model.Employee;
@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findBySalaryGreaterThan(Integer minSalary);
	
	List<Employee> findByPosition(String position);
	List<Employee> findByNameStartsWithIgnoreCase(String name);
	List<Employee> findByEmployedSinceBetween(LocalDateTime start, LocalDateTime end);

}

package hu.webuni.hr.rita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import hu.webuni.hr.rita.model.Company;
import hu.webuni.hr.rita.model.PositionAvgSalary;
@Component
public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query("SELECT DISTINCT c FROM Company c INNER JOIN Employee e ON e.company.id=c.id AND e.salary > :salary")
	List<Company> findWithEmployeeSalaryGreatherThan(int salary);
	
	@Query("SELECT c FROM Company c INNER JOIN Employee e ON e.company.id=c.id GROUP BY c HAVING COUNT(e.id) > :count")
	List<Company> findWithMoreEmployees(long count);
	
	@Query("SELECT new hu.webuni.hr.rita.model.PositionAvgSalary(e.position, AVG(e.salary)) FROM Employee e WHERE e.company.id=:id GROUP BY e.position ORDER BY AVG(e.salary) DESC")
	List<PositionAvgSalary> getAvgSalariesByPosition(long id);
	
	
}

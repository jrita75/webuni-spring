package hu.webuni.hr.rita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hu.webuni.hr.rita.model.Company;
@Component
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

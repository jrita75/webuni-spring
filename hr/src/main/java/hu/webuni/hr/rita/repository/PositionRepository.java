package hu.webuni.hr.rita.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import hu.webuni.hr.rita.model.Position;
@Component
public interface PositionRepository extends JpaRepository<Position, Long>{
	Position findByName(String name);
}

package hu.webuni.airport.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.repository.AirportRepository;

@Service
public class AirportService {
	
	AirportRepository airportRepository;
	
	public AirportService(AirportRepository airportRepository) {
		super();
		this.airportRepository = airportRepository;
	}

//	@PersistenceContext
//	EntityManager em;
	
	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata(), null);
		// em.persist(airport);
		return airportRepository.save(airport);
		
	}
	
	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata(), airport.getId());
//		em.merge(airport);
		if (airportRepository.existsById(airport.getId()))
			return airportRepository.save(airport);
		else
			throw new NoSuchElementException();
		
	}
	
	private void checkUniqueIata(String iata, Long id) {
		boolean forUpdate = (id!= null);
		long count = 
				forUpdate ? 
				airportRepository.countByIataAndIdNot(iata, id) : 
				airportRepository.countByIata(iata);
//		TypedQuery<Long> query = em.createNamedQuery((forUpdate ? "Airport.countByIataAndIdnotIn" : "Airport.countByIata"), Long.class)
//			.setParameter("iata", iata);
//		if (forUpdate) 
//			query.setParameter("id", id);
//		Long count = query
//			.getSingleResult();
//		
		
		if (count > 0) {
			throw new NonUniqueIataException(iata);
		}
	}
	
	public List<Airport> findAll(){
//		return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
		return airportRepository.findAll();
	}
	
	public Optional<Airport> findById(long id){
//		return em.find(Airport.class, id);
		return airportRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id){
//		em.remove(findById(id));
		airportRepository.deleteById(id);
	}
}


package hu.webuni.airport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;

@SpringBootTest
@AutoConfigureTestDatabase

public class AirportServiceIT {
	@Autowired
	AirportService airportService;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@BeforeEach
	public void Init() {
		flightRepository.deleteAll();
		airportRepository.deleteAll();
	}
	
	@Test
	void testCreateFlight() throws Exception {
			
		String flightNumber = "ABC123";
		long takeoff = createAirport("airport1", "iata1");
		long landing = createAirport("airport2", "iata2");
		LocalDateTime dateTime = LocalDateTime.now();
		long flightId = createFlight(flightNumber, takeoff, landing, dateTime);
		
		Optional<Flight> savedFlightOptional = flightRepository.findById(flightId);
		
		assertThat(savedFlightOptional).isNotEmpty();
		Flight savedFlight = savedFlightOptional.get();
		assertThat(savedFlight.getFlightNumber()).isEqualTo(flightNumber);
		assertThat(savedFlight.getTakeoffTime()).isCloseTo(dateTime, within(1, ChronoUnit.MICROS));
		assertThat(savedFlight.getTakeoff().getId()).isEqualTo(takeoff);
		assertThat(savedFlight.getLanding().getId()).isEqualTo(landing);
		// assertThat(savedFlight.getTakeoff()).isEqualTo()
		
	}

	private long createAirport(String name, String iata) {
		return airportRepository.save(new Airport(name, iata)).getId();
	}
	
	@Test
	void testFindFlightsByExample() throws Exception {
		long airportId1 = createAirport("airport1", "iata1");
		long airportId2 = createAirport("airport2", "iata2");
		long airportId3 = createAirport("airport3", "2iata");
		createAirport("airport4", "3ata1");
		LocalDateTime takeoff = LocalDateTime.of(2021, 8, 11, 8, 0, 0);
		long flightId1 = createFlight("ABC123", airportId1, airportId3, takeoff);
		long flightId2 = createFlight("ABC1234", airportId2, airportId3, takeoff.plusHours(2));
		createFlight("BC123", airportId1, airportId3, takeoff);
		createFlight("ABC123", airportId1, airportId3, takeoff.plusDays(1));
		createFlight("ABC123", airportId3, airportId3, takeoff);
		
		Flight example = new Flight();
		example.setFlightNumber("ABC123");
		example.setTakeoff(new Airport("sasa", "iata"));
		// example.setLanding(null);
		example.setTakeoffTime(takeoff);
		
		List<Flight> foundFlights = this.airportService.findFlightsByExample(example);
		assertThat(foundFlights
				.stream()
				.map(Flight::getId)
				.collect(Collectors.toList())).containsExactly(flightId1, flightId2);
		
		
	}

	private long createFlight(String flightNumber, long takeoff, long landing, LocalDateTime dateTime) {
		 return airportService.createFlight(flightNumber, takeoff, landing, dateTime).getId();
		
	}

	
}

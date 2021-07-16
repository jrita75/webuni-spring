package hu.webuni.airport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.webuni.airport.dto.AirportDto;
import hu.webuni.airport.model.Airport;
@Mapper(componentModel = "spring")
public interface AirportMapper {
	public List<AirportDto> airportsToDtos(List<Airport> airports);

	public AirportDto airportToDto(Airport airport);

	public Airport dtoToAirport(AirportDto airportDto);
}


package hu.webuni.hr.rita.mapper;

import org.mapstruct.Mapper;

import hu.webuni.hr.rita.dto.PositionDto;
import hu.webuni.hr.rita.model.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {
	PositionDto positionToDto(Position position);
	Position dtoToPosition(PositionDto positionDto);
}

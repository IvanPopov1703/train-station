package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.RailwayStationDto;
import ru.trainstation.data.entity.RailwayStation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RailwayStationMapper {

    private final CityMapper cityMapper;

    public RailwayStationMapper(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public RailwayStationDto toRailwayStationDto(RailwayStation railwayStation) {
        return new RailwayStationDto(
                railwayStation.getRailwayStationId(),
                railwayStation.getStationName(),
                railwayStation.getStationAddress(), cityMapper.toCityDto(railwayStation.getCity()));
    }

    public List<RailwayStationDto> toRailwayStationDto(List<RailwayStation> railwayStation) {
        var result = new ArrayList<RailwayStationDto>();
        railwayStation.stream()
                .map(this::toRailwayStationDto)
                .collect(Collectors.toList());
        return result;
    }

    public RailwayStation toRailwayStationFromDto(RailwayStationDto railwayStationDto) {
        return new RailwayStation(
                railwayStationDto.getRailwayStationId(),
                railwayStationDto.getName(),
                railwayStationDto.getAddress(),
                cityMapper.toCityFromCityDto(railwayStationDto.getCity()));
    }
}

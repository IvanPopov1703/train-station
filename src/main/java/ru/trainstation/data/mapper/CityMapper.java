package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.CityDto;
import ru.trainstation.data.dto.RoleDto;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    public CityDto toCityDto(City city){
        return new CityDto(city.getCityId(), city.getName());
    }

    public List<CityDto> toCityDto(List<City> cities) {
        return cities.stream().map(this::toCityDto).collect(Collectors.toList());
    }

    public City toCityFromCityDto(CityDto cityDto) {
        return new City(cityDto.getCityId(), cityDto.getName());
    }
}

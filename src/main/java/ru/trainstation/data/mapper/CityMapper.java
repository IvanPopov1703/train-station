package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.CityDto;
import ru.trainstation.data.entity.City;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    public CityDto toCityDto(City city){
        return new CityDto(city.getName());
    }

    public List<CityDto> toCityDto(List<City> cities) {
        return cities.stream().map(this::toCityDto).collect(Collectors.toList());
    }
}

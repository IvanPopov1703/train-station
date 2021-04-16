package ru.trainstation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.trainstation.data.dto.CityDto;
import ru.trainstation.data.mapper.CityMapper;
import ru.trainstation.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;

    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<CityDto> getAllCity() {
        return cityMapper.toCityDto(cityService.getAllCities());
    }
}

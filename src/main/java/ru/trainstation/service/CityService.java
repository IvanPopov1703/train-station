package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.repository.CityRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        var result = new ArrayList<City>();
        cityRepository.findAll().forEach(result::add);
        return result;
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(NotFountException::new);
    }
}

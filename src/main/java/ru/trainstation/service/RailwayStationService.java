package ru.trainstation.service;

import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.entity.RailwayStation;
import ru.trainstation.data.repository.RailwayStationRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RailwayStationService {

    private final RailwayStationRepository railwayStationRepository;

    public RailwayStationService(RailwayStationRepository railwayStationRepository) {
        this.railwayStationRepository = railwayStationRepository;
    }

    public List<RailwayStation> getAllRailwayStations() {
        var result = new ArrayList<RailwayStation>();
        railwayStationRepository.findAll().forEach(result::add);
        return result;
    }

    public RailwayStation getRailwayStationById(Long id) {
        return railwayStationRepository.findById(id).orElseThrow(NotFountException::new);
    }
}

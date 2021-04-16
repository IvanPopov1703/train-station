package ru.trainstation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.trainstation.data.entity.RailwayStation;
import ru.trainstation.service.RailwayStationService;

import java.util.List;

@RestController
@RequestMapping("/api/railwayStation")
public class RailwayStationController {

    private final RailwayStationService railwayStationService;

    public RailwayStationController(RailwayStationService railwayStationService) {
        this.railwayStationService = railwayStationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public List<RailwayStation> getAllRailwayStation() {
        return railwayStationService.getAllRailwayStations();
    }
}
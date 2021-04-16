package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainStops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainStopsId;
    private LocalTime plannedDepartureTime;
    private LocalTime plannedArrivalTime;

    @ManyToOne()
    @JoinColumn(name = "routeId")
    @JsonIgnoreProperties("trainStopsList")
    private Route route;

    @ManyToOne()
    @JoinColumn(name = "railwayStationId")
    @JsonIgnoreProperties("trainStopsList")
    private RailwayStation railwayStation;
}

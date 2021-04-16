package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RailwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long railwayStationId;
    private String stationName;
    private String stationAddress;

    @ManyToOne()
    @JoinColumn(name = "cityId")
    @JsonIgnoreProperties("railwayStationList")
    private City city;

    @OneToMany(mappedBy = "from")
    @JsonIgnoreProperties("from")
    private List<Route> fromList;

    @OneToMany(mappedBy = "to")
    @JsonIgnoreProperties("to")
    private List<Route> toList;

    @OneToMany(mappedBy = "railwayStation")
    @JsonIgnoreProperties("railwayStation")
    private List<TrainStops> trainStopsList;
}

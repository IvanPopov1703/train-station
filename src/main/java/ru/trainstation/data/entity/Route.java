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
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;

    @ManyToOne()
    @JoinColumn(name = "fromId")
    @JsonIgnoreProperties("fromList")
    private RailwayStation from;

    @ManyToOne()
    @JoinColumn(name = "toId")
    @JsonIgnoreProperties("toList")
    private RailwayStation to;

    @OneToMany(mappedBy = "route")
    @JsonIgnoreProperties("route")
    private List<TrainStops> trainStopsList;

    @OneToMany(mappedBy = "route")
    @JsonIgnoreProperties("route")
    private List<Schedule> scheduleList;
}

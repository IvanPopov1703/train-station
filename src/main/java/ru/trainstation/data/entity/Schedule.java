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
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne()
    @JoinColumn(name = "routeId")
    @JsonIgnoreProperties("scheduleList")
    private RailwayStation route;

    @OneToMany(mappedBy = "schedule")
    @JsonIgnoreProperties("schedule")
    private List<Ticket> ticketList;

    @OneToMany(mappedBy = "schedule")
    @JsonIgnoreProperties("schedule")
    private List<RailwayCarriageSchedule> railwayCarriageScheduleList;
}

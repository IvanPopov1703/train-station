package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RailwayCarriageSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long railwayCarriageScheduleId;

    @ManyToOne()
    @JoinColumn(name = "scheduleId")
    @JsonIgnoreProperties("railwayCarriageScheduleList")
    private Schedule schedule;

    @ManyToOne()
    @JoinColumn(name = "railwayCarriageId")
    @JsonIgnoreProperties("railwayCarriageList")
    private RailwayCarriage railwayCarriage;
}

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
public class RailwayCarriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long railwayCarriageId;
    private Integer num;
    private Integer numberOfSeats;

    @ManyToOne()
    @JoinColumn(name = "typeRailwayCarriageId")
    @JsonIgnoreProperties("railwayCarriageList")
    private TypeRailwayCarriage typeRailwayCarriage;

    @OneToMany(mappedBy = "railwayCarriage")
    @JsonIgnoreProperties("railwayCarriage")
    private List<RailwayCarriageSchedule> railwayCarriageScheduleList;

    @OneToMany(mappedBy = "railwayCarriage")
    @JsonIgnoreProperties("railwayCarriage")
    private List<Seat> seatList;
}

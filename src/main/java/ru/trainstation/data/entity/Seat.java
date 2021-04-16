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
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne()
    @JoinColumn(name = "seatTypeId")
    @JsonIgnoreProperties("seatList")
    private SeatType seatType;

    @ManyToOne()
    @JoinColumn(name = "railwayCarriageId")
    @JsonIgnoreProperties("seatList")
    private RailwayCarriage railwayCarriage;

    @OneToMany(mappedBy = "seat")
    @JsonIgnoreProperties("seat")
    private List<Ticket> ticketList;
}

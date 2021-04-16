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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne()
    @JoinColumn(name = "seatId")
    @JsonIgnoreProperties("ticketList")
    private Seat seat;

    @ManyToOne()
    @JoinColumn(name = "clientId")
    @JsonIgnoreProperties("ticketList")
    private Client client;

    @ManyToOne()
    @JoinColumn(name = "scheduleId")
    @JsonIgnoreProperties("ticketList")
    private Schedule schedule;
}

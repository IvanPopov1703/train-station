package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String login;
    private String password;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;

    @ManyToOne()
    @JoinColumn(name = "roleId")
    @JsonIgnoreProperties("clientList")
    private Role role;

    @ManyToOne()
    @JoinColumn(name = "discountId")
    @JsonIgnoreProperties("clientList")
    private Discount discount;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    private List<Ticket> ticketList;
}

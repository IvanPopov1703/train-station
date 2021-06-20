package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatTypeId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    public SeatType(Long seatTypeId,
                    @NotEmpty(message = "Название роли не может быть пустым!")
                    @Size(message = "Минимум 2 символа!", min = 2) String name
    ) {
        this.seatTypeId = seatTypeId;
        this.name = name;
    }

    @OneToMany(mappedBy = "seatType")
    @JsonIgnoreProperties("seatType")
    private List<Seat> seatList;
}

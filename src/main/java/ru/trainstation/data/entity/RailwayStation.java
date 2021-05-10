package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RailwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long railwayStationId;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле дожно содержать от 2 до 145 символов!", min = 2, max = 145)
    private String stationName;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле дожно содержать от 2 до 145 символов!", min = 2, max = 145)
    private String stationAddress;

    @NotNull(message = "Поле не можеты быть пустым!")
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

    public RailwayStation(Long railwayStationId,
                          String stationName,
                          String stationAddress,
                          City city
    ) {
        this.railwayStationId = railwayStationId;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.city = city;
    }
}

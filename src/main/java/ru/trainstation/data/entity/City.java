package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    @OneToMany(mappedBy = "city")
    @JsonIgnoreProperties("city")
    private List<RailwayStation> railwayStationList;

    public City(Long cityId,
                @NotEmpty(message = "Название роли не может быть пустым!")
                @Size(message = "Минимум 2 символа!", min = 2) String name
    ) {
        this.cityId = cityId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId) &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, name);
    }
}
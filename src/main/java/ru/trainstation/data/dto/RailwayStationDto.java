package ru.trainstation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RailwayStationDto {

    private Long railwayStationId;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле дожно содержать от 2 до 145 символов!", min = 2, max = 145)
    private String name;

    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле дожно содержать от 2 до 145 символов!", min = 2, max = 145)
    private String address;

    @NotNull(message = "Поле не можеты быть пустым!")
    private CityDto city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RailwayStationDto that = (RailwayStationDto) o;
        return Objects.equals(railwayStationId, that.railwayStationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(railwayStationId, name, address);
    }
}

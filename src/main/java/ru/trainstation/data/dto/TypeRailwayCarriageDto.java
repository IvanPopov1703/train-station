package ru.trainstation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeRailwayCarriageDto {

    private Long typeRailwayCarriageId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeRailwayCarriageDto that = (TypeRailwayCarriageDto) o;
        return Objects.equals(typeRailwayCarriageId, that.typeRailwayCarriageId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeRailwayCarriageId, name);
    }
}

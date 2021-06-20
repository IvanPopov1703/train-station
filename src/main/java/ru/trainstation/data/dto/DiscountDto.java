package ru.trainstation.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {

    private Long discountId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение!")
    private Integer percent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountDto that = (DiscountDto) o;
        return Objects.equals(discountId, that.discountId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(percent, that.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, name, percent);
    }
}

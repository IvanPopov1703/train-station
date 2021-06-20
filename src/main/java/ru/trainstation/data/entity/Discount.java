package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    @Min(value = 0, message = "Поле не может содержать отрицательное значение!")
    private Integer percent;

    @OneToMany(mappedBy = "discount")
    @JsonIgnoreProperties("discount")
    private List<Client> clientList;

    public Discount(
            Long discountId,
            @NotEmpty(message = "Название роли не может быть пустым!")
            @Size(message = "Минимум 2 символа!", min = 2)
                    String name, @Min(value = 0,
            message = "Поле не может содержать отрицательное значение!")
                    Integer percent
    ) {
        this.discountId = discountId;
        this.name = name;
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(discountId, discount.discountId) &&
                Objects.equals(name, discount.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, name);
    }
}

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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotEmpty(message = "Название роли не может быть пустым!")
    @Size(message = "Минимум 2 символа!", min = 2)
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("role")
    private List<Client> clientList;

    public Role(@NotEmpty(message = "Название роли не может быть пустым!")
                @Size(message = "Минимум 2 символа!", min = 2) String name
    ) {
        this.name = name;
    }

    public Role(Long roleId,
                @NotEmpty(message = "Название роли не может быть пустым!")
                @Size(message = "Минимум 2 символа!", min = 2) String name
    ) {
        this.roleId = roleId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name);
    }
}

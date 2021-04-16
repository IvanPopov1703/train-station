package ru.trainstation.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeRailwayCarriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeRailwayCarriageId;
    private String name;

    @OneToMany(mappedBy = "typeRailwayCarriage")
    @JsonIgnoreProperties("typeRailwayCarriage")
    private List<RailwayCarriage> railwayCarriageList;
}

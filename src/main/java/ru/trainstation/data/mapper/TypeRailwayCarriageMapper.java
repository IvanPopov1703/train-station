package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.TypeRailwayCarriageDto;
import ru.trainstation.data.entity.TypeRailwayCarriage;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeRailwayCarriageMapper {

    public TypeRailwayCarriageDto toTypeRailwayCarriageDto(TypeRailwayCarriage typeRailwayCarriage){
        return new TypeRailwayCarriageDto(
                typeRailwayCarriage.getTypeRailwayCarriageId(),
                typeRailwayCarriage.getName()
        );
    }

    public List<TypeRailwayCarriageDto> toTypeRailwayCarriageDto(List<TypeRailwayCarriage> cities) {
        return cities.stream().map(this::toTypeRailwayCarriageDto).collect(Collectors.toList());
    }

    public TypeRailwayCarriage toTypeRailwayCarriageFromTypeRailwayCarriageDto(
            TypeRailwayCarriageDto typeRailwayCarriageDto
    ) {
        return new TypeRailwayCarriage(
                typeRailwayCarriageDto.getTypeRailwayCarriageId(),
                typeRailwayCarriageDto.getName()
        );
    }
}

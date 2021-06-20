package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.SeatTypeDto;
import ru.trainstation.data.entity.SeatType;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatTypeMapper {

    public SeatTypeDto toSeatTypeDto(SeatType seatType){
        return new SeatTypeDto(seatType.getSeatTypeId(), seatType.getName());
    }

    public List<SeatTypeDto> toSeatTypeDto(List<SeatType> cities) {
        return cities.stream().map(this::toSeatTypeDto).collect(Collectors.toList());
    }

    public SeatType toSeatTypeFromSeatTypeDto(SeatTypeDto seatTypeDto) {
        return new SeatType(seatTypeDto.getSeatTypeId(), seatTypeDto.getName());
    }
}

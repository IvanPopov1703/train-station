package ru.trainstation.data.mapper;

import org.springframework.stereotype.Component;
import ru.trainstation.data.dto.DiscountDto;
import ru.trainstation.data.entity.Discount;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountMapper {

    public DiscountDto toDiscountDto(Discount discount){
        return new DiscountDto(discount.getDiscountId(), discount.getName(), discount.getPercent());
    }

    public List<DiscountDto> toDiscountDto(List<Discount> cities) {
        return cities.stream().map(this::toDiscountDto).collect(Collectors.toList());
    }

    public Discount toDiscountFromDiscountDto(DiscountDto discountDto) {
        return new Discount(discountDto.getDiscountId(), discountDto.getName(), discountDto.getPercent());
    }
}

package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.Seat;
import ru.trainstation.data.entity.SeatType;

@Repository
public interface SeatTypeRepository extends PagingAndSortingRepository<SeatType, Long> {
}

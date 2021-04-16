package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.Schedule;
import ru.trainstation.data.entity.Seat;

@Repository
public interface SeatRepository extends PagingAndSortingRepository<Seat, Long> {
}

package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.Ticket;
import ru.trainstation.data.entity.TrainStops;

@Repository
public interface TrainStopsRepository extends PagingAndSortingRepository<TrainStops, Long> {
}

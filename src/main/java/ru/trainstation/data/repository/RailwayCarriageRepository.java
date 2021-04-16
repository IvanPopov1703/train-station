package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.entity.RailwayCarriage;

@Repository
public interface RailwayCarriageRepository extends PagingAndSortingRepository<RailwayCarriage, Long> {
}

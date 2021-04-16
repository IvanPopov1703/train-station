package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.RailwayStation;

@Repository
public interface RailwayStationRepository extends PagingAndSortingRepository<RailwayStation, Long> {
}

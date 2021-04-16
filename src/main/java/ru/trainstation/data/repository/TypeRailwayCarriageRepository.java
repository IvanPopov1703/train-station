package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.TrainStops;
import ru.trainstation.data.entity.TypeRailwayCarriage;

@Repository
public interface TypeRailwayCarriageRepository extends PagingAndSortingRepository<TypeRailwayCarriage, Long> {
}

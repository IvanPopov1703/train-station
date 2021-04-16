package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
}

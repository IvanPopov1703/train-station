package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.Route;
import ru.trainstation.data.entity.Schedule;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {
}

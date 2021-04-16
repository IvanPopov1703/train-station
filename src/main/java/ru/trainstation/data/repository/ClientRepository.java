package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.entity.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
}

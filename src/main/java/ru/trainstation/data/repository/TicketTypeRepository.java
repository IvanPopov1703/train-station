package ru.trainstation.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trainstation.data.entity.SeatType;
import ru.trainstation.data.entity.Ticket;

@Repository
public interface TicketTypeRepository extends PagingAndSortingRepository<Ticket, Long> {
}

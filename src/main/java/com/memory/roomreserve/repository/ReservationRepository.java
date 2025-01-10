package com.memory.roomreserve.repository;

import com.memory.roomreserve.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}

package com.voronina.core.repositories;

import com.voronina.api.domain.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookingRepository extends CrudRepository<Booking, String> {
    Optional<Booking> findByName(String name);
}

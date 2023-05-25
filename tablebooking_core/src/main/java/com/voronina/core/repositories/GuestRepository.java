package com.voronina.core.repositories;

import com.voronina.api.domain.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GuestRepository extends CrudRepository<Guest, String> {
    Optional<Guest> findByName(String name);
}

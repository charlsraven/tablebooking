package com.voronina.core.repositories;

import com.voronina.api.domain.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, String> {
}

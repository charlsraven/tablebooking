package com.voronina;

import com.voronina.api.domain.Place;

public interface PlaceService {

    Place create(Place place);

    Iterable<Place> readAll();

    Place readOne(String id);

    Place update(Place place);

    void delete(String id);
}

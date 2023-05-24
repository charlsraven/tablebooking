package com.voronina;

import com.voronina.api.domain.Guest;

public interface GuestService {

    Guest create(Guest guest);

    Iterable<Guest> readAll();

    Guest readOne(String id);

    Guest update(Guest guest);

    void delete(String id);
}

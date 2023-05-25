package com.voronina.api;

import com.voronina.api.domain.Booking;

public interface BookingService {
    Booking create(Booking booking);

    Iterable<Booking> readAll();

    Booking readOne(String id);

    Booking update(Booking booking);

    void delete(String id);

    //why
    Booking readOneByName(String name);
}
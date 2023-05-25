package com.voronina.core.services;

import com.voronina.api.BookingService;
import com.voronina.api.domain.Booking;
import com.voronina.api.exceptions.notfound.BookingNotFoundException;
import com.voronina.api.exceptions.services.BookingServiceException;
import com.voronina.core.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking create(Booking booking) {
        log.debug("Creating {}", booking);
        try {
            var createdBooking = bookingRepository.save(booking);
            log.debug("Created {}", createdBooking);
            return createdBooking;
        } catch (Exception exception) {
            throw new BookingServiceException("Error while creating %s, cause: %s"
                    .formatted(booking, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Booking> readAll() {
        log.debug("Finding all");
        try {
            var bookings = bookingRepository.findAll();
            log.debug("Found {}", bookings);
            return bookings;
        } catch (Exception exception) {
            throw new BookingServiceException("Error while reading all, cause: %s"
                    .formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var booking = bookingRepository.findById(id).orElseThrow(() ->
                    new BookingNotFoundException("Booking with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, booking);
            return booking;
        } catch (Exception exception) {
            throw new BookingServiceException("Error while read booking by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking update(Booking booking) {
        log.debug("Updating {}", booking);
        try {
            var updatedLecture = this.bookingRepository;
            log.debug("Updated {}", updatedLecture);
            return updatedLecture.save(booking);
        } catch (Exception exception) {
            throw new BookingServiceException("Error while updating %s, cause: %s"
                    .formatted(booking, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            bookingRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new BookingServiceException("Error while deleting by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Booking readOneByName(String name) {
        log.debug("Finding by name={}", name);
        try {
            var lecture = bookingRepository.findByName(name).orElseThrow(() ->
                    new BookingNotFoundException("Booking with name=%s not found".formatted(name)));
            log.debug("Found by name={}, {}", name, lecture);
            return lecture;
        } catch (Exception exception) {
            throw new BookingServiceException("Error while read booking by name=%s, cause: %s"
                    .formatted(name, exception.getMessage()), exception);
        }
    }
}

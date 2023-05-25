package com.voronina.core.services;

import com.voronina.api.GuestService;
import com.voronina.api.domain.Guest;
import com.voronina.api.exceptions.notfound.GuestNotFoundException;
import com.voronina.api.exceptions.services.GuestServiceException;
import com.voronina.core.repositories.GuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Guest create(Guest guest) {
        log.debug("Creating {}", guest);
        try {
            var createdGuest = guestRepository.save(guest);
            log.debug("Created {}", createdGuest);
            return createdGuest;
        } catch (Exception exception) {
            throw new GuestServiceException("Error while creating %s, cause: %s"
                    .formatted(guest, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Guest> readAll() {
        log.debug("Finding all");
        try {
            var guests = guestRepository.findAll();
            log.debug("Found {}", guests);
            return guests;
        } catch (Exception exception) {
            throw new GuestServiceException("Error while reading all, cause: %s"
                    .formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Guest readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var guest = guestRepository.findById(id).orElseThrow(() ->
                    new GuestNotFoundException("Guest with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, guest);
            return guest;
        } catch (Exception exception) {
            throw new GuestServiceException("Error while read guest by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Guest update(Guest guest) {
        log.debug("Updating {}", guest);
        try {
            var updatedLecture = this.guestRepository;
            log.debug("Updated {}", updatedLecture);
            return updatedLecture.save(guest);
        } catch (Exception exception) {
            throw new GuestServiceException("Error while updating %s, cause: %s"
                    .formatted(guest, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            guestRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new GuestServiceException("Error while deleting by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Guest readOneByName(String name) {
        log.debug("Finding by name={}", name);
        try {
            var lecture = guestRepository.findByName(name).orElseThrow(() ->
                    new GuestNotFoundException("Lecture with name=%s not found".formatted(name)));
            log.debug("Found by name={}, {}", name, lecture);
            return lecture;
        } catch (Exception exception) {
            throw new GuestServiceException("Error while read lecture by name=%s, cause: %s".formatted(name, exception.getMessage()), exception);
        }
    }
}

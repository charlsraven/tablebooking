package com.voronina.core.services;

import com.voronina.api.PlaceService;
import com.voronina.api.domain.Place;
import com.voronina.api.exceptions.notfound.PlaceNotFoundException;
import com.voronina.api.exceptions.services.PlaceServiceException;
import com.voronina.core.repositories.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Place create(Place place) {
        log.debug("Creating {}", place);
        try {
            var createdPlace = placeRepository.save(place);
            log.debug("Created {}", createdPlace);
            return createdPlace;
        } catch (Exception exception) {
            throw new PlaceServiceException("Error while creating %s, cause: %s"
                    .formatted(place, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Place> readAll() {
        log.debug("Finding all");
        try {
            var places = placeRepository.findAll();
            log.debug("Found {}", places);
            return places;
        } catch (Exception exception) {
            throw new PlaceServiceException("Error while reading all, cause: %s"
                    .formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Place readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var place = placeRepository.findById(id).orElseThrow(() ->
                    new PlaceNotFoundException("Place with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, place);
            return place;
        } catch (Exception exception) {
            throw new PlaceServiceException("Error while read place by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Place update(Place place) {
        log.debug("Updating {}", place);
        try {
            var updatedLecture = this.placeRepository;
            log.debug("Updated {}", updatedLecture);
            return updatedLecture.save(place);
        } catch (Exception exception) {
            throw new PlaceServiceException("Error while updating %s, cause: %s"
                    .formatted(place, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            placeRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new PlaceServiceException("Error while deleting by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

}

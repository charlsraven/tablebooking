package com.voronina.core.services;

import com.voronina.api.StatusService;
import com.voronina.api.domain.Status;
import com.voronina.api.exceptions.notfound.StatusNotFoundException;
import com.voronina.api.exceptions.services.StatusServiceException;
import com.voronina.core.repositories.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Status create(Status status) {
        log.debug("Creating {}", status);
        try {
            var createdStatus = statusRepository.save(status);
            log.debug("Created {}", createdStatus);
            return createdStatus;
        } catch (Exception exception) {
            throw new StatusServiceException("Error while creating %s, cause: %s"
                    .formatted(status, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Iterable<Status> readAll() {
        log.debug("Finding all");
        try {
            var statuses = statusRepository.findAll();
            log.debug("Found {}", statuses);
            return statuses;
        } catch (Exception exception) {
            throw new StatusServiceException("Error while reading all, cause: %s"
                    .formatted(exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Status readOne(String id) {
        log.debug("Finding by id={}", id);
        try {
            var status = statusRepository.findById(id).orElseThrow(() ->
                    new StatusNotFoundException("Status with id=%s not found".formatted(id)));
            log.debug("Found by id={}, {}", id, status);
            return status;
        } catch (Exception exception) {
            throw new StatusServiceException("Error while read status by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Status update(Status status) {
        log.debug("Updating {}", status);
        try {
            var updatedLecture = this.statusRepository;
            log.debug("Updated {}", updatedLecture);
            return updatedLecture.save(status);
        } catch (Exception exception) {
            throw new StatusServiceException("Error while updating %s, cause: %s"
                    .formatted(status, exception.getMessage()), exception);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        log.debug("Deleting by id={}", id);
        try {
            statusRepository.deleteById(id);
            log.debug("Deleted by id={}", id);
        } catch (Exception exception) {
            throw new StatusServiceException("Error while deleting by id=%s, cause: %s"
                    .formatted(id, exception.getMessage()), exception);
        }
    }

}

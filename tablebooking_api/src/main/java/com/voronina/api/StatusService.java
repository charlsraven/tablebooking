package com.voronina.api;

import com.voronina.api.domain.Status;

public interface StatusService {

    Status create(Status status);

    Iterable<Status> readAll();

    Status readOne(String id);

    Status update(Status status);

    void delete(String id);
}

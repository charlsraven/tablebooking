package com.voronina.core.repositories;

import com.voronina.api.domain.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, String> {
}

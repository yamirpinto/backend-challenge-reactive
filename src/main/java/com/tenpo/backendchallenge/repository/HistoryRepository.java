package com.tenpo.backendchallenge.repository;


import com.tenpo.backendchallenge.repository.entities.HistoryEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends R2dbcRepository<HistoryEntity, UUID> {
}

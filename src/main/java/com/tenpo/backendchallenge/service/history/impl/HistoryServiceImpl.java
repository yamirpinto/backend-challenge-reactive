package com.tenpo.backendchallenge.service.history.impl;


import com.tenpo.backendchallenge.mapper.HistoryDataMapper;
import com.tenpo.backendchallenge.model.HistoryModel;
import com.tenpo.backendchallenge.repository.HistoryRepository;
import com.tenpo.backendchallenge.repository.entities.HistoryEntity;

import com.tenpo.backendchallenge.service.history.HistoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository repository;
    private final HistoryDataMapper mapper;

    @Override
    public Mono<HistoryModel> saveEntry(HistoryModel historyModel) {
        HistoryEntity entity = mapper.modelToEntity(historyModel);
        entity.setTimestamp(Instant.now());

        return repository.save(entity)
                .map(mapper::entityToModel);
    }

    @Override
    public Flux<HistoryModel> findAll(int page, int size) {
        return repository.findAll()
                .skip((long) page * size)
                .take(size)
                .map(mapper::entityToModel);
    }
}

package com.tenpo.backendchallenge.service.history;


import com.tenpo.backendchallenge.model.HistoryModel;
import com.tenpo.backendchallenge.repository.entities.HistoryEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HistoryService {

    Mono<HistoryModel> saveEntry(HistoryModel history);
    Flux<HistoryModel> findAll(int page, int size);
}

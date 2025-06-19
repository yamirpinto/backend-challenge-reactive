package com.tenpo.backendchallenge.controller.history;

import com.tenpo.backendchallenge.model.HistoryModel;
import reactor.core.publisher.Flux;

public interface HistoryController {
    Flux<HistoryModel> getHistory(int page, int size);
}

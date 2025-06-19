package com.tenpo.backendchallenge.controller.history.impl;

import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.controller.history.HistoryController;
import com.tenpo.backendchallenge.model.HistoryModel;
import com.tenpo.backendchallenge.service.history.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(ApplicationConstants.HISTORY_PATH)
@RequiredArgsConstructor
public class HistoryControllerImpl implements HistoryController {

    private final HistoryService historyService;

    @Override
    @Operation(summary = "Lista el historial de llamadas",
            description = "Devuelve un flujo paginado de registros de historial")
    @GetMapping
    public Flux<HistoryModel> getHistory(
            @Parameter(description = "Índice de página (0-based)")
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página")
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return historyService.findAll(page, size);
    }
}

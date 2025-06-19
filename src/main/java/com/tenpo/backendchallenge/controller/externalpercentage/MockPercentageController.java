package com.tenpo.backendchallenge.controller.externalpercentage;


import com.tenpo.backendchallenge.dto.response.PercentageResponse;
import reactor.core.publisher.Mono;

public interface MockPercentageController {
    Mono<PercentageResponse> getMockPercentage();
}

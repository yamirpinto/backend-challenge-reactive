package com.tenpo.backendchallenge.service.calculation;

import com.tenpo.backendchallenge.dto.request.CalculateRequest;
import com.tenpo.backendchallenge.dto.response.CalculateResponse;
import reactor.core.publisher.Mono;

public interface CalculationService {

    Mono<CalculateResponse> calculate(CalculateRequest request);
}

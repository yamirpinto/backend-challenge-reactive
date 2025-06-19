package com.tenpo.backendchallenge.controller.calculation;

import com.tenpo.backendchallenge.dto.request.CalculateRequest;
import com.tenpo.backendchallenge.dto.response.CalculateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface CalculationController {
    Mono<ResponseEntity<CalculateResponse>> calculate(@RequestBody CalculateRequest request);
}

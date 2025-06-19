package com.tenpo.backendchallenge.service.externalpercentage;

import reactor.core.publisher.Mono;

public interface ExternalPercentageService {

    Mono<Double> getPercentage();
}

package com.tenpo.backendchallenge.service.externalpercentage.impl;


import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.dto.response.PercentageResponse;
import com.tenpo.backendchallenge.service.externalpercentage.ExternalPercentageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalPercentageServiceImpl implements ExternalPercentageService {

    private final WebClient webClient;
    private final Cache<String, Double> percentageCache;

    @Value("${external.percentage.url}")
    private String percentageUrl;

    private static final String CACHE_KEY = "percentage";

    /*@Override
    public Mono<Double> getPercentage() {
        Double cached = percentageCache.getIfPresent(CACHE_KEY);
        if (cached != null) {
            return Mono.just(cached);
        }

        return webClient.get()
                .uri(percentageUrl)
                .retrieve()
                .bodyToMono(PercentageResponse.class)
                .map(PercentageResponse::getPercentage)
                .doOnNext(value -> percentageCache.put(CACHE_KEY, value))
                .onErrorResume(err -> {
                    Double last = percentageCache.getIfPresent(CACHE_KEY);
                    if (last != null) {
                        return Mono.just(last);
                    }
                    return Mono.error(new IllegalStateException(
                            "No hay porcentaje en cache y el servicio externo falló", err));
                });
    } */

    @Override
    public Mono<Double> getPercentage() {
        Double cached = percentageCache.getIfPresent(CACHE_KEY);
        if (cached != null) {
            return Mono.just(cached);
        }
        double pct = ApplicationConstants.FIXED_PERCENTAGE;
        percentageCache.put(CACHE_KEY, pct);
        return Mono.just(pct);
    }
}


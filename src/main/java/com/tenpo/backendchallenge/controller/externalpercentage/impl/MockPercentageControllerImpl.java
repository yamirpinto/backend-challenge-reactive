package com.tenpo.backendchallenge.controller.externalpercentage.impl;

import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.controller.externalpercentage.MockPercentageController;
import com.tenpo.backendchallenge.dto.response.PercentageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MockPercentageControllerImpl implements MockPercentageController {

    @Override
    @GetMapping("/mock-percentage")
    public Mono<PercentageResponse> getMockPercentage() {
        return Mono.just(new PercentageResponse(ApplicationConstants.FIXED_PERCENTAGE));
    }
}

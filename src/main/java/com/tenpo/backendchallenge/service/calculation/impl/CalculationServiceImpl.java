package com.tenpo.backendchallenge.service.calculation.impl;

import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.dto.request.CalculateRequest;
import com.tenpo.backendchallenge.dto.response.CalculateResponse;
import com.tenpo.backendchallenge.mapper.HistoryDataMapper;
import com.tenpo.backendchallenge.model.CalculateModel;
import com.tenpo.backendchallenge.model.HistoryModel;

import com.tenpo.backendchallenge.service.calculation.CalculationService;
import com.tenpo.backendchallenge.service.externalpercentage.ExternalPercentageService;
import com.tenpo.backendchallenge.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final ExternalPercentageService externalPercentageService;
    private final HistoryService historyService;
    private final HistoryDataMapper historyMapper;

    @Override
    public Mono<CalculateResponse> calculate(CalculateRequest request) {
        return externalPercentageService.getPercentage()
                .map(pct -> buildModel(request, pct))
                .map(this::buildResponse)
                .flatMap(response ->
                        historyService.saveEntry(mapToHistoryModel(request, response))
                                .thenReturn(response)
                );
    }

    private CalculateModel buildModel(CalculateRequest request, double percentage) {
        return CalculateModel.builder()
                .num1(request.getNum1())
                .num2(request.getNum2())
                .percentage(percentage)
                .build();
    }

    private CalculateResponse buildResponse(CalculateModel model) {
        double result = computeResult(
                model.getNum1(),
                model.getNum2(),
                model.getPercentage()
        );
        return CalculateResponse.builder()
                .num1(model.getNum1())
                .num2(model.getNum2())
                .percentageUsed(model.getPercentage())
                .result(result)
                .build();
    }

    private double computeResult(double num1, double num2, double percentage) {
        double sum = num1 + num2;
        return sum * (ApplicationConstants.BASE_MULTIPLIER
                + percentage / ApplicationConstants.PERCENTAGE_SCALE);
    }

    private HistoryModel mapToHistoryModel(CalculateRequest req, CalculateResponse resp) {
        return HistoryModel.builder()
                .endpoint(ApplicationConstants.CALCULATE_PATH)
                .requestBody(req.toString())
                .responseBody(resp.toString())
                .errorMessage(null)
                .build();
    }
}

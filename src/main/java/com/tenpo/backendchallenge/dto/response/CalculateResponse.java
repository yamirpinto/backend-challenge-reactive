package com.tenpo.backendchallenge.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculateResponse {
    private double num1;
    private double num2;
    private double percentageUsed;
    private double result;
}

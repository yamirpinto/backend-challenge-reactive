package com.tenpo.backendchallenge.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculateModel {
    private double num1;
    private double num2;
    private double percentage;
}

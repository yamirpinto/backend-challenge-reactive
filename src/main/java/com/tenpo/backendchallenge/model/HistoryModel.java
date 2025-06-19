package com.tenpo.backendchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryModel {

    private UUID id;
    private Instant timestamp;
    private String endpoint;
    private String requestBody;
    private String responseBody;
    private String errorMessage;
}

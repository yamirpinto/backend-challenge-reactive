package com.tenpo.backendchallenge.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("history")
public class HistoryEntity {

    @Id
    private UUID id;

    private Instant timestamp;
    private String endpoint;
    private String requestBody;
    private String responseBody;
    private String errorMessage;
}

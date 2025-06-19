package com.tenpo.backendchallenge.mapper;

import com.tenpo.backendchallenge.model.HistoryModel;
import com.tenpo.backendchallenge.repository.entities.HistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class HistoryDataMapper {

    public HistoryEntity modelToEntity(HistoryModel model) {
        return HistoryEntity.builder()
                .id(model.getId())
                .timestamp(model.getTimestamp())
                .endpoint(model.getEndpoint())
                .requestBody(model.getRequestBody())
                .responseBody(model.getResponseBody())
                .errorMessage(model.getErrorMessage())
                .build();
    }

    public HistoryModel entityToModel(HistoryEntity entity) {
        return HistoryModel.builder()
                .id(entity.getId())
                .timestamp(entity.getTimestamp())
                .endpoint(entity.getEndpoint())
                .requestBody(entity.getRequestBody())
                .responseBody(entity.getResponseBody())
                .errorMessage(entity.getErrorMessage())
                .build();
    }
}

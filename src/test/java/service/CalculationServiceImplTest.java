package service;

import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.dto.request.CalculateRequest;
import com.tenpo.backendchallenge.dto.response.CalculateResponse;
import com.tenpo.backendchallenge.model.HistoryModel;

import com.tenpo.backendchallenge.service.calculation.impl.CalculationServiceImpl;
import com.tenpo.backendchallenge.service.externalpercentage.ExternalPercentageService;
import com.tenpo.backendchallenge.service.history.HistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CalculationServiceImplTest {

    @Mock
    private ExternalPercentageService externalPercentageService;

    @Mock
    private HistoryService historyService;

    @InjectMocks
    private CalculationServiceImpl calculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculate_successfulCalculationAndHistorySaved() {
        CalculateRequest request = new CalculateRequest();
        request.setNum1(100);
        request.setNum2(50);
        double percentage = 10.0;
        double expectedResult = (100 + 50) * (1 + percentage / ApplicationConstants.PERCENTAGE_SCALE);

        when(externalPercentageService.getPercentage())
                .thenReturn(Mono.just(percentage));
        when(historyService.saveEntry(any(HistoryModel.class)))
                .thenReturn(Mono.just(HistoryModel.builder()
                        .endpoint(ApplicationConstants.CALCULATE_PATH)
                        .build()));

        Mono<CalculateResponse> responseMono = calculationService.calculate(request);

        StepVerifier.create(responseMono)
                .expectNextMatches(response ->
                        response.getNum1() == 100.0
                                && response.getNum2() == 50.0
                                && response.getPercentageUsed() == percentage
                                && response.getResult() == expectedResult
                )
                .verifyComplete();

        verify(externalPercentageService, times(1)).getPercentage();
        verify(historyService, times(1)).saveEntry(any(HistoryModel.class));
    }
}
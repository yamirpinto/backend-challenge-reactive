package service;

import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.service.externalpercentage.impl.ExternalPercentageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class ExternalPercentageServiceImplTest {

    @Mock
    private Cache<String, Double> percentageCache;

    @InjectMocks
    private ExternalPercentageServiceImpl service;

    private static final String CACHE_KEY = "percentage";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPercentage_cacheHit() {
        when(percentageCache.getIfPresent(CACHE_KEY)).thenReturn(20.0);

        StepVerifier.create(service.getPercentage())
                .expectNext(20.0)
                .verifyComplete();

        verify(percentageCache, never()).put(anyString(), anyDouble());
    }

    @Test
    void getPercentage_cacheMiss_thenCachePut() {
        when(percentageCache.getIfPresent(CACHE_KEY)).thenReturn(null);

        StepVerifier.create(service.getPercentage())
                .expectNext(ApplicationConstants.FIXED_PERCENTAGE)
                .verifyComplete();

        verify(percentageCache, times(1))
                .put(CACHE_KEY, ApplicationConstants.FIXED_PERCENTAGE);
    }
}

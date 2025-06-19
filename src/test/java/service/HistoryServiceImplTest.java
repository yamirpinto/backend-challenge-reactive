package service;

import com.tenpo.backendchallenge.mapper.HistoryDataMapper;
import com.tenpo.backendchallenge.model.HistoryModel;
import com.tenpo.backendchallenge.repository.HistoryRepository;
import com.tenpo.backendchallenge.repository.entities.HistoryEntity;
import com.tenpo.backendchallenge.service.history.impl.HistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HistoryServiceImplTest {

    @Mock
    private HistoryRepository repository;

    @Mock
    private HistoryDataMapper mapper;

    @InjectMocks
    private HistoryServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEntry_success() {
        HistoryModel inputModel = HistoryModel.builder()
                .endpoint("test/endpoint")
                .requestBody("reqBody")
                .responseBody("respBody")
                .errorMessage(null)
                .build();

        HistoryEntity mappedEntity = HistoryEntity.builder()
                .endpoint(inputModel.getEndpoint())
                .requestBody(inputModel.getRequestBody())
                .responseBody(inputModel.getResponseBody())
                .errorMessage(inputModel.getErrorMessage())
                .build();

        HistoryEntity savedEntity = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint(mappedEntity.getEndpoint())
                .requestBody(mappedEntity.getRequestBody())
                .responseBody(mappedEntity.getResponseBody())
                .errorMessage(mappedEntity.getErrorMessage())
                .build();

        HistoryModel outputModel = HistoryModel.builder()
                .id(savedEntity.getId())
                .timestamp(savedEntity.getTimestamp())
                .endpoint(savedEntity.getEndpoint())
                .requestBody(savedEntity.getRequestBody())
                .responseBody(savedEntity.getResponseBody())
                .errorMessage(savedEntity.getErrorMessage())
                .build();

        when(mapper.modelToEntity(inputModel)).thenReturn(mappedEntity);
        when(repository.save(any(HistoryEntity.class))).thenReturn(Mono.just(savedEntity));
        when(mapper.entityToModel(savedEntity)).thenReturn(outputModel);

        StepVerifier.create(service.saveEntry(inputModel))
                .expectNext(outputModel)
                .verifyComplete();

        verify(mapper).modelToEntity(inputModel);
        verify(repository).save(any(HistoryEntity.class));
        verify(mapper).entityToModel(savedEntity);
    }

    @Test
    void findAll_pagination_page0() {
        HistoryEntity e1 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e1")
                .requestBody("r1")
                .responseBody("s1")
                .errorMessage(null)
                .build();
        HistoryEntity e2 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e2")
                .requestBody("r2")
                .responseBody("s2")
                .errorMessage(null)
                .build();
        HistoryEntity e3 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e3")
                .requestBody("r3")
                .responseBody("s3")
                .errorMessage(null)
                .build();

        HistoryModel m1 = HistoryModel.builder()
                .id(e1.getId()).timestamp(e1.getTimestamp())
                .endpoint(e1.getEndpoint()).requestBody(e1.getRequestBody())
                .responseBody(e1.getResponseBody()).errorMessage(e1.getErrorMessage())
                .build();
        HistoryModel m2 = HistoryModel.builder()
                .id(e2.getId()).timestamp(e2.getTimestamp())
                .endpoint(e2.getEndpoint()).requestBody(e2.getRequestBody())
                .responseBody(e2.getResponseBody()).errorMessage(e2.getErrorMessage())
                .build();
        HistoryModel m3 = HistoryModel.builder()
                .id(e3.getId()).timestamp(e3.getTimestamp())
                .endpoint(e3.getEndpoint()).requestBody(e3.getRequestBody())
                .responseBody(e3.getResponseBody()).errorMessage(e3.getErrorMessage())
                .build();

        when(repository.findAll()).thenReturn(Flux.just(e1, e2, e3));
        when(mapper.entityToModel(e1)).thenReturn(m1);
        when(mapper.entityToModel(e2)).thenReturn(m2);
        when(mapper.entityToModel(e3)).thenReturn(m3);

        StepVerifier.create(service.findAll(0, 2))
                .expectNext(m1, m2)
                .verifyComplete();

        verify(repository).findAll();
    }

    @Test
    void findAll_pagination_page1() {
        HistoryEntity e1 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e1").requestBody("r1").responseBody("s1").errorMessage(null)
                .build();
        HistoryEntity e2 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e2").requestBody("r2").responseBody("s2").errorMessage(null)
                .build();
        HistoryEntity e3 = HistoryEntity.builder()
                .id(UUID.randomUUID())
                .timestamp(Instant.now())
                .endpoint("e3").requestBody("r3").responseBody("s3").errorMessage(null)
                .build();

        HistoryModel m1 = HistoryModel.builder()
                .id(e1.getId()).timestamp(e1.getTimestamp())
                .endpoint(e1.getEndpoint()).requestBody(e1.getRequestBody())
                .responseBody(e1.getResponseBody()).errorMessage(e1.getErrorMessage())
                .build();
        HistoryModel m2 = HistoryModel.builder()
                .id(e2.getId()).timestamp(e2.getTimestamp())
                .endpoint(e2.getEndpoint()).requestBody(e2.getRequestBody())
                .responseBody(e2.getResponseBody()).errorMessage(e2.getErrorMessage())
                .build();
        HistoryModel m3 = HistoryModel.builder()
                .id(e3.getId()).timestamp(e3.getTimestamp())
                .endpoint(e3.getEndpoint()).requestBody(e3.getRequestBody())
                .responseBody(e3.getResponseBody()).errorMessage(e3.getErrorMessage())
                .build();

        when(repository.findAll()).thenReturn(Flux.just(e1, e2, e3));
        when(mapper.entityToModel(e1)).thenReturn(m1);
        when(mapper.entityToModel(e2)).thenReturn(m2);
        when(mapper.entityToModel(e3)).thenReturn(m3);

        StepVerifier.create(service.findAll(1, 2))
                .expectNext(m3)
                .verifyComplete();

        verify(repository).findAll();
    }
}

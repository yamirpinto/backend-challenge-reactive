package com.tenpo.backendchallenge.controller.calculation.impl;


import com.tenpo.backendchallenge.common.constants.ApplicationConstants;
import com.tenpo.backendchallenge.controller.calculation.CalculationController;
import com.tenpo.backendchallenge.dto.request.CalculateRequest;
import com.tenpo.backendchallenge.dto.response.CalculateResponse;
import com.tenpo.backendchallenge.service.calculation.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApplicationConstants.CALCULATE_PATH)
@RequiredArgsConstructor
public class CalculationControllerImpl implements CalculationController {

    private final CalculationService calculationService;

    @Override
    @Operation(
            summary = "Calcula suma + porcentaje dinámico",
            description = "Recibe num1 y num2, obtiene porcentaje cacheado o mock, y devuelve el resultado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cálculo correcto"),
                    @ApiResponse(responseCode = "503", description = "Servicio externo no disponible")
            }
    )
    @PostMapping
    public Mono<ResponseEntity<CalculateResponse>> calculate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto con los números a sumar",
                    required = true
            )
            @Valid @RequestBody CalculateRequest request
    ) {
        return calculationService.calculate(request)
                .map(ResponseEntity::ok);
    }
}

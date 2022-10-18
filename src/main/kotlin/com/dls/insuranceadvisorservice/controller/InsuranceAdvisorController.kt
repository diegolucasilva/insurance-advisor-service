package com.dls.insuranceadvisorservice.controller

import com.dls.insuranceadvisorservice.dto.RiskProfileResponse
import com.dls.insuranceadvisorservice.dto.UserPersonalInformationRequest
import com.dls.insuranceadvisorservice.dto.toDomain
import com.dls.insuranceadvisorservice.dto.toResponse
import com.dls.insuranceadvisorservice.port.RiskScoreProcessorUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@Validated
@RestController
class InsuranceAdvisorController(val riskScoreProcessorUseCase: RiskScoreProcessorUseCase) {

    @Operation(summary = "Create a customized insurance profile for users' specific needs according to their entries", description = "Returns 201 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Successful Operation"),
        ]
    )
    @PostMapping("/insurance/advisor")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRiskProfile(@Valid @RequestBody userPersonalInformationRequest: UserPersonalInformationRequest): RiskProfileResponse? {
         val customerProfile = userPersonalInformationRequest.toDomain()
         return riskScoreProcessorUseCase.execute(customerProfile).toResponse()
    }

}
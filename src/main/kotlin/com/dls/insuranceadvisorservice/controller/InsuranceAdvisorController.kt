package com.dls.insuranceadvisorservice.controller

import com.dls.insuranceadvisorservice.controller.dto.*
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@Validated
@RestController
class InsuranceAdvisorController(val riskCalculatorUseCase: RiskCalculatorUseCase) {

    @PostMapping("/insurance/advisor")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUserProfile(@Valid @RequestBody userProfileDto: UserProfileRequest): RiskProfileResponse? {
         val customerProfile = userProfileDto.toDomain()
         val riskProfile = riskCalculatorUseCase.execute(customerProfile).toResponse()
        return riskProfile;
    }

}
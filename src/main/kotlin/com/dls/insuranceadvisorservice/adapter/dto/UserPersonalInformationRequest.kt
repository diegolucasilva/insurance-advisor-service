package com.dls.insuranceadvisorservice.adapter.dto

import com.dls.insuranceadvisorservice.adapter.validator.BooleanList
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserPersonalInformationRequest(
    @field:PositiveOrZero
    @get:NotNull
    val age: Int?,
    @field:PositiveOrZero
    @get:NotNull
    val dependents: Int?,
    val house: HouseRequest?,
    @field:PositiveOrZero
    @get:NotNull
    val income: Int?,
    val maritalStatus: UserRiskProfile.MaritalStatus,
    @field:BooleanList
    @field:Size(min = 3, max = 3)
    @field:NotNull
    val riskQuestions: List<Int>,
    val vehicle: VehicleRequest?,
    ) {
    data class VehicleRequest(
        @get:NotNull
        @field:Positive val year: Int?
        )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class HouseRequest(val ownershipStatus: UserRiskProfile.OwnershipStatus)

}

package com.dls.insuranceadvisorservice.controller.dto

import com.dls.insuranceadvisorservice.controller.validator.BooleanList
import com.dls.insuranceadvisorservice.domain.UserProfile
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserProfileRequest(
    @field:PositiveOrZero
    val age: Integer,
    @field:PositiveOrZero
    val dependents: Integer,
    val house: HouseRequest?,
    @field:PositiveOrZero
    val income: Integer,
    val maritalStatus: UserProfile.MaritalStatus,
    @field:BooleanList
    @field:Size(min = 3, max = 3)
    @field:NotNull
    val riskQuestions: List<Int>,
    val vehicle: VehicleRequest?,
    ) {
    data class VehicleRequest(@field:Positive val year: Integer)

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class HouseRequest(val ownershipStatus: UserProfile.OwnershipStatus)

}

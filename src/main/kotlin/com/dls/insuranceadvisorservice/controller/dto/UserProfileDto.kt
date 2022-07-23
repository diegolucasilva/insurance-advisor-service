package com.dls.insuranceadvisorservice.controller.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserProfileDto(
    @field:Positive
    val age: Integer,
    @field:PositiveOrZero
    val dependents: Integer,
    val house: House,
    @field:PositiveOrZero
    val income: Integer,
    val maritalStatus: MaritalStatus,
    val riskQuestions: List<Boolean>,
    val vehicle: Vehicle,

) {
    data class Vehicle(@field:Positive val year: Integer)

    enum class MaritalStatus { single,married }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class House(val ownershipStatus: OwnershipStatus)

    enum class OwnershipStatus { owned,mortgaged}
}

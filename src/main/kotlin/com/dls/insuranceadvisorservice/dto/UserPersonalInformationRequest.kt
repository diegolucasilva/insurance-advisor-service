package com.dls.insuranceadvisorservice.dto

import com.dls.insuranceadvisorservice.validator.BooleanList
import com.dls.insuranceadvisorservice.domain.customer.House
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@Schema(description = "User personal information")
data class UserPersonalInformationRequest(
    @field:Schema(description = "User age", example = "23", type = "int", minimum = "0")
    @field:PositiveOrZero
    @get:NotNull
    val age: Int?,
    @field:Schema(description = "The number of dependents", example = "2", type = "int", minimum = "0")
    @field:PositiveOrZero
    @get:NotNull
    val dependents: Int?,
    val house: HouseRequest?,
    @field:Schema(description = "The value of income", example = "200000", type = "int", minimum = "0")
    @field:PositiveOrZero
    @get:NotNull
    val income: Int?,
    val maritalStatus: Customer.MaritalStatus,
    @field:BooleanList
    @field:Size(min = 3, max = 3)
    @field:NotNull
    val riskQuestions: List<Int>,
    val vehicle: VehicleRequest?,
    ) {
    data class VehicleRequest(
        @field:Schema(description = "year the car vehicle was produced", example = "2018", type = "int", minimum = "0")
        @get:NotNull
        @field:Positive val year: Int?
        )

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class HouseRequest(
        val ownershipStatus: House.OwnershipStatus
        )

}

package com.dls.insuranceadvisorservice.adapter.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import io.swagger.v3.oas.annotations.media.Schema

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@Schema(description = "Risk profile response")
class RiskProfileResponse(
    @field:Schema(description = "risk profile for auto", example = "regular")
    val auto: String,
    @field:Schema(description = "risk profile for disability", example = "ineligible")
    val disability: String,
    @field:Schema(description = "risk profile for home", example = "economic")
    val home: String?,
    @field:Schema(description = "risk profile for life", example = "regular")
    val life: String,
    @field:Schema(description = "risk profile for umbrella", example = "regular")
    val umbrella: String,
    @field:Schema(description = "risk profile for renter", example = "economic")
    val renter: String?)

package com.dls.insuranceadvisorservice.adapter.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class RiskProfileResponse(
    val auto: String,
    val disability: String,
    val home: String,
    val life: String)

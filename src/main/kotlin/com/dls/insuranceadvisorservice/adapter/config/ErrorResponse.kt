package com.dls.insuranceadvisorservice.adapter.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ErrorResponse(val errorMessages: List<ErrorDescriptor>) {
    constructor(parameter_name: String? = null, description: String? = null) :
            this(listOf(ErrorDescriptor(parameter_name, description)))

    data class ErrorDescriptor(
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val parameterName: String?=null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val description: String?=null
    )
}
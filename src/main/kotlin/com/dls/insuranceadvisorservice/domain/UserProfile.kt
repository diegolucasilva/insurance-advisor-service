package com.dls.insuranceadvisorservice.domain

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.Positive

data class UserProfile(
    val age: Integer,
    val dependents: Integer,
    val income: Integer,
    val maritalStatus: MaritalStatus,
    val house: House?,
    val questionScore: Int,
    val vehicle: Vehicle?
){

    data class Vehicle(@field:Positive val year: Integer)

    enum class MaritalStatus { single,married }

    enum class OwnershipStatus {owned,mortgaged}


    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class House(val ownershipStatus: OwnershipStatus)

}
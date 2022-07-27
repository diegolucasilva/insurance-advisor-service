package com.dls.insuranceadvisorservice.domain

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.Positive

data class UserRiskProfile(
    val age: Int,
    val dependents: Int,
    val income: Int,
    val maritalStatus: MaritalStatus,
    val house: House?,
    val questionScore: Int,
    val vehicle: Vehicle?
){

    data class Vehicle(val year: Int)

    enum class MaritalStatus { single,married }

    enum class OwnershipStatus {owned,mortgaged}

    data class House(val ownershipStatus: OwnershipStatus)

}
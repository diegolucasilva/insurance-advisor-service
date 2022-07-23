package com.dls.insuranceadvisorservice.controller.dto

import com.dls.insuranceadvisorservice.domain.RiskProfile
import com.dls.insuranceadvisorservice.domain.UserProfile


fun UserProfileRequest.toDomain() = UserProfile(
        age = age,
        dependents = dependents,
        income = income,
        maritalStatus = maritalStatus,
        questionScore = riskQuestions.sum(),
        house = (house?.toDomain()),
        vehicle = (vehicle?.toDomain())
    )

fun UserProfileRequest.HouseRequest.toDomain() = UserProfile.House(
        ownershipStatus = ownershipStatus
        )

fun UserProfileRequest.VehicleRequest.toDomain() = UserProfile.Vehicle(
        year = year
        )

fun List<RiskProfile>.toResponse() = RiskProfileResponse(
        auto = this.first { it.insuranceLine ==RiskProfile.InsuranceLine.AUTO }.finalScoreStatus.name.lowercase(),
        disability = this.first { it.insuranceLine ==RiskProfile.InsuranceLine.DISABILITY}.finalScoreStatus.name.lowercase(),
        home =  " ",
        life =" "
        )


package com.dls.insuranceadvisorservice.adapter.dto

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile


fun UserPersonalInformationRequest.toDomain() = UserRiskProfile(
        age = age!!,
        dependents = dependents!!,
        income = income!!,
        maritalStatus = maritalStatus,
        questionScore = riskQuestions.sum(),
        house = (house?.toDomain()),
        vehicle = (vehicle?.toDomain())
    )

fun UserPersonalInformationRequest.HouseRequest.toDomain() = UserRiskProfile.House(
        ownershipStatus = ownershipStatus
        )

fun UserPersonalInformationRequest.VehicleRequest.toDomain() = UserRiskProfile.Vehicle(
        year = year!!
        )

fun List<RiskProfileLineInsurance>.toResponse() = RiskProfileResponse(
        auto = this.first { it.name ==RiskProfileLineInsurance.Name.AUTO }.finalScoreStatus.name.lowercase(),
        disability = this.first { it.name ==RiskProfileLineInsurance.Name.DISABILITY}.finalScoreStatus.name.lowercase(),
        home =  this.first { it.name ==RiskProfileLineInsurance.Name.HOME}.finalScoreStatus.name.lowercase(),
        life =this.first { it.name ==RiskProfileLineInsurance.Name.LIFE}.finalScoreStatus.name.lowercase(),
        )


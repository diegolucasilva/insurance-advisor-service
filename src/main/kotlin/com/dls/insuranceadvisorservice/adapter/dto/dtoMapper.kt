package com.dls.insuranceadvisorservice.adapter.dto

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile


fun UserPersonalInformationRequest.toDomain() = UserRiskProfile(
        age = age!!,
        dependents = dependents!!,
        income = income!!,
        maritalStatus = maritalStatus,
        questionScore = riskQuestions,
        house = (house?.toDomain()),
        vehicle = (vehicle?.toDomain())
    )

fun UserPersonalInformationRequest.HouseRequest.toDomain() = UserRiskProfile.House(
        ownershipStatus = ownershipStatus
        )

fun UserPersonalInformationRequest.VehicleRequest.toDomain() = UserRiskProfile.Vehicle(
        year = year!!
        )

fun List<RiskProfileForInsuranceLine>.toResponse() = RiskProfileResponse(
        auto = this.first { it.name ==RiskProfileForInsuranceLine.Name.AUTO }.insurancePlanStatus?.name!!.lowercase(),
        disability = this.first { it.name ==RiskProfileForInsuranceLine.Name.DISABILITY}.insurancePlanStatus?.name!!.lowercase(),
        home =  this.firstOrNull { it.name ==RiskProfileForInsuranceLine.Name.HOME}?.insurancePlanStatus?.name?.lowercase(),
        renter =  this.firstOrNull{ it.name ==RiskProfileForInsuranceLine.Name.RENTER}?.insurancePlanStatus?.name?.lowercase(),
        life =this.first { it.name ==RiskProfileForInsuranceLine.Name.LIFE}.insurancePlanStatus?.name!!.lowercase(),
        umbrella =this.first { it.name ==RiskProfileForInsuranceLine.Name.UMBRELLA}.insurancePlanStatus?.name!!.lowercase(),
        )


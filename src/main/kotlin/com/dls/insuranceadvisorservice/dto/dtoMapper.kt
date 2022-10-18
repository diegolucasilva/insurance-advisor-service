package com.dls.insuranceadvisorservice.dto

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.House
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.customer.Vehicle


fun UserPersonalInformationRequest.toDomain() = Customer(
        age = age!!,
        dependents = dependents!!,
        income = income!!,
        maritalStatus = maritalStatus,
        questionScore = riskQuestions,
        house = (house?.toDomain()),
        vehicle = (vehicle?.toDomain())
    )

fun UserPersonalInformationRequest.HouseRequest.toDomain() = House(
        ownershipStatus = ownershipStatus
        )

fun UserPersonalInformationRequest.VehicleRequest.toDomain() = Vehicle(
        year = year!!
        )

fun List<RiskProfileForInsuranceLine>.toResponse() = RiskProfileResponse(
        auto = this.first { it.name == RiskProfileForInsuranceLine.Name.AUTO }.insurancePlanStatus?.name!!.lowercase(),
        home =  this.firstOrNull { it.name == RiskProfileForInsuranceLine.Name.HOME}?.insurancePlanStatus?.name?.lowercase(),
        life =this.first { it.name == RiskProfileForInsuranceLine.Name.LIFE}.insurancePlanStatus?.name!!.lowercase(),
        )


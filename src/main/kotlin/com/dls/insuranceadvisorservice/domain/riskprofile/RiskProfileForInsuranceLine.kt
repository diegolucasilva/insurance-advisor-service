package com.dls.insuranceadvisorservice.domain.riskprofile

class RiskProfileForInsuranceLine(
    val name: Name,
    var score: Int,
    var insurancePlanStatus: InsurancePlanStatus?=null
    ) {

    enum class Name {AUTO, HOME, LIFE}
    enum class InsurancePlanStatus {REGULAR, INELIGIBLE, ECONOMIC, RESPONSIBLE}
}


package com.dls.insuranceadvisorservice.domain

class RiskProfileForInsuranceLine(
    val name: Name,
    var score: Int,
    var insurancePlanStatus: InsurancePlanStatus?=null
    ) {

    fun incrementScore(value: Int=1){
        score += value
    }
    fun decrementScore(value: Int=1){
        score -= value
    }
    fun makeIneligible(){
        insurancePlanStatus = InsurancePlanStatus.INELIGIBLE
    }
    fun calculatePlanBasedOnScore(){
        if(insurancePlanStatus!= InsurancePlanStatus.INELIGIBLE){
            insurancePlanStatus = when {
                score <= 0 -> InsurancePlanStatus.ECONOMIC
                score in (1..2) -> InsurancePlanStatus.REGULAR
                else -> InsurancePlanStatus.RESPONSIBLE
            }
        }
    }
    enum class Name { AUTO, DISABILITY, HOME, LIFE, UMBRELLA}
    enum class InsurancePlanStatus {REGULAR, INELIGIBLE, ECONOMIC, RESPONSIBLE}
}


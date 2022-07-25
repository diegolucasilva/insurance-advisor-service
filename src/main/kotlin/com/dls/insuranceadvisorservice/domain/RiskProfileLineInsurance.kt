package com.dls.insuranceadvisorservice.domain

class RiskProfileLineInsurance(
    val name: Name,
    var score: Int,
    var finalScoreStatus: FinalScoreStatus?=null
    ) {

    fun incrementScore(value: Int=1){
        score += value
    }
    fun decrementScore(value: Int=1){
        score -= value
    }
    fun makeIneligible(){
        finalScoreStatus = FinalScoreStatus.INELIGIBLE
    }
    fun calculatePlanBasedOnScore(){
        if(finalScoreStatus!= FinalScoreStatus.INELIGIBLE){
            finalScoreStatus = when {
                score <= 0 -> FinalScoreStatus.ECONOMIC
                score in (1..2) -> FinalScoreStatus.REGULAR
                else -> FinalScoreStatus.RESPONSIBLE
            }
        }
    }
    enum class Name { AUTO, DISABILITY, HOME, LIFE}
    enum class FinalScoreStatus {REGULAR, INELIGIBLE, ECONOMIC, RESPONSIBLE}
}


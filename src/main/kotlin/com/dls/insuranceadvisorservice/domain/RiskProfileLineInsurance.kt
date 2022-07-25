package com.dls.insuranceadvisorservice.domain

class RiskProfileLineInsurance(
    val name: Name?,
    var score: Int,
    var finalScoreStatus: FinalScoreStatus
    ) {
    constructor() : this(null,0, FinalScoreStatus.NOTCALCULATED)


    fun calculatePlanBasedOnScore(){
        if(finalScoreStatus!= FinalScoreStatus.INELIGIBLE){
            when {
                score <= 0 -> finalScoreStatus = FinalScoreStatus.ECONOMIC
                score in (1..2) -> finalScoreStatus = FinalScoreStatus.REGULAR
                else -> finalScoreStatus= FinalScoreStatus.RESPONSIBLE
            }
        }
    }


    enum class Name { AUTO, DISABILITY, HOME, LIFE}
    enum class FinalScoreStatus { NOTCALCULATED, REGULAR, INELIGIBLE, ECONOMIC, RESPONSIBLE}
}


package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import java.time.LocalDate

class VehicleProductionDateRule: RiskScoreRule {

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(wasProducedInTheLastFiveYear(userRiskProfile.vehicle?.year)){
            riskProfileForInsuranceLine.incrementScore()
        }
    }
    private fun wasProducedInTheLastFiveYear(year: Int?): Boolean{
        if(year!=null){
            return (LocalDate.now().year - year.toInt()) <= 5
        }
        return false
    }

}
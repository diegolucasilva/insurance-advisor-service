package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import java.time.LocalDate

class VehicleProductionDateRule: RiskScoreRule {

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(wasProducedInTheLastFiveYear(userRiskProfile.vehicle?.year)){
            riskProfileLineInsurance.incrementScore()
        }
    }
    private fun wasProducedInTheLastFiveYear(year: Int?): Boolean{
        if(year!=null){
            return (LocalDate.now().year - year.toInt()) <= 5
        }
        return false
    }

}
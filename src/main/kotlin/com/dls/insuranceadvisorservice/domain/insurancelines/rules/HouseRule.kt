package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class HouseRule: RiskScoreRule {

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(userRiskProfile.house==null){
            riskProfileForInsuranceLine.makeIneligible()
        }
    }
}
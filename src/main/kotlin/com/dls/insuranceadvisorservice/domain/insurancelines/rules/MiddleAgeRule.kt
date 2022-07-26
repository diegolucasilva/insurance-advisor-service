package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class MiddleAgeRule : RiskScoreRule {

    override fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(userRiskProfile.age in 30..40){
            riskProfileForInsuranceLine.decrementScore()
        }
    }
}

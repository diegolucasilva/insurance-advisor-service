package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class MiddleAgeRule : RiskScoreRule {

    override fun execute(userRiskProfile: UserRiskProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userRiskProfile.age >= 30 && userRiskProfile.age <= 40){
            riskProfileLineInsurance.decrementScore();
        }
    }
}

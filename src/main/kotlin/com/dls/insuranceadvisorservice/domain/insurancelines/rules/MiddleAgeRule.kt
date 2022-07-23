package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

class MiddleAgeRule : RiskScoreRule {

    override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userProfile.age in 30..40){
            riskProfileLineInsurance.score-=1;
        }
    }
}

package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

class OldAgeRule : RiskScoreRule {

    override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userProfile.age > 60){
            riskProfileLineInsurance.finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE;
        }
    }
}

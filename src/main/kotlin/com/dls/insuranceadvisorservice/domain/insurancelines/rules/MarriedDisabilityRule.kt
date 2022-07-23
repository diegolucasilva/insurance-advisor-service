package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

class MarriedDisabilityRule: RiskScoreRule {


     override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userProfile.maritalStatus == UserProfile.MaritalStatus.married){
            riskProfileLineInsurance.score-=1;
        }
    }
}
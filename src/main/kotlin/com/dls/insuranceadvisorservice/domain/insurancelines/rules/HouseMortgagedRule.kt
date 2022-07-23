package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

class HouseMortgagedRule: RiskScoreRule {

     override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userProfile.house?.ownershipStatus == UserProfile.OwnershipStatus.mortgaged){
            riskProfileLineInsurance.score+=1;
        }
    }
}
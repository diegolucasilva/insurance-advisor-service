package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class HouseMortgagedRule: RiskScoreRule {

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userRiskProfile.house?.ownershipStatus == UserRiskProfile.OwnershipStatus.mortgaged){
            riskProfileLineInsurance.incrementScore()
        }
    }
}
package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

class MinimumIncomeRule: RiskScoreRule {

     override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userProfile.income <= 0){
            riskProfileLineInsurance.finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE
        }
    }
}
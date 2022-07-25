package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class HouseRule: RiskScoreRule {

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(userRiskProfile.house==null){
            riskProfileLineInsurance.finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE
        }
    }
}
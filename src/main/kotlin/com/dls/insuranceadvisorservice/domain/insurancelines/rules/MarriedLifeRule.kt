package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class MarriedLifeRule: RiskScoreRule {


     override fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(userRiskProfile.maritalStatus == UserRiskProfile.MaritalStatus.married){
            riskProfileForInsuranceLine.incrementScore()
        }
    }
}
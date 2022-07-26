package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

class HighIncomeRule: RiskScoreRule {

    val HIGH_INCOME = 200000;

     override fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(userRiskProfile.income > HIGH_INCOME){
            riskProfileForInsuranceLine.decrementScore()
        }
    }
}
package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile


interface RiskScoreRule {

    fun execute(userRiskProfile: UserRiskProfile, riskProfileForInsuranceLine: RiskProfileForInsuranceLine)
}
package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule

interface InsuranceLineRiskScoreCalculator {

    fun execute(userRiskProfile: UserRiskProfile): RiskProfileForInsuranceLine

    fun getRules(): List<RiskScoreRule>

}
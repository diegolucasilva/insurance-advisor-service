package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import java.time.zone.ZoneRulesProvider.getRules

interface LineOfInsuranceRiskScoreCalculator {

    fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance

    fun getRules(): List<RiskScoreRule>

}
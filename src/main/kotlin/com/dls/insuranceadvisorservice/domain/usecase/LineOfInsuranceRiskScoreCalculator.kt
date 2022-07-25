package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

interface LineOfInsuranceRiskScoreCalculator {

    fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance


}
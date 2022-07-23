package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile

interface LineOfInsuranceRiskScoreCalculator {

    fun execute(userProfile: UserProfile): RiskProfileLineInsurance


}
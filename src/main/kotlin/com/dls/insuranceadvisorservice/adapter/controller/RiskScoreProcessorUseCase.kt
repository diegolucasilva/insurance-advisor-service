package com.dls.insuranceadvisorservice.adapter.controller

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

interface RiskScoreProcessorUseCase{
    fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileLineInsurance>
}
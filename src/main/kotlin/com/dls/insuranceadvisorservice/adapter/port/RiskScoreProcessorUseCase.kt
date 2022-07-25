package com.dls.insuranceadvisorservice.adapter.port

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

interface RiskScoreProcessorUseCase{
    fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileLineInsurance>
}
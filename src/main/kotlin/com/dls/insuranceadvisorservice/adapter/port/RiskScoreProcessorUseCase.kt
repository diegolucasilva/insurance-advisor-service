package com.dls.insuranceadvisorservice.adapter.port

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile

interface RiskScoreProcessorUseCase{
    fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileForInsuranceLine>
}
package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.adapter.port.RiskScoreProcessorUseCase
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.*

import org.springframework.stereotype.Service

@Service
class RiskScoreProcessorUseCaseImpl: RiskScoreProcessorUseCase {

    override fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileForInsuranceLine>{
        return getLineRiskCalculators().map {
            it.execute(userRiskProfile);
        }.toList()
    }

    private fun getLineRiskCalculators():List<InsuranceLineRiskScoreCalculator>{
        return listOf(
            AutoInsuranceLineRiskScore(),
            HomeInsuranceLineRiskScore(),
            LifeInsuranceLineRiskScore(),
        )
    }
}
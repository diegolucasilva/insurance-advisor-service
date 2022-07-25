package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.adapter.controller.RiskScoreProcessorUseCase
import com.dls.insuranceadvisorservice.domain.insurancelines.AutoInsuranceRiskScore
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.DisabilityInsuranceRiskScore
import com.dls.insuranceadvisorservice.domain.insurancelines.HomeInsuranceRiskScore
import com.dls.insuranceadvisorservice.domain.insurancelines.LifeInsuranceRiskScore

import org.springframework.stereotype.Service

@Service
class RiskScoreProcessorUseCaseImpl: RiskScoreProcessorUseCase {

    override fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileLineInsurance>{
        return getLineRiskCalculators().map {
            it.execute(userRiskProfile);
        }.toList()
    }

    private fun getLineRiskCalculators():List<LineOfInsuranceRiskScoreCalculator>{
        return listOf(
            AutoInsuranceRiskScore(),
            DisabilityInsuranceRiskScore(),
            HomeInsuranceRiskScore(),
            LifeInsuranceRiskScore())
    }
}
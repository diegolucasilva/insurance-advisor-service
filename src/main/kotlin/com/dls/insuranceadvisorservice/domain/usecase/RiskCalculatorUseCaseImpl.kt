package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.controller.RiskCalculatorUseCase
import com.dls.insuranceadvisorservice.domain.insurancelines.AutoInsuranceRiskScore
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.DisabilityInsuranceRiskScore

import org.springframework.stereotype.Service

@Service
class RiskCalculatorUseCaseImpl: RiskCalculatorUseCase {

    override fun execute(userProfile: UserProfile): List<RiskProfileLineInsurance>{
        return getLineRiskCalculators().map {
            it.execute(userProfile);
        }.toList()
    }

    private fun getLineRiskCalculators():List<LineOfInsuranceRiskScoreCalculator>{
        return listOf(AutoInsuranceRiskScore(), DisabilityInsuranceRiskScore())
    }
}
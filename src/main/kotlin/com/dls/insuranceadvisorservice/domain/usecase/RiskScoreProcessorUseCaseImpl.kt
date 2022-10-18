package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.adapter.port.RiskScoreProcessorUseCase
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.insurancelines.*

import org.springframework.stereotype.Service

@Service
class RiskScoreProcessorUseCaseImpl: RiskScoreProcessorUseCase {

    override fun execute(customer: Customer): List<RiskProfileForInsuranceLine>{
        return getLineRiskCalculators().map {
            it.execute(customer);
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
package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.adapter.port.RiskScoreProcessorUseCase
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.*

import org.springframework.stereotype.Service

@Service
class RiskScoreProcessorUseCaseImpl: RiskScoreProcessorUseCase {

    override fun execute(userRiskProfile: UserRiskProfile): List<RiskProfileForInsuranceLine>{
        return getLineRiskCalculators(userRiskProfile).map {
            it.execute(userRiskProfile);
        }.toList()
    }

    private fun getLineRiskCalculators(userRiskProfile: UserRiskProfile):List<InsuranceLineRiskScoreCalculator>{
        var homeInsurance: InsuranceLineRiskScoreCalculator? = null
        homeInsurance = if(userRiskProfile.house?.ownershipStatus== UserRiskProfile.OwnershipStatus.rented){
            RenterInsuranceLineRiskScore()
        }else
            HomeInsuranceLineRiskScore()

        return listOf(
            AutoInsuranceLineRiskScore(),
            DisabilityInsuranceLineRiskScore(),
            homeInsurance,
            LifeInsuranceLineRiskScore(),
            UmbrellaInsuranceLineRiskScore()
        )
    }
}
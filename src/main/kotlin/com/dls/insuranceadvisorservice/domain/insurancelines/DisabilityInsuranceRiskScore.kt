package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class DisabilityInsuranceRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userProfile: UserProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.DISABILITY, userProfile.questionScore,
            RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )
        getRules().forEach {
            it.execute(userProfile, riskProfileBaseLine);
        }
        riskProfileBaseLine.calculateProfileBasedScore();
        return riskProfileBaseLine

    }

    private fun getRules():List<RiskScoreRule>{
        return listOf(
            MinimumIncomeRule(),
            OldAgeRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            HouseMortgagedRule(),
            DependentsIncomeRule(),
            MarriedDisabilityRule(),
        )
    }
}
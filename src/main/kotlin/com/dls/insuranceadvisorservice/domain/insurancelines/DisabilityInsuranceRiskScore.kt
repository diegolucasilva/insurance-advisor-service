package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class DisabilityInsuranceRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.DISABILITY, userRiskProfile.questionScore,
            RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )
        getRules().forEach {
            it.execute(userRiskProfile, riskProfileBaseLine);
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
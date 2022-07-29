package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.InsuranceLineRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class DisabilityInsuranceLineRiskScore: InsuranceLineRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileForInsuranceLine {
        var riskProfileBaseLine = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.DISABILITY, userRiskProfile.questionScore.sum())
        getRules().forEach {
            it.execute(userRiskProfile, riskProfileBaseLine);
        }
        riskProfileBaseLine.calculatePlanBasedOnScore();
        return riskProfileBaseLine

    }

    override fun getRules():List<RiskScoreRule>{
        return listOf(
            MinimumIncomeRule(),
            OldAgeRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            HouseMortgagedRule(),
            DependentsIncomeRule(),
            MarriedDisabilityRule(),
            SecondRiskAnswerRule(),
            LowIncomeRule())
    }
}
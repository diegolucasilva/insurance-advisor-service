package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.InsuranceLineRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class UmbrellaInsuranceLineRiskScore: InsuranceLineRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileForInsuranceLine {

        var riskProfileBaseLine = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.UMBRELLA, userRiskProfile.questionScore.sum())

        getRules().forEach {
            it.execute(userRiskProfile, riskProfileBaseLine);
        }
        riskProfileBaseLine.calculatePlanBasedOnScore();
        return riskProfileBaseLine
    }

    override fun getRules():List<RiskScoreRule>{
        return listOf(
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule())
    }
}
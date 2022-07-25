package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class LifeInsuranceRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.LIFE, userRiskProfile.questionScore,
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
            OldAgeRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            DependentsIncomeRule(),
            MarriedLifeRule(),
          )
    }
}
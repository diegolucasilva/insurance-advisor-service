package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class HomeInsuranceRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.HOME, userRiskProfile.questionScore,
            RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )
        getRules().forEach {
            it.execute(userRiskProfile, riskProfileBaseLine);
        }
        riskProfileBaseLine.calculateProfileBasedScore();
        return riskProfileBaseLine

    }

    override fun getRules():List<RiskScoreRule>{
        return listOf(
            HouseRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            HouseMortgagedRule(),
          )
    }
}
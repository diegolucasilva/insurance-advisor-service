package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class HomeRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userProfile: UserProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.HOME, userProfile.questionScore,
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
            HouseRule(),
            OldAgeRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            HouseMortgagedRule(),
          )
    }
}
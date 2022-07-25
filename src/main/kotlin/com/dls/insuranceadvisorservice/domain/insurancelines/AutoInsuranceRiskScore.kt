package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.LineOfInsuranceRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class AutoInsuranceRiskScore: LineOfInsuranceRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileLineInsurance {
        var riskProfileBaseLine = RiskProfileLineInsurance(
            RiskProfileLineInsurance.Name.AUTO, userRiskProfile.questionScore)
        getRules().forEach {
            it.execute(userRiskProfile, riskProfileBaseLine);
        }
        riskProfileBaseLine.calculatePlanBasedOnScore();
        return riskProfileBaseLine
    }

    override fun getRules():List<RiskScoreRule>{
        return listOf(
            VehicleRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            VehicleProductionDateRule())
    }
}
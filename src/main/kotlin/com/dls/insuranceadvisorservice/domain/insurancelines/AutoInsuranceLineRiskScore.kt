package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.InsuranceLineRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class AutoInsuranceLineRiskScore: InsuranceLineRiskScoreCalculator {

    override fun execute(userRiskProfile: UserRiskProfile): RiskProfileForInsuranceLine {
        var riskProfileBaseLine = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.AUTO, userRiskProfile.questionScore.sum())
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
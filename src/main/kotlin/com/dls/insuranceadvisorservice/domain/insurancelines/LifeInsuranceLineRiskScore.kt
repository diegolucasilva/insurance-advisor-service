package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.usecase.InsuranceLineRiskScoreCalculator
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.insurancelines.rules.*

class LifeInsuranceLineRiskScore: InsuranceLineRiskScoreCalculator {

    override fun execute(customer: Customer): RiskProfileForInsuranceLine {
        var riskProfileBaseLine = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.LIFE, customer.questionScore.sum())

        getRules().forEach {
            it.execute(customer, riskProfileBaseLine);
        }

        riskProfileBaseLine.calculatePlanBasedOnScore();
        return riskProfileBaseLine

    }

    override fun getRules():List<RiskScoreRule>{
        return listOf(
            OldAgeRule(),
            UnderThirtyAgeRule(),
            MiddleAgeRule(),
            HighIncomeRule(),
            DependentsIncomeRule(),
            MarriedRule(),
            LowIncomeRule())
    }
}
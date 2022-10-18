package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer

class HighIncomeRule: RiskScoreRule {

    val HIGH_INCOME = 200000;

     override fun execute(customer: Customer, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(customer.income > HIGH_INCOME){
            riskProfileForInsuranceLine.decrementScore()
        }
    }
}
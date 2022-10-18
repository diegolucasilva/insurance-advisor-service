package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule

interface InsuranceLineRiskScoreCalculator {

    fun execute(customer: Customer): RiskProfileForInsuranceLine

    fun getRules(): List<RiskScoreRule>

}
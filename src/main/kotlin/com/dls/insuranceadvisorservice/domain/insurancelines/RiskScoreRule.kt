package com.dls.insuranceadvisorservice.domain.insurancelines

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer


interface RiskScoreRule {

    fun execute(customer: Customer, riskProfileForInsuranceLine: RiskProfileForInsuranceLine)
}
package com.dls.insuranceadvisorservice.domain

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer

interface RiskScoreProcessorUseCase{
    fun execute(customer: Customer): List<RiskProfileForInsuranceLine>
}
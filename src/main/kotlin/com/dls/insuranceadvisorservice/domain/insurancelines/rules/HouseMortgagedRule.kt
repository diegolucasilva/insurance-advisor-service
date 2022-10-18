package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.House
import com.dls.insuranceadvisorservice.domain.customer.Customer

class HouseMortgagedRule: RiskScoreRule {

     override fun execute(customer: Customer, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(customer.house?.ownershipStatus == House.OwnershipStatus.mortgaged){
            riskProfileForInsuranceLine.incrementScore()
        }
    }
}
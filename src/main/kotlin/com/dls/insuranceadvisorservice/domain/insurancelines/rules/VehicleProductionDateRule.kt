package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import java.time.LocalDate

class VehicleProductionDateRule: RiskScoreRule {

     override fun execute(customer: Customer, riskProfileForInsuranceLine: RiskProfileForInsuranceLine){
        if(wasProducedInTheLastFiveYear(customer.vehicle?.year)){
            riskProfileForInsuranceLine.incrementScore()
        }
    }
    private fun wasProducedInTheLastFiveYear(year: Int?): Boolean{
        if(year!=null){
            return (LocalDate.now().year - year.toInt()) <= 5
        }
        return false
    }

}
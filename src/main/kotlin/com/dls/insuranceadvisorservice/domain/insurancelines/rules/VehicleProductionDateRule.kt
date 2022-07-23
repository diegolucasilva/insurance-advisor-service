package com.dls.insuranceadvisorservice.domain.insurancelines.rules

import com.dls.insuranceadvisorservice.domain.insurancelines.RiskScoreRule
import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import java.time.LocalDate

class VehicleProductionDateRule: RiskScoreRule {

     override fun execute(userProfile: UserProfile, riskProfileLineInsurance: RiskProfileLineInsurance){
        if(wasProducedInTheLastFiveYear(userProfile.vehicle?.year)){
            riskProfileLineInsurance.score+=1;
        }
    }
    private fun wasProducedInTheLastFiveYear(year:Int?): Boolean{
        if(year!=null){
            return (LocalDate.now().year - year) <= 5
        }
        return false;
    }

}
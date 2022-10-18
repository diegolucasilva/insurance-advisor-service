package com.dls.insuranceadvisorservice.domain.usecase

import com.dls.insuranceadvisorservice.port.RiskScoreProcessorUseCase
import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.insurancelines.*

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RiskScoreProcessorUseCaseImpl: RiskScoreProcessorUseCase {

    override fun execute(customer: Customer): List<RiskProfileForInsuranceLine>{
        //AUTO
        var riskProfileAUTO = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.AUTO, customer.questionScore.sum())
        if(customer.vehicle==null){
            riskProfileAUTO.insurancePlanStatus = RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE
        }else{
            if(customer.age < 30){
                riskProfileAUTO.score -=2
            }
            else if(customer.age in 30..40){
                riskProfileAUTO.score -=1
            }
            if(customer.income > 200000){
                riskProfileAUTO.score -=1
            }
            if((LocalDate.now().year - customer.vehicle?.year?.toInt()!!) <= 5){
                riskProfileAUTO.score+=1
            }
        }
        riskProfileAUTO.insurancePlanStatus = calculatePlanBasedOnScore(riskProfileAUTO.score)

        //LIFE
        var riskProfileLIFE = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.LIFE, customer.questionScore.sum())
        if(customer.age < 30){
            riskProfileLIFE.score -=2
        }
        else if(customer.age in 30..40){
            riskProfileLIFE.score -=1
        }
        if(customer.income > 200000){
            riskProfileLIFE.score -=1
        }
        riskProfileLIFE.insurancePlanStatus = calculatePlanBasedOnScore(riskProfileLIFE.score)

        //HOME
        var riskProfileHOME = RiskProfileForInsuranceLine(
            RiskProfileForInsuranceLine.Name.HOME, customer.questionScore.sum())
        if(customer.age < 30){
            riskProfileHOME.score -=2
        }
        else if(customer.age in 30..40){
            riskProfileHOME.score -=1
        }
        if(customer.income > 200000){
            riskProfileHOME.score -=1
        }
        riskProfileHOME.insurancePlanStatus = calculatePlanBasedOnScore(riskProfileLIFE.score)

        return listOf(riskProfileAUTO,riskProfileLIFE,riskProfileHOME)
    }


    fun calculatePlanBasedOnScore(score: Int): RiskProfileForInsuranceLine.InsurancePlanStatus{
        var insurancePlanStatus: RiskProfileForInsuranceLine.InsurancePlanStatus? = null
        if(insurancePlanStatus!= RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE){
            insurancePlanStatus = when {
                score <= 0 -> RiskProfileForInsuranceLine.InsurancePlanStatus.ECONOMIC
                score in (1..2) -> RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR
                else -> RiskProfileForInsuranceLine.InsurancePlanStatus.RESPONSIBLE
            }
        }
        return insurancePlanStatus
    }

}
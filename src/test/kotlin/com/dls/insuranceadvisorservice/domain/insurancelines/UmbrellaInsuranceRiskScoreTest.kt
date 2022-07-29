package com.dls.insuranceadvisorservice.domain.insurancelines;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test
import java.time.LocalDate


internal class UmbrellaInsuranceRiskScoreTest {

    private val umbrellaInsuranceRiskScore= UmbrellaInsuranceLineRiskScore()


    @Test
    //If the user is under 30 years old, deduct 2 risk points from all lines of insurance.
    fun `Given a user that is under 30, the umbrella insurance risk calculator should return the score status REGULAR and less 1 score points`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year)
        )
        //WHEN
        val riskProfileLineInsurance = umbrellaInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-2)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    @Test
    //User is between 30 and 40 years old, deduct 1.
    //User's income is above $200k, deduct 1 risk point from all lines of insurance.
    fun `Given a user that is between 30 and 40 years old and income above $200k, the umbrella insurance risk calculator should return the score status REGULAR and less 2 score point`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=35,
            dependents=1,
            income=200001,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-4)
        )
        //WHEN
        val riskProfileLineInsurance = umbrellaInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-2)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }
}
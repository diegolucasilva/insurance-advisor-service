package com.dls.insuranceadvisorservice.domain.insurancelines;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test
import java.time.LocalDate


internal class AutoInsuranceRiskScoreTest {

    private val autoInsuranceRiskScore= AutoInsuranceLineRiskScore()

    @Test
    fun `Given a user that doesn't have a vehicle, the auto insurance risk calculator should return the score status INELIGIBLE`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = null
        )
        //WHEN
        val riskProfileLineInsurance = autoInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE)
    }

    @Test
    //If the user is under 30 years old, deduct 2 risk points from all lines of insurance.
    //If the user's vehicle was produced in the last 5 years, add 1 risk point to that vehicle’s score.
    fun `Given a user that is under 30 and has a vehicle produced in the last 5 years, the auto insurance risk calculator should return the score status REGULAR and less 1 score points`() {
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
        val riskProfileLineInsurance = autoInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-1)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    @Test
    //If the user is under 30 years old, deduct 2 risk points from all lines of insurance.
    //If the user's vehicle was produced in the last 5 years, add 1 risk point to that vehicle’s score.
    fun `Given a user that is under 30 and has a vehicle wasn't produced in the last 5 years, the auto insurance risk calculator should return the score status REGULAR and less 2 score points`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-10)
        )
        //WHEN
        val riskProfileLineInsurance = autoInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-2)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    @Test
    //If the user is between 30 and 40 years old, deduct 1.
    //If the user's vehicle was produced in the last 5 years, add 1 risk point to that vehicle’s score.
    fun `Given a user that is between 30 and 40 years old and has a vehicle wasn't produced in the last 5 years, the auto insurance risk calculator should return the score status RESPONSIBLE  and less 1 score point`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=35,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-6)
        )
        //WHEN
        val riskProfileLineInsurance = autoInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-1)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    @Test
    //User is between 30 and 40 years old, deduct 1.
    //User's vehicle was produced in the last 5 years, add 1 risk point to that vehicle’s score.
    //User's income is above $200k, deduct 1 risk point from all lines of insurance.
    fun `Given a user that is between 30 and 40 years old, that has a vehicle produced in the last 5 years and income above $200k, the auto insurance risk calculator should return the score status RESPONSIBLE and less 1 score point`() {
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
        val riskProfileLineInsurance = autoInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-1)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }
}
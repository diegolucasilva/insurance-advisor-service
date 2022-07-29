package com.dls.insuranceadvisorservice.domain.insurancelines;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class LifeInsuranceRiskScoreTest {

    private val lifeInsuranceRiskScore = LifeInsuranceLineRiskScore()


    @Test
    fun `Given a user that is under 60 years old, the auto insurance risk calculator should return the score status INELIGIBLE`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=64,
            dependents=5,
            income=0,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = null
        )
        //WHEN
        val riskProfileLineInsurance = lifeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE)
    }

    @Test
    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    //User has dependents, add 1 risk point to both the disability and life scores.
    fun `Given a user that is under 30 with dependents, the auto insurance risk calculator should return the score status REGULAR and less 1 score points`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.single,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year)
        )
        //WHEN
        val riskProfileLineInsurance = lifeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-1)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    //User is married, add 1 risk point
    @Test
    fun `Given a user that is under 30 and married, doesn't have dependent and have a house mortgaged, the auto insurance risk calculator should return the score status REGULAR and less 1 score point`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=0,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserRiskProfile.House(UserRiskProfile.OwnershipStatus.mortgaged),
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-10)
        )
        //WHEN
        val riskProfileLineInsurance = lifeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum()-1)
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.REGULAR)
    }

    @Test
    //The user is between 30 and 40 years old, deduct 1.
    //The user has dependents, add 1 risk point to both the disability and life scores.
    fun `Given a user that is between 30 and 40 years old and has a dependencies, the auto insurance risk calculator should return the score status RESPONSIBLE`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=35,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.single,
            house = null,
            questionScore = listOf(1,1,1),
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-6)
        )
        //WHEN
        val riskProfileLineInsurance = lifeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum())
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.RESPONSIBLE)
    }

    @Test
    //User is between 30 and 40 years old, deduct 1.
    //User is married, add 1 risk point
    //User has dependents, add 1 risk point to both the disability and life scores.
    //User's income is above $200k, deduct 1 risk point from all lines of insurance.
    fun `Given a user that is between 30 and 40 years old, that has dependencies, that is married and has income above $200k, the auto insurance risk calculator should return the score status RESPONSIBLE`() {
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
        val riskProfileLineInsurance = lifeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore.sum())
        assert(riskProfileLineInsurance.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.RESPONSIBLE)
    }
}
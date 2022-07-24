package com.dls.insuranceadvisorservice.domain.insurancelines;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import org.junit.jupiter.api.Test
import java.time.LocalDate


class DisabilityRiskScoreTest {

    private val disabilityInsuranceRiskScore= DisabilityInsuranceRiskScore()

    @Test
    fun `Given a user that doesn't have a income, the auto insurance risk calculator should return the score status INELIGIBLE`() {
        //GIVEN
        val userProfile = UserProfile(
            age=30,
            dependents=1,
            income=0,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = null
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)
    }

    @Test
    fun `Given a user that is under 60 years old, the auto insurance risk calculator should return the score status INELIGIBLE`() {
        //GIVEN
        val userProfile = UserProfile(
            age=64,
            dependents=5,
            income=0,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = null
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)
    }

    @Test
    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    //User has dependents, add 1 risk point to both the disability and life scores.
    fun `Given a user that is under 30, has dependents and is single, the auto insurance risk calculator should return the score status REGULAR and less 1 score points`() {
        //GIVEN
        val userProfile = UserProfile(
            age=25,
            dependents=1,
            income=10,
            maritalStatus = UserProfile.MaritalStatus.single,
            house = null,
            questionScore = 2,
            vehicle = UserProfile.Vehicle(LocalDate.now().year)
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userProfile.questionScore-1)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.REGULAR)
    }

    @Test
    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    //User's house is mortgaged, add 1 risk point
    //User is married, remove 1 risk point
    fun `Given a user that is under 30, doesn't have dependents,house is mortgaged and is married, the auto insurance risk calculator should return the score status REGULAR and less 2 score point`() {
        //GIVEN
        val userProfile = UserProfile(
            age=25,
            dependents=0,
            income=10,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = UserProfile.House(UserProfile.OwnershipStatus.mortgaged),
            questionScore = 3,
            vehicle = UserProfile.Vehicle(LocalDate.now().year-10)
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userProfile.questionScore-2)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.REGULAR)
    }

    @Test
    //The user is between 30 and 40 years old, deduct 1.
    //The user has dependents, add 1 risk point to both the disability and life scores.
    fun `Given a user that is between 30 and 40 years old and has a dependencies, the auto insurance risk calculator should return the score status RESPONSIBLE`() {
        //GIVEN
        val userProfile = UserProfile(
            age=35,
            dependents=1,
            income=10,
            maritalStatus = UserProfile.MaritalStatus.single,
            house = null,
            questionScore = 3,
            vehicle = UserProfile.Vehicle(LocalDate.now().year-6)
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userProfile.questionScore)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.RESPONSIBLE)
    }

    @Test
    //User is between 30 and 40 years old, deduct 1.
    //User is married, remove 1 risk point
    //User has dependents, add 1 risk point to both the disability and life scores.
    //User's income is above $200k, deduct 1 risk point from all lines of insurance.
    fun `Given a user that is between 30 and 40 years old, that has dependencies, that is married and has income above $200k, the auto insurance risk calculator should return the score status RESPONSIBLE and less 2 score point`() {
        //GIVEN
        val userProfile = UserProfile(
            age=35,
            dependents=1,
            income=200001,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = null,
            questionScore = 5,
            vehicle = UserProfile.Vehicle(LocalDate.now().year-4)
        )
        //WHEN
        val riskProfileLineInsurance = disabilityInsuranceRiskScore.execute(userProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userProfile.questionScore-2)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.RESPONSIBLE)
    }
}
package com.dls.insuranceadvisorservice.domain.insurancelines;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test
import java.time.LocalDate


internal class HomeInsuranceRiskScoreTest {

    private val homeInsuranceRiskScore= HomeInsuranceRiskScore()

    @Test
    fun `Given a user that doesn't have a house, the auto insurance risk calculator should return the score status INELIGIBLE`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=30,
            dependents=1,
            income=1000000,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year)
        )
        //WHEN
        val riskProfileLineInsurance = homeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)
    }


    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    @Test
    fun `Given a user that is under 30 the auto insurance risk calculator should return the score status ECONOMIC and less 2 score points`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.single,
            house = UserRiskProfile.House(UserRiskProfile.OwnershipStatus.owned),
            questionScore = 2,
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year)
        )
        //WHEN
        val riskProfileLineInsurance = homeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore-2)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.ECONOMIC)
    }

    @Test
    //User is under 30 years old, deduct 2 risk points from all lines of insurance.
    //User's house is mortgaged, add 1 risk point
    fun `Given a user that is under 30 with housemortgaged, the auto insurance risk calculator should return the score status REGULAR and less 1 score point`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=25,
            dependents=0,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserRiskProfile.House(UserRiskProfile.OwnershipStatus.mortgaged),
            questionScore = 3,
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-10)
        )
        //WHEN
        val riskProfileLineInsurance = homeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore-1)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.REGULAR)
    }

    @Test
    //The user is between 30 and 40 years old, deduct 1.
    //The user income is above $200k, deduct 1 risk point from all lines of insurance.
    fun `Given a user that is between 30 and 40 years old with high income value, the auto insurance risk calculator should return the score status REGULAR with less 2 points`() {
        //GIVEN
        val userRiskProfile = UserRiskProfile(
            age=35,
            dependents=1,
            income=60000000,
            maritalStatus = UserRiskProfile.MaritalStatus.single,
            house = UserRiskProfile.House(UserRiskProfile.OwnershipStatus.owned),
            questionScore = 3,
            vehicle = UserRiskProfile.Vehicle(LocalDate.now().year-6)
        )
        //WHEN
        val riskProfileLineInsurance = homeInsuranceRiskScore.execute(userRiskProfile)
        //THEN
        assert(riskProfileLineInsurance.score ==userRiskProfile.questionScore-2)
        assert(riskProfileLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.REGULAR)
    }
}
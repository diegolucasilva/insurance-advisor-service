package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import org.junit.jupiter.api.Test


internal class HouseRuleTest {

    private val rule =HouseRule()

    @Test
    fun `Given a user without a house, this rule must change the final score status to INELIGIBLE`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile()
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
        assert(riskProfileBaseLine.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)
    }

    @Test
    fun `Given a user with a house,this rule must keep the final score status to NOTCALCULATED`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(UserProfile.House(UserProfile.OwnershipStatus.mortgaged))
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
        assert(riskProfileBaseLine.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileLineInsurance(
            name=RiskProfileLineInsurance.Name.AUTO,
            score=actualScore,
            finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )

    private fun givenUserProfile(house: UserProfile.House?=null) =
        UserProfile(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = house,
            questionScore = 2,
            vehicle = null
        )


}
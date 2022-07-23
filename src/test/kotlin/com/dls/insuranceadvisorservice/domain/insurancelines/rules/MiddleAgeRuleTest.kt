package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import org.junit.jupiter.api.Test


internal class MiddleAgeRuleTest {

    private val dependentsIncomeRule = MiddleAgeRule()


    @Test
    fun `Given a user between 30 and 40, this rule must deduct 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(31)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        dependentsIncomeRule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore-1)
    }

    @Test
    fun `Given a user that ins't between 30 and 40, this rule mustn't deduct 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(41)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        dependentsIncomeRule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileLineInsurance(
            name=RiskProfileLineInsurance.Name.AUTO,
            score=actualScore,
            finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )

    private fun givenUserProfile(age: Int) =
        UserProfile(
            age=age,
            dependents=1,
            income=1,
            maritalStatus = UserProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = null
        )


}
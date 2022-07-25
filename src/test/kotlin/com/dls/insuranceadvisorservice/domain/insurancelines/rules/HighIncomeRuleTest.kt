package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class HighIncomeRuleTest {

    private val rule =HighIncomeRule()

    @Test
    fun `Given a user with income above $200k, this rule must deduct 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(200001)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore-1)
    }

    @Test
    fun `Given a user with income above $200k, this rule mustn't deduct 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(100001)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileLineInsurance(
            name=RiskProfileLineInsurance.Name.AUTO,
            score=actualScore,
            finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )

    private fun givenUserProfile(income: Int) =
        UserRiskProfile(
            age=30,
            dependents=1,
            income=income,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = null
        )


}
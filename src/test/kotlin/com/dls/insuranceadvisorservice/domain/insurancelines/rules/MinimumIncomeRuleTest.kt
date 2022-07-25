package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class MinimumIncomeRuleTest {

    private val rule =MinimumIncomeRule()
    @Test
    fun `Given a user without income, this rule must change the final score status to INELIGIBLE`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(0)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score ==actualScore)
        assert(riskProfileBaseLine.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)

    }

    @Test
    fun `Given a user with income, this rule must keep the final score status to NOTCALCULATED`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(100001)
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
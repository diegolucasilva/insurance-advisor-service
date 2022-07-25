package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class OldAgeRuleTest {

    private val rule =OldAgeRule()

    @Test
    fun `Given a use over 60 years old, this rule must change the final score status to INELIGIBLE`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(78)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
        assert(riskProfileBaseLine.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.INELIGIBLE)

    }

    @Test
    fun `Given a use under 60 years old, this rule must keep the final score status to NOTCALCULATED`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(40)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score ==actualScore)
        assert(riskProfileBaseLine.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED)

    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileLineInsurance(
            name=RiskProfileLineInsurance.Name.AUTO,
            score=actualScore,
            finalScoreStatus = RiskProfileLineInsurance.FinalScoreStatus.NOTCALCULATED
        )

    private fun givenUserProfile(age: Int) =
        UserRiskProfile(
            age=age,
            dependents=1,
            income=1,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = 2,
            vehicle = null
        )


}
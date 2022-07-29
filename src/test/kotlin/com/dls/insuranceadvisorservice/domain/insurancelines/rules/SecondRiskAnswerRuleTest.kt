package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class SecondRiskAnswerRuleTest {

    private val rule =SecondRiskAnswerRule()

    @Test
    fun `Given a user with the second risk answer equals true, this rule must add 2 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(listOf(1,1,1))
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+2)
    }

    @Test
    fun `Given a user with the second risk answer equals false, this rule must not add 2 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(listOf(1,0,1))
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name=RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(score: List<Int>) =
        UserRiskProfile(
            age=30,
            dependents=1,
            income=10000,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = score,
            vehicle = null
        )


}
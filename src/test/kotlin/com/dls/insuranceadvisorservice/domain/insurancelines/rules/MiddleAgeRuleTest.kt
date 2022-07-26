package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
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
        RiskProfileForInsuranceLine(
            name=RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(age: Int) =
        UserRiskProfile(
            age=age,
            dependents=1,
            income=1,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = null
        )


}
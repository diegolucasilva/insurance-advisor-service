package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class UnderThirtyAgeRuleTest {

    private val rule = UnderThirtyAgeRule()


    @Test
    fun `Given a user under 30 years old, this rule must deduct 2 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(29)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore-2)
    }

    @Test
    fun `Given a user over 30 years old, this rule mustn't deduct any point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(45)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileLineInsurance(
            name=RiskProfileLineInsurance.Name.AUTO,
            score=actualScore)

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
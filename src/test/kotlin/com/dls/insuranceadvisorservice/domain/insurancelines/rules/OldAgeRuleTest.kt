package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
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
        assert(riskProfileBaseLine.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE)

    }

    @Test
    fun `Given a use under 60 years old, this rule must keep the  score`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(40)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score ==actualScore)
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
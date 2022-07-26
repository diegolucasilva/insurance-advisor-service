package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test

internal class MarriedLifeRuleTest {

    private val rule =MarriedLifeRule()

    @Test
    fun `Given a user married, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(UserRiskProfile.MaritalStatus.married)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userRiskProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+1)
    }

    @Test
    fun `Given a user single, this rule mustn't add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(UserRiskProfile.MaritalStatus.single)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userRiskProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name=RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(maritalStatus: UserRiskProfile.MaritalStatus) =
        UserRiskProfile(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = maritalStatus,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = null
        )


}
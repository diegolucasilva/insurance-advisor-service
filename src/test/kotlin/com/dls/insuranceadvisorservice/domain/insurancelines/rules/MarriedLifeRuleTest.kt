package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileLineInsurance
import com.dls.insuranceadvisorservice.domain.UserProfile
import org.junit.jupiter.api.Test

internal class MarriedLifeRuleTest {

    private val rule =MarriedLifeRule()

    @Test
    fun `Given a user married, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(UserProfile.MaritalStatus.married)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+1)
    }

    @Test
    fun `Given a user single, this rule mustn't add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(UserProfile.MaritalStatus.single)
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

    private fun givenUserProfile(maritalStatus: UserProfile.MaritalStatus) =
        UserProfile(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = maritalStatus,
            house = null,
            questionScore = 2,
            vehicle = null
        )


}
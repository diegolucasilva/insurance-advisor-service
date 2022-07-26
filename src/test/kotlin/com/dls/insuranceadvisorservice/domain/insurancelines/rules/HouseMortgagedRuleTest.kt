package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Test


internal class HouseMortgagedRuleTest {

    private val rule =HouseMortgagedRule()

    @Test
    fun `Given a user with house mortgaged, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(UserRiskProfile.OwnershipStatus.mortgaged)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userRiskProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+1)
    }

    @Test
    fun `Given a user with house owned, this rule mustn't add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(UserRiskProfile.OwnershipStatus.owned)
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

    private fun givenUserProfile(houseOwnershipStatus: UserRiskProfile.OwnershipStatus) =
        UserRiskProfile(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserRiskProfile.House(houseOwnershipStatus),
            questionScore = 2,
            vehicle = null
        )


}
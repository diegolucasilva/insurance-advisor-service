package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.House
import com.dls.insuranceadvisorservice.domain.customer.Customer
import org.junit.jupiter.api.Test


internal class HouseMortgagedRuleTest {

    private val rule =HouseMortgagedRule()

    @Test
    fun `Given a user with house mortgaged, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(House.OwnershipStatus.mortgaged)
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
        val userRiskProfile = givenUserProfile(House.OwnershipStatus.owned)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userRiskProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name= RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(houseOwnershipStatus: House.OwnershipStatus) =
        Customer(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = Customer.MaritalStatus.married,
            house = House(houseOwnershipStatus),
            questionScore = listOf(1,1,0),
            vehicle = null
        )


}
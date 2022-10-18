package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.House
import com.dls.insuranceadvisorservice.domain.customer.Customer
import org.junit.jupiter.api.Test


internal class HouseRuleTest {

    private val rule =HouseRule()

    @Test
    fun `Given a user without a house, this rule must change the final score status to INELIGIBLE`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile()
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
        assert(riskProfileBaseLine.insurancePlanStatus == RiskProfileForInsuranceLine.InsurancePlanStatus.INELIGIBLE)
    }

    @Test
    fun `Given a user with a house,this rule must keep the score`() {
        //GIVEN
        val actualScore = 2
        val userRiskProfile = givenUserProfile(House(House.OwnershipStatus.mortgaged))
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userRiskProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)}

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name= RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(house: House?=null) =
        Customer(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = Customer.MaritalStatus.married,
            house = house,
            questionScore = listOf(1,1,0),
            vehicle = null
        )


}
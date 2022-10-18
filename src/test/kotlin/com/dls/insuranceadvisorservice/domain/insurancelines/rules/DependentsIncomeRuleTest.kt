package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import org.junit.jupiter.api.Test


internal class DependentsIncomeRuleTest {

    private val dependentsIncomeRule = DependentsIncomeRule()


    @Test
    fun `Given a user with dependents, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(3)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        dependentsIncomeRule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+1)
    }

    @Test
    fun `Given a user without dependents, this rule mustn't add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val userProfile = givenUserProfile(0)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        dependentsIncomeRule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name= RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(dependents: Int) =
        Customer(
            age=30,
            dependents=dependents,
            income=1,
            maritalStatus = Customer.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = null
        )


}
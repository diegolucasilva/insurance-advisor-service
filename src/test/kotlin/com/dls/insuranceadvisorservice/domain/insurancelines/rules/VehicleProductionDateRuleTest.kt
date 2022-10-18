package com.dls.insuranceadvisorservice.domain.insurancelines.rules;

import com.dls.insuranceadvisorservice.domain.riskprofile.RiskProfileForInsuranceLine
import com.dls.insuranceadvisorservice.domain.customer.Customer
import com.dls.insuranceadvisorservice.domain.customer.Vehicle
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class VehicleProductionDateRuleTest {

    private val rule =VehicleProductionDateRule()

    @Test
    fun `Given a user with a vehicle produced in the last 5 years, this rule must add 1 point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val validYear = LocalDate.now().year - 5
        val userProfile = givenUserProfile(validYear)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore+1)
    }

    @Test
    fun `Given a user with a vehicle that wasn't produced in the last 5 years, this rule mustn't add any point to the risk score of a line insurance`() {
        //GIVEN
        val actualScore = 2
        val unValidYear = LocalDate.now().year - 8
        val userProfile = givenUserProfile(unValidYear)
        val riskProfileBaseLine = givenARiskProfileBaseLine(actualScore)
        //WHEN
        rule.execute(userProfile,riskProfileBaseLine);
        //THEN
        assert(riskProfileBaseLine.score == actualScore)
    }

    private fun givenARiskProfileBaseLine(actualScore: Int) =
        RiskProfileForInsuranceLine(
            name= RiskProfileForInsuranceLine.Name.AUTO,
            score=actualScore)

    private fun givenUserProfile(year: Int) =
        Customer(
            age=30,
            dependents=1,
            income=10,
            maritalStatus = Customer.MaritalStatus.married,
            house = null,
            questionScore = listOf(1,1,0),
            vehicle = Vehicle(year)
        )


}
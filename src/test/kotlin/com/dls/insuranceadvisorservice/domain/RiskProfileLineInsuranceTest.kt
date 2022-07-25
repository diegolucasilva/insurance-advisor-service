package com.dls.insuranceadvisorservice.domain;

import org.junit.jupiter.api.Test


internal class RiskProfileLineInsuranceTest {


    @Test
    fun `Given a risk profile base line insurance, when decrement a score, the score must be 1 point lower`() {
        //GIVEN
         val actualScore = 3
         val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.decrementScore()
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore-1)
    }

    @Test
    fun `Given a risk profile base line insurance, when increment a score, the score must be 1 point higher`() {
        //GIVEN
        val actualScore = 3
        val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.incrementScore()
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore+1)
    }

    @Test
    fun `Given a risk profile base line insurance, when increment 2 scores, the score must be 2 point higher`() {
        //GIVEN
        val actualScore = 3
        val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.incrementScore(2)
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore+2)
    }

    @Test
    fun `Given a risk profile base line insurance with 2 points, the plan based on score must to be REGULAR`() {
        //GIVEN
        val actualScore = 2
        val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.calculatePlanBasedOnScore()
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore)
        assert(riskProfileBaseLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.REGULAR)
    }

    @Test
    fun `Given a risk profile base line insurance with 0 points, the plan based on score must to be ECONOMIC`() {
        //GIVEN
        val actualScore = 0
        val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.calculatePlanBasedOnScore()
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore)
        assert(riskProfileBaseLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.ECONOMIC)
    }

    @Test
    fun `Given a risk profile base line insurance with 3 points, the plan based on score must to be RESPONSIBLE`() {
        //GIVEN
        val actualScore = 3
        val riskProfileBaseLineInsurance = RiskProfileLineInsurance(RiskProfileLineInsurance.Name.AUTO, actualScore)
        //WHEN
        riskProfileBaseLineInsurance.calculatePlanBasedOnScore()
        //THEN
        assert(riskProfileBaseLineInsurance.score == actualScore)
        assert(riskProfileBaseLineInsurance.finalScoreStatus == RiskProfileLineInsurance.FinalScoreStatus.RESPONSIBLE)
    }





}
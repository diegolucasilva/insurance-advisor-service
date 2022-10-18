package com.dls.insuranceadvisorservice.controller;

import com.dls.insuranceadvisorservice.adapter.config.ErrorResponse
import com.dls.insuranceadvisorservice.adapter.dto.RiskProfileResponse
import com.dls.insuranceadvisorservice.adapter.dto.UserPersonalInformationRequest
import com.dls.insuranceadvisorservice.domain.UserRiskProfile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class InsuranceAdvisorControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    @Test
    fun `Given a user with the following attributes, the risk score result must be auto=regular, disability=ineligible, home=economic, life=regular, umbrella=economic`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=35,
            dependents=2,
            income=30000,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.owned),
            riskQuestions = listOf(0,0,1),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.CREATED)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.body?.auto, "regular")
        Assertions.assertEquals(response?.body?.disability, "economic")
        Assertions.assertEquals(response?.body?.home, "economic")
        Assertions.assertEquals(response?.body?.life, "regular")
        Assertions.assertEquals(response?.body?.umbrella, "economic")

    }


    @Test
    fun `Given a user with the following attributes, the risk score result must be auto=regular, disability=ineligible, home=economic, life=ineligible, umbrella=regular`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=83,
            dependents=2,
            income=2000001,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.owned),
            riskQuestions = listOf(1,1,1),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.CREATED)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.body?.auto, "responsible")
        Assertions.assertEquals(response?.body?.disability, "ineligible")
        Assertions.assertEquals(response?.body?.home, "regular")
        Assertions.assertEquals(response?.body?.life, "ineligible")
        Assertions.assertEquals(response?.body?.umbrella, "regular")
    }

    @Test
    fun `Given a user with the following attributes, the risk score result must be auto=regular, disability=ineligible, renter=economic, life=ineligible, umbrella=regular`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=83,
            dependents=2,
            income=2000001,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.rented),
            riskQuestions = listOf(1,1,1),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.CREATED)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.body?.auto, "responsible")
        Assertions.assertEquals(response?.body?.disability, "ineligible")
        Assertions.assertEquals(response?.body?.life, "ineligible")
        Assertions.assertEquals(response?.body?.umbrella, "regular")
    }

    @Test
    fun `Given a user with the following attributes, the risk score result must be ineligible for all lines of insurance`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=83,
            dependents=2,
            income=3000,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.owned),
            riskQuestions = listOf(1,1,1),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.CREATED)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.body?.auto, "ineligible")
        Assertions.assertEquals(response?.body?.disability, "ineligible")
        Assertions.assertEquals(response?.body?.home, "ineligible")
        Assertions.assertEquals(response?.body?.life, "ineligible")
        Assertions.assertEquals(response?.body?.umbrella, "ineligible")

    }

    @Test
    fun `Given a user with the following invalid attributes, the response status should be BAD_REQUEST`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=-1,
            dependents=2,
            income=-10,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.owned),
            riskQuestions = listOf(1,1,1),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenInvalidRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.BAD_REQUEST)
    }



    private fun whenRequestIsMade(request: HttpEntity<UserPersonalInformationRequest>): ResponseEntity<RiskProfileResponse>? =
         testRestTemplate.postForEntity("/insurance/advisor", request, RiskProfileResponse::class.java)


    private fun whenInvalidRequestIsMade(request: HttpEntity<UserPersonalInformationRequest>): ResponseEntity<ErrorResponse>? =
         testRestTemplate.postForEntity("/insurance/advisor", request, ErrorResponse::class.java)


}
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
class InsuranceAdvisorControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    @Test
    fun `Given a user with the following attributes, the risk score result must be auto=regular, disability=ineligible, home=economic, life=regular`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=35,
            dependents=2,
            income=0,
            maritalStatus = UserRiskProfile.MaritalStatus.married,
            house = UserPersonalInformationRequest.HouseRequest(UserRiskProfile.OwnershipStatus.owned),
            riskQuestions = listOf(0,1,0),
            vehicle = UserPersonalInformationRequest.VehicleRequest(2018)
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserPersonalInformationRequest>(userPersonalInformationRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.CREATED)
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response?.body?.auto, "regular")
        Assertions.assertEquals(response?.body?.disability, "ineligible")
        Assertions.assertEquals(response?.body?.home, "economic")
        Assertions.assertEquals(response?.body?.life, "regular")
    }


    @Test
    fun `Given a user with the following attributes, the risk score result must be auto=regular, disability=ineligible, home=economic, life=ineligible`() {
        //GIVEN
        val userPersonalInformationRequest = UserPersonalInformationRequest(
            age=83,
            dependents=2,
            income=2000000,
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
package com.dls.insuranceadvisorservice.controller;

import com.dls.insuranceadvisorservice.config.ErrorResponse
import com.dls.insuranceadvisorservice.controller.dto.RiskProfileResponse
import com.dls.insuranceadvisorservice.controller.dto.UserProfileRequest
import com.dls.insuranceadvisorservice.domain.UserProfile
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
        val userProfileRequest = UserProfileRequest(
            age=Integer(35),
            dependents=Integer(2),
            income=Integer(0),
            maritalStatus = UserProfile.MaritalStatus.married,
            house = UserProfileRequest.HouseRequest(UserProfile.OwnershipStatus.owned),
            riskQuestions = listOf(0,1,0),
            vehicle = UserProfileRequest.VehicleRequest(Integer(2018))
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserProfileRequest>(userProfileRequest, HttpHeaders()))

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
        val userProfileRequest = UserProfileRequest(
            age=Integer(83),
            dependents=Integer(2),
            income=Integer(2000000),
            maritalStatus = UserProfile.MaritalStatus.married,
            house = UserProfileRequest.HouseRequest(UserProfile.OwnershipStatus.owned),
            riskQuestions = listOf(1,1,1),
            vehicle = UserProfileRequest.VehicleRequest(Integer(2018))
        )
        //WHEN
        val response = whenRequestIsMade(HttpEntity<UserProfileRequest>(userProfileRequest, HttpHeaders()))

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
        val userProfileRequest = UserProfileRequest(
            age=Integer(-1),
            dependents=Integer(2),
            income=Integer(-10),
            maritalStatus = UserProfile.MaritalStatus.married,
            house = UserProfileRequest.HouseRequest(UserProfile.OwnershipStatus.owned),
            riskQuestions = listOf(1,1,1),
            vehicle = UserProfileRequest.VehicleRequest(Integer(2018))
        )
        //WHEN
        val response = whenInvalidRequestIsMade(HttpEntity<UserProfileRequest>(userProfileRequest, HttpHeaders()))

        //THEN
        Assertions.assertEquals(response?.statusCode, HttpStatus.BAD_REQUEST)
    }



    private fun whenRequestIsMade(request: HttpEntity<UserProfileRequest>): ResponseEntity<RiskProfileResponse>? =
         testRestTemplate.postForEntity("/insurance/advisor", request, RiskProfileResponse::class.java)


    private fun whenInvalidRequestIsMade(request: HttpEntity<UserProfileRequest>): ResponseEntity<ErrorResponse>? =
         testRestTemplate.postForEntity("/insurance/advisor", request, ErrorResponse::class.java)


}
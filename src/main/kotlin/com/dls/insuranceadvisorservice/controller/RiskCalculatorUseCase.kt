package com.dls.insuranceadvisorservice.controller

import com.dls.insuranceadvisorservice.domain.RiskProfile
import com.dls.insuranceadvisorservice.domain.UserProfile

interface RiskCalculatorUseCase{
    fun execute(userProfile: UserProfile): List<RiskProfile>
}